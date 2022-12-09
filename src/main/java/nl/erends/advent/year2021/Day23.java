package nl.erends.advent.year2021;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/**
 * --- Day 23: Amphipod ---
 * <p>A group of amphipods notice your fancy submarine and flag you down.The
 * amphipods would like a method to organize every amphipod into side rooms so
 * that each side room contains one type of amphipod. What is the least energy
 * required to organize the amphipods?
 * <p><a href="https://adventofcode.com/2021/day/23">2021 Day 23</a>
 */
public class Day23 extends AbstractProblem<List<String>, Number> {

    private static final Map<Character, Integer> roomLocation = Map.of(
            'A', 2, 'B', 4, 'C', 6, 'D', 8);
    private static final Map<Character, Integer> stepCost = Map.of(
            'A', 1, 'B', 10, 'C', 100, 'D', 1000);
    private static final List<Integer> hallwaysPositions = List.of(10, 9, 7, 5, 3, 1, 0);
    private int roomSize = 2;
    private Hotel bestHotel;
    private Set<String> seenStates;

    public static void main(String[] args) {
        new Day23().setAndSolve(Util.readInput(2021, 23));
    }

    @Override
    protected Integer solve1() {
        solve(new Hotel(input));
        return bestHotel.energy;
    }

    @Override
    public Number solve2() {
        roomSize = 4;
        Hotel root = new Hotel(input);
        root.roomA.add(1, 'D');
        root.roomA.add(1, 'D');
        root.roomB.add(1, 'B');
        root.roomB.add(1, 'C');
        root.roomC.add(1, 'A');
        root.roomC.add(1, 'B');
        root.roomD.add(1, 'C');
        root.roomD.add(1, 'A');
        solve(root);
        return bestHotel.energy;
    }

    private void solve(Hotel root) {
        seenStates = new HashSet<>();
        bestHotel = new Hotel(root);
        bestHotel.energy = Integer.MAX_VALUE;
        List<Hotel> hotels = new ArrayList<>();
        hotels.add(root);
        while (!hotels.isEmpty()) {
            hotels.sort(Comparator.comparingInt(Hotel::getPotentialEnergy));
            hotels.addAll(nextStates(hotels.remove(0)));
        }
    }

    private List<Hotel> nextStates(Hotel hotel) {
        if (!seenStates.add(hotel.toString())) {
            return Collections.emptyList();
        }
        List<Hotel> nextStates = moveAllRoomToRoom(hotel);
        nextStates.addAll(moveAllHallwayToRoom(hotel));
        nextStates.addAll(moveAllRoomToHallway(hotel));
        nextStates.removeIf(h -> h == null || h.getPotentialEnergy() >= bestHotel.energy);
        return nextStates;
    }

    private List<Hotel> moveAllRoomToRoom(Hotel hotel) {
        List<Hotel> nextStates = new ArrayList<>();
        for (char room = 'A'; room <= 'D'; room++) {
            nextStates.add(moveRoomToRoom(hotel, room));
        }
        return nextStates;
    }

    private Hotel moveRoomToRoom(Hotel hotel, char fromRoomChar) {
        List<Character> fromRoom = hotel.getRoom(fromRoomChar);
        if (fromRoom.isEmpty()) {
            return null;
        }
        Character toRoomChar = fromRoom.get(0);
        if (toRoomChar == fromRoomChar) {
            return null;
        }
        List<Character> toRoom = hotel.getRoom(toRoomChar);
        if (toRoom.size() == roomSize) {
            return null;
        }
        if (!toRoom.isEmpty() && toRoom.stream().anyMatch(c -> !Objects.equals(c, toRoomChar))) {
            return null;
        }
        int fromIndex = roomLocation.get(fromRoomChar);
        int toIndex = roomLocation.get(toRoomChar);
        if (hotel.pathBlocked(fromIndex, toIndex)) {
            return null;
        }
        Hotel newHotel = new Hotel(hotel);
        fromRoom = newHotel.getRoom(fromRoomChar);
        toRoom = newHotel.getRoom(toRoomChar);
        int steps = roomSize - fromRoom.size() + 1 + Math.abs(fromIndex - toIndex) + roomSize - toRoom.size();
        int cost = steps * stepCost.get(toRoomChar);
        toRoom.add(0, fromRoom.remove(0));
        newHotel.energy += cost;
        return newHotel;
    }

    private List<Hotel> moveAllHallwayToRoom(Hotel hotel) {
        List<Hotel> nextStates = new ArrayList<>();
        for (int i : hallwaysPositions) {
            nextStates.add(moveHallwayToRoom(hotel, i));
        }
        return nextStates;
    }

    private Hotel moveHallwayToRoom(Hotel hotel, int hallwayIndex) {
        char toRoomChar = hotel.hallway.charAt(hallwayIndex);
        if (toRoomChar == '.') {
            return null;
        }
        List<Character> toRoom = hotel.getRoom(toRoomChar);
        if (toRoom.size() == roomSize) {
            return null;
        }
        if (!toRoom.isEmpty() && toRoom.stream().anyMatch(c -> !Objects.equals(c, toRoomChar))) {
            return null;
        }
        int toIndex = roomLocation.get(toRoomChar);
        if (hotel.pathBlocked(hallwayIndex, toIndex)) {
            return null;
        }
        Hotel newHotel = new Hotel(hotel);
        toRoom = newHotel.getRoom(toRoomChar);
        int steps = Math.abs(hallwayIndex - toIndex) + roomSize - toRoom.size();
        int cost = steps * stepCost.get(toRoomChar);
        toRoom.add(0, toRoomChar);
        newHotel.hallway = hotel.hallway.substring(0, hallwayIndex) + '.' + hotel.hallway.substring(hallwayIndex + 1);
        newHotel.energy += cost;
        if (newHotel.isSolved()) {
            if (newHotel.energy < bestHotel.energy) {
                bestHotel = newHotel;
            }
            return null;
        }
        return newHotel;
    }

    private List<Hotel> moveAllRoomToHallway(Hotel hotel) {
        List<Hotel> nextStates = new ArrayList<>();
        for (char room = 'A'; room <= 'D'; room++) {
            for (int hallwayIndex : hallwaysPositions) {
                nextStates.add(moveRoomToHallway(hotel, room, hallwayIndex));
            }
        }
        return nextStates;
    }

    private Hotel moveRoomToHallway(Hotel hotel, char fromRoomChar, int hallwayIndex) {
        List<Character> fromRoom = hotel.getRoom(fromRoomChar);
        if (fromRoom.isEmpty() || fromRoom.stream().allMatch(c -> c == fromRoomChar)) {
            return null;
        }
        int fromIndex = roomLocation.get(fromRoomChar);
        if (hotel.pathBlocked(fromIndex, hallwayIndex)) {
            return null;
        }
        Hotel newHotel = new Hotel(hotel);
        fromRoom = newHotel.getRoom(fromRoomChar);
        int steps = roomSize - fromRoom.size() + 1 + Math.abs(fromIndex - hallwayIndex);
        char subject = fromRoom.remove(0);
        int cost = steps * stepCost.get(subject);
        newHotel.hallway = hotel.hallway.substring(0, hallwayIndex) + subject + hotel.hallway.substring(hallwayIndex + 1);
        newHotel.energy += cost;
        return newHotel;
    }

    private class Hotel {
        String hallway;
        List<Character> roomA = new ArrayList<>();
        List<Character> roomB = new ArrayList<>();
        List<Character> roomC = new ArrayList<>();
        List<Character> roomD = new ArrayList<>();
        int energy = 0;
        int potentialEnergy = 0;

        private Hotel(List<String> input) {
            hallway = input.get(1).substring(1, 12);
            roomA.add(0, input.get(3).toCharArray()[3]);
            roomA.add(0, input.get(2).toCharArray()[3]);
            roomB.add(0, input.get(3).toCharArray()[5]);
            roomB.add(0, input.get(2).toCharArray()[5]);
            roomC.add(0, input.get(3).toCharArray()[7]);
            roomC.add(0, input.get(2).toCharArray()[7]);
            roomD.add(0, input.get(3).toCharArray()[9]);
            roomD.add(0, input.get(2).toCharArray()[9]);
        }

        private Hotel(Hotel other) {
            this.hallway = other.hallway;
            this.roomA = new ArrayList<>(other.roomA);
            this.roomB = new ArrayList<>(other.roomB);
            this.roomC = new ArrayList<>(other.roomC);
            this.roomD = new ArrayList<>(other.roomD);
            this.energy = other.energy;
        }

        private List<Character> getRoom(char room) {
            return switch (room) {
                case 'A' -> roomA;
                case 'B' -> roomB;
                case 'C' -> roomC;
                case 'D' -> roomD;
                default -> throw new IllegalArgumentException("Illegal room: " + room);
            };
        }

        private boolean pathBlocked(int from, int to) {
            int lowIndex = Math.min(from, to);
            int highIndex = Math.max(from, to);
            for (int i = lowIndex; i <= highIndex; i++) {
                if (i == from) {
                    continue;
                }
                if (hallway.charAt(i) != '.') {
                    return true;
                }
            }
            return false;
        }

        private boolean isSolved() {
            return "...........".equals(hallway)
                    && roomA.stream().allMatch(c -> c == 'A')
                    && roomB.stream().allMatch(c -> c == 'B')
                    && roomC.stream().allMatch(c -> c == 'C');
        }

        @Override
        public String toString() {
            return hallway + '-' + roomA + '-' + roomB + '-' + roomC + '-' + roomD;
        }

        private int getPotentialEnergy() {
            if (potentialEnergy != 0) {
                return potentialEnergy;
            }
            potentialEnergy = energy;
            for (int hallwayIndex : hallwaysPositions) {
                char toRoomChar = hallway.charAt(hallwayIndex);
                if (toRoomChar != '.') {
                    int steps = Math.abs(hallwayIndex - roomLocation.get(toRoomChar));
                    potentialEnergy += steps * stepCost.get(toRoomChar);
                }
            }
            for (char roomChar = 'A'; roomChar <= 'D'; roomChar++) {
                List<Character> room = getRoom(roomChar);
                boolean fixed = true;
                int indexOffset = roomSize - room.size();
                for (int index = room.size() - 1; index >= 0; index--) {
                    char toRoomChar = room.get(index);
                    if (toRoomChar == roomChar && fixed) {
                        continue;
                    }
                    fixed = false;
                    int steps = index + indexOffset + 1;
                    if (toRoomChar == roomChar) {
                        steps += 2;
                    }
                    steps += Math.abs(roomLocation.get(roomChar) - roomLocation.get(toRoomChar));
                    potentialEnergy += steps * stepCost.get(toRoomChar);
                }

                int emptySpaces = roomSize;
                for (int index = room.size() - 1; index >= 0; index--) {
                    char c = room.get(index);
                    if (c == roomChar) {
                        emptySpaces--;
                    } else {
                        break;
                    }
                }
                int steps = emptySpaces * (emptySpaces + 1) / 2;
                potentialEnergy += steps * stepCost.get(roomChar);
            }
            return potentialEnergy;
        }
    }
}
