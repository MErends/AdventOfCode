package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;

import java.util.*;

public class Day06 {

    public static void main(String[] args) {
        List<String> input = Util.getFileAsList("2018day06.txt");
        long start = System.currentTimeMillis();
        Map<Point, Integer> sources = new HashMap<>();
        List<Point> edges = new ArrayList<>();
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        for (String line : input) {
            String[] coords = line.split(",");
            int x = Integer.parseInt(coords[0].trim());
            int y = Integer.parseInt(coords[1].trim());
            maxX = Math.max(maxX, x);
            maxY = Math.max(maxY, y);
            sources.put(new Point(x, y), 0);
        }
        maxX++;
        maxY++;
        int pointsWithin10k = 0;
        for (int y = 0; y < maxY; y++) {
            for (int x = 0; x < maxX; x++) {
                Point nearest = getNearest(x, y, sources.keySet());
                if (nearest != null) {
                    sources.put(nearest, sources.get(nearest) + 1);
                }
                if (getTotalDistance(x, y, sources.keySet()) < 10000) {
                    pointsWithin10k++;
                }
                if (x == 0 || x == maxX - 1 || y == 0 || y == maxY - 1) {
                    edges.add(nearest);
                }
            }
        }
        edges.forEach(sources::remove);
        OptionalInt maximum = sources.values().stream().mapToInt(i -> i).max();
        System.out.println(maximum.orElse(-1));
        System.out.println(pointsWithin10k);
        long end = System.currentTimeMillis();
        System.out.println("Parts 1 & 2: " + (end - start) + " millis.");
    }

    
    private static Point getNearest(int x, int y, Collection<Point> sources) {
        List<Point> nearest = new ArrayList<>();
        int minDistance = Integer.MAX_VALUE;
        for (Point source : sources) {
            int distance = Math.abs(x - source.x) + Math.abs(y - source.y);
            if (distance < minDistance) {
                minDistance = distance;
                nearest = new ArrayList<>();
                nearest.add(source);
            } else if (distance == minDistance) {
                nearest.add(source);
            }
        }
        return nearest.size() == 1 ? nearest.get(0) : null;
    }
    
    private static int getTotalDistance(int x, int y, Collection<Point> sources) {
        int totalDistance = 0;
        for (Point source : sources) {
            totalDistance += Math.abs(x - source.x) + Math.abs(y - source.y);
        }
        return totalDistance;
    }
    
    private static class Point {
        private int x;
        private int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
