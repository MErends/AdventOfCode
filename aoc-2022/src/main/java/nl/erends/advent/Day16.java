package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * --- Day 16: Proboscidea Volcanium ---
 * <p>The sensors have led you to the origin of the distress signal: the device
 * is surrounded by elephants! You need to get the elephants out of here,
 * quickly. Your device estimates that you have 30 minutes before the volcano
 * erupts. You scan the cave for other options and discover a network of pipes
 * and pressure-release valves. What is the most pressure you can release?
 * <p><a href="https://adventofcode.com/2022/day/16">2022 Day 16</a>
 */
public class Day16 extends AbstractProblem<List<String>, Number> {

    private final Map<String, Room> roomMap = new HashMap<>();
    private State bestState;
    private State2 bestState2;
    private Map<String, Integer> distanceMap;
    private List<Room> valves;

    public static void main(String[] args) {
        new Day16().setAndSolve(Util.readInput(2022, 16));
    }

    @Override
    protected Integer solve1() {
        bestState = new State();
        bestState.released = Integer.MIN_VALUE;
        for (String line : input) {
            Room room = new Room(line);
            roomMap.put(room.name, room);
        }
        valves = roomMap.values().stream()
                .filter(r -> r.rate != 0)
                .sorted((r1, r2) -> r2.rate - r1.rate)
                .toList();
        fillDistanceMap();
        State start = new State();
        start.position = roomMap.get("AA");
        start.valvesClosed.addAll(valves);
        resolveState(start);
        return bestState.released;
    }

    @Override
    public Number solve2() {
        bestState2 = new State2();
        bestState2.released = Integer.MIN_VALUE;
        State2 start = new State2();
        start.mPosition = roomMap.get("AA");
        start.ePosition = roomMap.get("AA");
        start.valvesClosed.addAll(valves);
        resolveState(start);
        return bestState2.released;
    }

    private void fillDistanceMap() {
        distanceMap = new HashMap<>();
        for (int fromValve = 0; fromValve < valves.size(); fromValve++) {
            Room valveOne = valves.get(fromValve);
            distanceMap.put("AA" + ',' + valveOne.name, computeDistance(roomMap.get("AA"), valveOne));
            for (int toValve = fromValve + 1; toValve < valves.size(); toValve++) {
                Room valveTwo = valves.get(toValve);
                int distance = computeDistance(valveOne, valveTwo);
                distanceMap.put(valveTwo.name + ',' + valveOne.name, distance);
                distanceMap.put(valveOne.name + ',' + valveTwo.name, distance);
            }
        }
    }

    private void resolveState(State s) {
        s.valvesClosed.removeIf(r -> getDistance(s.position, r) + s.time + 1 >= 30);
        int potential = s.released;
        for (Room target : s.valvesClosed) {
            int distance = getDistance(s.position, target);
            int timeLeft = (30 - s.time - distance - 1);
            potential += timeLeft * target.rate;
        }
        if (potential < bestState.released) {
            return;
        }
        if (s.valvesClosed.isEmpty() && s.released > bestState.released) {
            bestState = s;
            return;
        }
        for (Room target : s.valvesClosed) {
            int distance = getDistance(s.position, target);
            State newState = new State();
            newState.valvesClosed.addAll(s.valvesClosed);
            newState.valvesClosed.remove(target);
            newState.valvesOpen.addAll(s.valvesOpen);
            newState.valvesOpen.add(target);
            newState.position = target;
            newState.time = s.time + distance + 1;
            newState.released = s.released + (30 - newState.time) * target.rate;
            resolveState(newState);
        }
    }
    
    private void resolveState(State2 s) {
        if (s.time == 26) {
            if (s.released > bestState2.released) {
                bestState2 = s;
            }
            return;
        }
        int timeLeft = 26 - s.time;
        int potential = s.released + s.rate * timeLeft;
        List<Room> remainingValves = new ArrayList<>(valves);
        remainingValves.removeIf(v -> s.valvesOpen.contains(v));
        for (Room valve : remainingValves) {
            potential += (timeLeft - 1) * valve.rate;
        }
        if (potential <= bestState2.released) {
            return;
        }
        if (setNewTargets(s)) {
            return;
        }
        s.released += s.rate;
        if (s.mSteps == 0 && s.mTarget != null) {
            s.mPosition = s.mTarget;
            s.mTarget = null;
            s.rate += s.mPosition.rate;
            s.valvesOpen.add(s.mPosition);
        } else {
            s.mSteps--;
        }
        if (s.eSteps == 0 && s.eTarget != null) {
            s.ePosition = s.eTarget;
            s.eTarget = null;
            s.rate += s.ePosition.rate;
            s.valvesOpen.add(s.ePosition);
        } else {
            s.eSteps--;
        }
        s.time++;
        resolveState(s);
    }

    private boolean setNewTargets(State2 s) {
        if (s.mTarget == null && !s.valvesClosed.isEmpty()) {
            for (Room target : s.valvesClosed) {
                State2 newState = new State2(s);
                newState.valvesClosed.remove(target);
                newState.mTarget = target;
                newState.mSteps = getDistance(newState.mPosition, newState.mTarget);
                newState.mPosition = null;
                resolveState(newState);
            }
            return true;
        }
        if (s.eTarget == null && !s.valvesClosed.isEmpty()) {
            for (Room target : s.valvesClosed) {
                State2 newState = new State2(s);
                newState.valvesClosed.remove(target);
                newState.eTarget = target;
                newState.eSteps = getDistance(newState.ePosition, newState.eTarget);
                newState.ePosition = null;
                resolveState(newState);
            }
            return true;
        }
        return false;
    }

    private int getDistance(Room from, Room to) {
        return distanceMap.get(from.name + ',' + to.name);
    }

    private int computeDistance(Room from, Room to) {
        int distance = 1;
        Set<String> neighbors = new HashSet<>(from.tunnels);
        while (!neighbors.contains(to.name)) {
            Set<String> newNeighbors = new HashSet<>();
            for (String neighbor : neighbors) {
                Room neighborRoom = roomMap.get(neighbor);
                newNeighbors.addAll(neighborRoom.tunnels);
            }
            neighbors = newNeighbors;
            distance++;
        }
        return distance;
    }

    private static class Room {

        String name;
        int rate;
        List<String> tunnels;

        private Room(String line) {
            String[] words = line.split(" ");
            name = words[1];
            rate = Integer.parseInt(words[4].substring(5, words[4].length() - 1));
            tunnels = new ArrayList<>();
            for (int i = 9; i < words.length; i++) {
                tunnels.add(words[i].substring(0, 2));
            }
        }
    }

    private static class State {

        Set<Room> valvesClosed = new HashSet<>();
        List<Room> valvesOpen = new ArrayList<>();
        Room position;
        int time;
        int released;
    }

    private static class State2 {

        List<Room> valvesClosed = new ArrayList<>();
        List<Room> valvesOpen = new ArrayList<>();
        Room mPosition;
        Room mTarget;
        Room ePosition;
        Room eTarget;
        int mSteps;
        int eSteps;
        int time;
        int rate;
        int released;

        State2() {
        }

        State2(State2 s) {
            this.valvesClosed.addAll(s.valvesClosed);
            this.valvesOpen.addAll(s.valvesOpen);
            this.mPosition = s.mPosition;
            this.mTarget = s.mTarget;
            this.ePosition = s.ePosition;
            this.eTarget = s.eTarget;
            this.mSteps = s.mSteps;
            this.eSteps = s.eSteps;
            this.time = s.time;
            this.rate = s.rate;
            this.released = s.released;
        }
    }
}
