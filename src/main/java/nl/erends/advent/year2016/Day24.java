package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;

import java.util.*;

public class Day24 {
    
    private static Map<String, Integer> distanceMap = new HashMap<>();
    private static int roundTrip = Integer.MAX_VALUE;

    public static void main(String[] args) {
        List<String> input = Util.getFileAsList("2016day24.txt");
        long start = System.currentTimeMillis();
        char[][] maze = new char[input.size()][input.get(0).length()];
        List<Character> nodes = new ArrayList<>();
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                char c = input.get(y).charAt(x);
                maze[y][x] = c;
                if (c != '#' && c != '.') nodes.add(c);
            }
        }
        for (char startNode : nodes) {
            int[][] distances = new int[maze.length][maze[0].length];
            for (int[] line : distances) {
                Arrays.fill(line, Integer.MAX_VALUE);
            }
            for (int y = 0; y < maze.length; y++) {
                for (int x = 0; x < maze[y].length; x++) {
                    char c = maze[y][x];
                    if (c == startNode) {
                        distances[y][x] = 0;
                        maze[y][x] = '.';
                        break;
                    }
                }
            }
            int lookingFor = 0;
            boolean done = false;
            while (!done) {
                done = true;
                for (int y = 0; y < maze.length; y++) {
                    for (int x = 0; x < maze[y].length; x++) {
                        if (distances[y][x] == lookingFor) {
                            if (maze[y + 1][x] != '#') {
                                distances[y + 1][x] = Math.min(distances[y + 1][x], lookingFor + 1);
                                done = false;
                            }
                            if (maze[y - 1][x] != '#') {
                                distances[y - 1][x] = Math.min(distances[y - 1][x], lookingFor + 1);
                                done = false;
                            }
                            if (maze[y][x + 1] != '#') {
                                distances[y][x + 1] = Math.min(distances[y][x + 1], lookingFor + 1);
                                done = false;
                            }
                            if (maze[y][x - 1] != '#') {
                                distances[y][x - 1] = Math.min(distances[y][x - 1], lookingFor + 1);
                                done = false;
                            }
                        }
                    }
                }
                lookingFor++;
            }
            for (int y = 0; y < maze.length; y++) {
                for (int x = 0; x < maze[y].length; x++) {
                    char c = maze[y][x];
                    if (nodes.contains(c) && c != startNode) {
                        distanceMap.put(startNode + "-" + c, distances[y][x]);
                    }
                }
            }
        }
        System.out.println(getMinDistance('0', distanceMap, 0));
        System.out.println(roundTrip);  //434 too low
        long end = System.currentTimeMillis();
        System.out.println("Part 1 & 2: " + (end - start) + " millis.");
    }
    
    
    private static int getMinDistance(char startFrom, Map<String, Integer> distances, int currentDistanceDone) {
        if (currentDistanceDone == 430) {
            System.currentTimeMillis();
        }
        if (distances.size() == 1) {
            StringBuilder keyStringBuilder = new StringBuilder(distances.keySet().iterator().next());
            keyStringBuilder.deleteCharAt(keyStringBuilder.indexOf("" + startFrom));
            keyStringBuilder.deleteCharAt(keyStringBuilder.indexOf("-"));
            char newStartFrom = keyStringBuilder.charAt(0);
            String backToRoot = newStartFrom + "-0";
            if (!distanceMap.containsKey(backToRoot)) {
                backToRoot = "0-" + newStartFrom;
            }
            roundTrip = Math.min(roundTrip, currentDistanceDone + distances.entrySet().iterator().next().getValue() + distanceMap.get(backToRoot));
            return distances.entrySet().iterator().next().getValue();
        }

        int minDistance = Integer.MAX_VALUE;
        for (String key : distances.keySet()) {
            if (key.contains("" + startFrom)) {
                StringBuilder keyStringBuilder = new StringBuilder(key);
                keyStringBuilder.deleteCharAt(keyStringBuilder.indexOf("" + startFrom));
                keyStringBuilder.deleteCharAt(keyStringBuilder.indexOf("-"));
                char newStartFrom = keyStringBuilder.charAt(0);
                Map<String, Integer> newDistanceMap = new HashMap<>(distances);
                for (String oldKey : distances.keySet()) {
                    if (oldKey.contains("" + startFrom)) {
                        newDistanceMap.remove(oldKey);
                    }
                }
                minDistance = Math.min(minDistance, distances.get(key) + getMinDistance(newStartFrom, newDistanceMap, currentDistanceDone + distances.get(key)));
            }
        }
        return minDistance;
    }
}
