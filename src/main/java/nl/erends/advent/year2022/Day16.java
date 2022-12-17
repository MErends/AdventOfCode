package nl.erends.advent.year2022;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Timer;
import nl.erends.advent.util.Util;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * --- Day 16
 * <p>
 * <p><a href="https://adventofcode.com/2022/day/16">2022 Day 16</a>
 */
public class Day16 extends AbstractProblem<List<String>, Number> {

    Map<String, Room> roomMap = new HashMap<>();
    int maxReleased = Integer.MIN_VALUE;
    private Deque<State> states;
    private Map<String, Integer> distanceMap;
    private List<String> valves;
    private int valveCount;

    public static void main(String[] args) {
        new Day16().setAndSolve(Util.readInput(2022, 16, 1));
    }

    @Override
    protected Integer solve1() {
        for (String line : input) {
            Room room = new Room(line);
            roomMap.put(room.name, room);
        }
        valves = roomMap.values().stream().filter(r -> r.rate != 0).map(r -> r.name).toList();
        valveCount = valves.size();
        fillDistanceMap();
        State start = new State();
        start.mPosition = "AA";
        start.valvesClosed.addAll(valves);
        states = new ArrayDeque<>();
        states.add(start);
        while (!states.isEmpty()) {
            Timer.tick(states);
            State state = states.remove();
            List<State> clones = states.stream()
                    .filter(s -> s.valvesOpen.equals(state.valvesOpen) && s.mPosition.equals(state.mPosition)).toList();
            boolean stateGood = true;
            for (State clone : clones) {
                if (clone.totalReleased > state.totalReleased) {
                    stateGood = false;
                } else {
                    states.remove(clone);
                }
            }
            if (stateGood) {
                resolveState(state);
            }
        }
        return maxReleased;
    }

    @Override
    public Number solve2() {
        roomMap = new HashMap<>();
        maxReleased = Integer.MIN_VALUE;
        for (String line : input) {
            Room room = new Room(line);
            roomMap.put(room.name, room);
        }
        valves = roomMap.values().stream().filter(r -> r.rate != 0).map(r -> r.name).toList();
        valveCount = valves.size();
        fillDistanceMap();
        State start = new State();
        start.mPosition = "AA";
        start.ePosition = "AA";
        start.valvesClosed.addAll(valves);
        states = new ArrayDeque<>();
        states.add(start);
        while (!states.isEmpty()) {
            Timer.tick(states);
            State state = states.remove();
            resolveState2(state);
        }
        return maxReleased;
    }

    private void fillDistanceMap() {
        distanceMap = new HashMap<>();
        for (int fromValve = 0; fromValve < valves.size(); fromValve++) {
            String from = valves.get(fromValve);
            distanceMap.put("AA" + ',' + from, getDistance("AA", from));
            for (int toValve = fromValve + 1; toValve < valves.size(); toValve++) {
                String to = valves.get(toValve);
                int distance = getDistance(from, to);
                distanceMap.put(to + ',' + from, distance);
                distanceMap.put(from + ',' + to, distance);
            }
        }
        for (Room fromRoom : roomMap.values()) {
            for (String to : valves) {
                String from = fromRoom.name;
                if (!from.equals(to)) {
                    int distance = getDistance(from, to);
                    distanceMap.put(from + ',' + to, distance);
                    if (valves.contains(from)) {
                        distanceMap.put(to + ',' + from, distance);
                    }
                }
            }
        }
    }

    private void resolveState(State oldState) {
        if (oldState.valvesClosed.isEmpty() || oldState.time >= 30) {
            maxReleased = Math.max(maxReleased, oldState.totalReleased);
            return;
        }
        for (String target : valves) {
            if (oldState.valvesClosed.contains(target)) {
                int distance = distanceMap.get(oldState.mPosition + ',' + target);
                State newState = new State(oldState);
                newState.time += distance;
                newState.mPosition = target;
                newState.time++;
                newState.valvesOpen.add(target);
                newState.valvesClosed.remove(target);
                if (newState.time < 30) {
                    newState.totalReleased += (30 - newState.time) * roomMap.get(target).rate;
                }
                states.add(newState);
            }
        }
    }

    private void resolveState2(State s) {
        if (s.valvesOpen.size() == valveCount || s.time >= 26) {
            maxReleased = Math.max(maxReleased, s.totalReleased);
            return;
        }

        if (s.mTarget == null || s.eTarget == null) {
            if (s.mTarget == null) { // Create new states for me
                for (String target : s.valvesClosed) {
                    int distance = distanceMap.get(s.mPosition + ',' + target);
                    State newState = new State(s);
                    newState.mTarget = target;
                    newState.mStepsLeft = distance;
                    newState.valvesClosed.remove(target);
                    states.add(newState);
                }
            }
            if (s.eTarget == null) { // Create new states for elephant
                for (String target : s.valvesClosed) {
                    int distance = distanceMap.get(s.ePosition + ',' + target);
                    State newState = new State(s);
                    newState.eTarget = target;
                    newState.eStepsLeft = distance;
                    newState.valvesClosed.remove(target);
                    states.add(newState);
                }
            }
            return;
        }

        if (s.mStepsLeft == 0 && s.eStepsLeft == 0) { // Both at target
            s.valvesOpen.add(s.mTarget);
            s.valvesOpen.add(s.eTarget);
            s.time++;
            if (s.time < 26) {
                s.totalReleased += (26 - s.time) * roomMap.get(s.mTarget).rate;
                s.totalReleased += (26 - s.time) * roomMap.get(s.eTarget).rate;
            }
            s.mTarget = null;
            s.eTarget = null;
            states.add(s);
            return;
        } else if (s.mStepsLeft == 0) {
            s.valvesOpen.add(s.mTarget);
            s.eStepsLeft--;
            s.time++;
            if (s.time < 26) {
                s.totalReleased += (26 - s.time) * roomMap.get(s.mTarget).rate;
            }
            s.mTarget = null;
            states.add(s);
            return;
        } else if (s.eStepsLeft == 0) {
            s.valvesOpen.add(s.eTarget);
            s.mStepsLeft--;
            s.time++;
            if (s.time < 26) {
                s.totalReleased += (26 - s.time) * roomMap.get(s.eTarget).rate;
            }
            s.eTarget = null;
            states.add(s);
            return;
        }

        if (s.mStepsLeft < s.eStepsLeft) { // Both in transit
            s.eStepsLeft -= s.mStepsLeft;
            s.time += s.mStepsLeft;
            s.mStepsLeft = 0;
            s.mPosition = s.mTarget;
        } else {
            s.mStepsLeft -= s.eStepsLeft;
            s.time += s.eStepsLeft;
            s.eStepsLeft = 0;
            s.ePosition = s.eTarget;
            if (s.mStepsLeft == 0) {
                s.mPosition = s.mTarget;
            }
        }
        states.add(s);
    }

    private int getDistance(String from, String to) {
        Room fromRoom = roomMap.get(from);
        int distance = 1;
        Set<String> neighbors = new HashSet<>(fromRoom.tunnels);
        while (!neighbors.contains(to)) {
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

    private class Room {
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

    private class State {
        Set<String> valvesClosed = new HashSet<>();
        Set<String> valvesOpen = new HashSet<>();
        String mPosition;
        String ePosition;
        String mTarget;
        String eTarget;
        int mStepsLeft;
        int eStepsLeft;
        int time;
        int totalReleased;

        public State() {
        }

        public State(State oldState) {
            valvesClosed = new HashSet<>(oldState.valvesClosed);
            valvesOpen = new HashSet<>(oldState.valvesOpen);
            mPosition = oldState.mPosition;
            ePosition = oldState.ePosition;
            mTarget = oldState.mTarget;
            eTarget = oldState.eTarget;
            mStepsLeft = oldState.mStepsLeft;
            eStepsLeft = oldState.eStepsLeft;
            time = oldState.time;
            totalReleased = oldState.totalReleased;
        }

        @Override
        public String toString() {
            return "State{" +
                    "valvesClosed=" + valvesClosed +
                    ", valvesOpen=" + valvesOpen +
                    ", mPosition='" + mPosition + '\'' +
                    ", ePosition='" + ePosition + '\'' +
                    ", mTarget='" + mTarget + '\'' +
                    ", eTarget='" + eTarget + '\'' +
                    ", mStepsLeft=" + mStepsLeft +
                    ", eStepsLeft=" + eStepsLeft +
                    ", time=" + time +
                    ", totalReleased=" + totalReleased +
                    '}';
        }
    }
}
