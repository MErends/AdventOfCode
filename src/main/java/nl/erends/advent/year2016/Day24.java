package nl.erends.advent.year2016;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day24 extends AbstractProblem<List<String>, Integer> {
    
    private Map<String, Integer> distanceMap = new HashMap<>();
    private int roundTrip = Integer.MAX_VALUE;
    private char[][] maze;
    private List<Character> nodes;

    public static void main(String[] args) {
        new Day24().setAndSolve(Util.readInput(2016, 24));
    }

    @Override
    public Integer solve1() {
        maze = new char[input.size()][input.get(0).length()];
        nodes = new ArrayList<>();
        fillMazeAndNodes();
        for (char startNode : nodes) {
            int[][] distances = new int[maze.length][maze[0].length];
            for (int[] line : distances) {
                Arrays.fill(line, Integer.MAX_VALUE);
            }
            initDistanceArray(startNode, distances);
            int lookingFor = 0;
            boolean done = false;
            while (!done) {
                done = fillDistanceArray(distances, lookingFor);
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
        int answer1 =  getMinDistance('0', distanceMap, 0);
        answer2 = roundTrip;
        return answer1;
    }

    private boolean fillDistanceArray(int[][] distances, int lookingFor) {
        boolean done = true;
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[y].length; x++) {
                done = fillNeighborDistances(x, y, lookingFor, distances, done);
            }
        }
        return done;
    }

    private boolean fillNeighborDistances(int x, int y, int lookingFor, int[][] distances, boolean done) {
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
        return done;
    }

    private void initDistanceArray(char startNode, int[][] distances) {
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
    }

    private void fillMazeAndNodes() {
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                char c = input.get(y).charAt(x);
                maze[y][x] = c;
                if (c != '#' && c != '.') {
                    nodes.add(c);
                }
            }
        }
    }


    private int getMinDistance(char startFrom, Map<String, Integer> distances, int currentDistanceDone) {
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
        for(Map.Entry<String, Integer> entry : distances.entrySet()) {
            String key = entry.getKey();
            Integer value = entry.getValue();
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
                minDistance = Math.min(minDistance, value + getMinDistance(newStartFrom, newDistanceMap, currentDistanceDone + value));
            }
        }
        return minDistance;
    }
}
