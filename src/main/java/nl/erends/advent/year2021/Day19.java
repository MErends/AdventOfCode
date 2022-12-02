package nl.erends.advent.year2021;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * --- Day 19: Beacon Scanner ---
 * <p>As your probe drifted down through this area, it released an assortment of
 * beacons and scanners into the water. Each scanner is capable of detecting all
 * beacons in a large cube centered on the scanner. Each scanner can report the
 * positions of all detected beacons relative to itself, the scanners do not
 * know their own position or orientation. How many beacons are there and what
 * is the greatest distance between two scanners?
 * <p><a href="https://adventofcode.com/2021/day/19">2021 Day 19</a>
 */
public class Day19 extends AbstractProblem<List<String>,Integer> {
    
    public static void main(String[] args) {
        new Day19().setAndSolve(Util.readInput(2021, 19));
    }

    @Override
    protected Integer solve1() {
        List<Scanner> scannersToDo = new ArrayList<>();
        List<Scanner> scannersDone = new ArrayList<>();
        Scanner scanner = new Scanner();
        scannersToDo.add(scanner);
        for (int index = 1; index < input.size(); index++) {
            String line = input.get(index);
            if (line.startsWith("---")) {
                scanner = new Scanner();
                scannersToDo.add(scanner);
            } else if (!line.isEmpty()){
                scanner.addCoord(line);
            }
        }
        Scanner root = scannersToDo.remove(0);
        root.pointToX();
        root.applyOffset("0,0,0");
        scannersDone.add(root);
        while (!scannersToDo.isEmpty()) {
            for (int index = 0; index < scannersDone.size(); index++) {
                Scanner done = scannersDone.get(index);
                for (Scanner toDo : scannersToDo) {
                    String offset = findOrientation(done, toDo);
                    if (offset != null) {
                        toDo.applyOffset(offset);
                        scannersToDo.remove(toDo);
                        scannersDone.add(toDo);
                        break;
                    }
                }
            }
        }
        Set<Coord> uniqueBeacons = new HashSet<>();
        scannersDone.stream()
                .flatMap(s -> s.absolutes.stream())
                .forEach(uniqueBeacons::add);
        answer2 = Integer.MIN_VALUE;
        for (Scanner scanner1 : scannersDone) {
            for (Scanner scanner2 : scannersDone) {
                int distance = Math.abs(scanner1.offsetX - scanner2.offsetX) +
                        Math.abs(scanner1.offsetY - scanner2.offsetY) +
                        Math.abs(scanner1.offsetZ - scanner2.offsetZ);
                answer2 = Math.max(distance, answer2);
            }
        }
        return uniqueBeacons.size();
    }
    
    private String findOrientation(Scanner reference, Scanner scanner) {
        scanner.pointToX();
        String offset = determineOffset(reference, scanner);
        if (offset != null) {
            return offset;
        }
        scanner.flip();
        offset = determineOffset(reference, scanner);
        if (offset != null) {
            return offset;
        }
        scanner.pointToY();
        offset = determineOffset(reference, scanner);
        if (offset != null) {
            return offset;
        }
        scanner.flip();
        offset = determineOffset(reference, scanner);
        if (offset != null) {
            return offset;
        }
        scanner.pointToZ();
        offset = determineOffset(reference, scanner);
        if (offset != null) {
            return offset;
        }
        scanner.flip();
        offset = determineOffset(reference, scanner);
        return offset;
    }
    
    // 68,-1246,-43
    private String determineOffset(Scanner reference, Scanner scanner) {
        for (int rotation = 0; rotation < 4; rotation++) {
            Map<String, Integer> offsetCount = new HashMap<>();
            for (Coord referCoord : reference.absolutes) {
                for (Coord otherCoord : scanner.transformed) {
                    String offsetString = (referCoord.x - otherCoord.x) + "," +
                            (referCoord.y - otherCoord.y) + "," +
                            (referCoord.z - otherCoord.z);
                    offsetCount.compute(offsetString, (k, v) -> v == null ? 1 : v + 1);
                }
            }
            String offset = offsetCount.entrySet().stream()
                    .filter(e -> e.getValue() >= 12)
                    .map(Map.Entry::getKey)
                    .findAny().orElse(null);
            if (offset != null) {
                return offset;
            }
            scanner.rotate();
        }
        return null;
    }
    
    
    private static class Scanner {
        final List<Coord> coords = new ArrayList<>();
        List<Coord> transformed = new ArrayList<>();
        final List<Coord> absolutes = new ArrayList<>();
        int offsetX;
        int offsetY;
        int offsetZ;
        
        private void addCoord(String line) {
            coords.add(new Coord(line));
        }
        
        private void pointToX() {
            transformed = new ArrayList<>();
            coords.forEach(c -> transformed.add(new Coord(c)));
        }
        
        private void pointToY() {
            pointToX();
            for (Coord coord : transformed) {
                int temp = coord.x;
                coord.x = coord.y;
                coord.y = -temp;
            }
        }
        
        private void pointToZ() {
            pointToX();
            for (Coord coord : transformed) {
                int temp = coord.x;
                coord.x = coord.z;
                coord.z = -temp;
            }
        }
        
        private void rotate() {
            for (Coord coord : transformed) {
                int temp = coord.y;
                coord.y = -coord.z;
                coord.z = temp;
            }
        }
        
        private void flip() {
            for (Coord coord : transformed) {
                coord.x = -coord.x;
                coord.z = -coord.z;
            }
        }
        
        private void applyOffset(String offset) {
            String[] split = offset.split(",");
            offsetX = Integer.parseInt(split[0]);
            offsetY = Integer.parseInt(split[1]);
            offsetZ = Integer.parseInt(split[2]);
            for (Coord transform : transformed) {
                Coord absolute = new Coord(transform);
                absolute.x = transform.x + offsetX;
                absolute.y = transform.y + offsetY;
                absolute.z = transform.z + offsetZ;
                absolutes.add(absolute);
            }
        }
    }
    
    private static class Coord {
        int x;
        int y;
        int z;
        
        private Coord(Coord coord) {
            this.x = coord.x;
            this.y = coord.y;
            this.z = coord.z;
        }
        
        private Coord(String line) {
            String[] split = line.split(",");
            x = Integer.parseInt(split[0]);
            y = Integer.parseInt(split[1]);
            z = Integer.parseInt(split[2]);
        }

        @Override
        public String toString() {
            return x + "," + y + "," + z;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Coord coord = (Coord) o;
            if (x != coord.x) return false;
            if (y != coord.y) return false;
            return z == coord.z;
        }

        @Override
        public int hashCode() {
            return toString().hashCode();
        }
    }
}
