package nl.erends.advent.year2018;

import nl.erends.advent.util.FileIO;

import java.util.*;

public class Day23 {
    
    static List<Drone> droneList = new ArrayList<>();
   
    public static void main(String[] args) {
        List<String> input = FileIO.getFileAsList("2018day23.txt");
        long start = System.currentTimeMillis();
        input.forEach(s -> droneList.add(new Drone(s)));
        Drone largestDrone = null;
        int radius = Integer.MIN_VALUE;
        for (Drone drone : droneList) {
            if (drone.r > radius) {
                largestDrone = drone;
                radius = drone.r;
            }
        }
        if (largestDrone == null) throw new IllegalStateException();
        int inRange = 0;
        for (Drone drone : droneList) {
            int distance = Math.abs(largestDrone.x - drone.x);
            distance += Math.abs(largestDrone.y - drone.y);
            distance += Math.abs(largestDrone.z - drone.z);
            if (distance <= largestDrone.r) inRange++;
        }
        System.out.println(inRange);
        long mid = System.currentTimeMillis();
        int min = -1 * (1 << 29);
        int size = 1 << 30;
        List<Cube> cubes = new ArrayList<>();
        cubes.add(new Cube(min, min, min, size));
        while (true) {
            Collections.sort(cubes);
            Cube smallest = cubes.remove(0);
            if (smallest.size == 1) {
                System.out.println(Math.abs(smallest.x) + Math.abs(smallest.y) + Math.abs(smallest.z));
                break;
            }
            cubes.addAll(smallest.getSubcubes());
        }
        long end = System.currentTimeMillis();
        
        System.out.println("Part 1: " + (mid - start) + " millis.\nPart 2: " + (end - mid) + " millis.");
    }
    
    
    private static class Drone {
        int x;
        int y;
        int z;
        int r;
        
        private Drone(String line) {
            String[] posR = line.split("=");
            r = Integer.parseInt(posR[2]);
            String[] pos = posR[1].split(",");
            x = Integer.parseInt(pos[0].split("<")[1]);
            y = Integer.parseInt(pos[1]);
            z = Integer.parseInt(pos[2].split(">")[0]);
        }
    }
    
    private static class Cube implements Comparable<Cube> {
        int x;
        int y;
        int z;
        int size;
        int dronesInRange;


        Cube(int x, int y, int z, int size) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.size = size;
            
            dronesInRange = 0;

            int cubeXmax = x + size - 1;
            int cubeYmax = y + size - 1;
            int cubeZmax = z + size - 1;
            for (Drone drone : droneList) {
                int distance = 0;
                distance += distanceFromBox(drone.x, x, cubeXmax);
                distance += distanceFromBox(drone.y, y, cubeYmax);
                distance += distanceFromBox(drone.z, z, cubeZmax);
                if (distance <= drone.r) {
                    dronesInRange++;
                }
            }
        }
        
        List<Cube> getSubcubes() {
            List<Cube> subCubes = new ArrayList<>();
            int newSize = size / 2;
            subCubes.add(new Cube(x + newSize, y, z, newSize));
            subCubes.add(new Cube(x, y + newSize, z, newSize));
            subCubes.add(new Cube(x, y, z + newSize, newSize));
            subCubes.add(new Cube(x + newSize, y + newSize, z, newSize));
            subCubes.add(new Cube(x + newSize, y, z + newSize, newSize));
            subCubes.add(new Cube(x, y + newSize, z + newSize, newSize));
            subCubes.add(new Cube(x + newSize, y + newSize, z + newSize, newSize));
            subCubes.add(new Cube(x, y, z, newSize));
            return subCubes;
        }


        @Override
        public int compareTo(Cube o) {
            int difference = Integer.compare(o.dronesInRange, dronesInRange);
            if (difference != 0) return difference;
            int thisDistance = Math.abs(x) + Math.abs(y) + Math.abs(z);
            int otherDistance = Math.abs(o.x) + Math.abs(o.y) + Math.abs(o.z);
            return Integer.compare(thisDistance, otherDistance);
        }

        @Override
        public String toString() {
            return "Cube{" +
                    "x=" + x +
                    ", y=" + y +
                    ", z=" + z +
                    ", size=" + size +
                    ", dronesInRange=" + dronesInRange +
                    '}';
        }
        
        private int distanceFromBox(int droneCoord, int lowerBoxCoord, int upperBoxCoord) {
            if (droneCoord < lowerBoxCoord) return lowerBoxCoord - droneCoord;
            if (droneCoord > upperBoxCoord) return droneCoord - upperBoxCoord;
            return 0;
        }
    }
}
