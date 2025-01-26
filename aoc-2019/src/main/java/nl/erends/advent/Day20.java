package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day20 extends AbstractProblem<List<String>, Integer> {

    private char[][] maze;
    private Map<String, List<String>> portals;
    private Map<String, String> inwardPortals;
    private Map<String, String> outwardPortals;
    private int[][] distanceArray;
    private List<int[][]> levelDistanceList;

    public static void main(String[] args) {
        new Day20().setAndSolve(Util.readInput(2019, 20));
    }

    @Override
    public Integer solve1() {
        readMaze();
        readOuterPortals();
        readInnerPortals();
        fillMappings();
        distanceArray = createIntArray();
        String[] start = portals.get("AA").getFirst().split(",");
        String[] end = portals.get("ZZ").getFirst().split(",");
        int xEnd = Integer.parseInt(end[0]);
        int yEnd = Integer.parseInt(end[1]);
        distanceArray[Integer.parseInt(start[1])][Integer.parseInt(start[0])] = 0;
        int steps = 0;
        while (distanceArray[yEnd][xEnd] == Integer.MAX_VALUE) {
            for (int y = 0; y < distanceArray.length; y++) {
                for (int x = 0; x < distanceArray[y].length; x++) {
                    if (distanceArray[y][x] == steps) {
                        fillNeighbors(x, y, steps + 1);
                    }
                }
            }
            steps++;
        }
        return distanceArray[yEnd][xEnd];
    }

    @Override
    public Integer solve2() {
        readMaze();
        readOuterPortals();
        readInnerPortals();
        fillMappings();
        levelDistanceList = new ArrayList<>();
        levelDistanceList.add(createIntArray());
        String[] start = portals.get("AA").getFirst().split(",");
        String[] end = portals.get("ZZ").getFirst().split(",");
        int xEnd = Integer.parseInt(end[0]);
        int yEnd = Integer.parseInt(end[1]);
        levelDistanceList.getFirst()[Integer.parseInt(start[1])][Integer.parseInt(start[0])] = 0;
        int steps = 0;
        while (levelDistanceList.getFirst()[yEnd][xEnd] == Integer.MAX_VALUE) {
            for (int level = 0; level < levelDistanceList.size(); level++) {
                int[][] arr = levelDistanceList.get(level);
                for (int y = 0; y < arr.length; y++) {
                    for (int x = 0; x < arr[y].length; x++) {
                        if (arr[y][x] == steps) {
                            fillNeighbors(x, y, level, steps + 1);
                        }
                    }
                }
            }
            steps++;
        }
        return levelDistanceList.getFirst()[yEnd][xEnd];
    }

    private int[][] createIntArray() {
        int[][] arr = new int[maze.length][maze[0].length];
        for (int[] line : arr) {
            Arrays.fill(line, Integer.MAX_VALUE);
        }
        return arr;
    }

    private void readMaze() {
        maze = new char[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            maze[i] = input.get(i).toCharArray();
        }
    }

    private void readOuterPortals() {
        portals = new HashMap<>();
        for (int x = 0; x < maze[0].length; x++) {
            if (maze[0][x] != ' ') {
                String portal = "" + maze[0][x] + maze[1][x];
                String coords = x + "," + 2;
                portals.computeIfAbsent(portal, p -> new ArrayList<>()).add(coords);
            }
        }
        for (int y = 0; y < maze.length; y++) {
            if (maze[y][0] != ' ') {
                String portal = "" + maze[y][0] + maze[y][1];
                String coords = 2 + "," + y;
                portals.computeIfAbsent(portal, p -> new ArrayList<>()).add(coords);
            }
        }
        int yMax = maze.length - 1;
        for (int x = 0; x < maze[yMax].length; x++) {
            if (maze[yMax][x] != ' ') {
                String portal = "" + maze[yMax - 1][x] + maze[yMax][x];
                String coords = x + "," + (yMax - 2);
                portals.computeIfAbsent(portal, p -> new ArrayList<>()).add(coords);
            }
        }
        int xMax = maze[yMax].length - 1;
        for (int y = 0; y < maze.length; y++) {
            if (maze[y][xMax] != ' ') {
                String portal = "" + maze[y][xMax - 1] + maze[y][xMax];
                String coords = (xMax - 2) + "," + y;
                portals.computeIfAbsent(portal, p -> new ArrayList<>()).add(coords);
            }
        }
    }

    private void readInnerPortals() {
        int xMin = 2;
        int yMin = 2;
        while (maze[yMin][xMin] != ' ') {
            xMin++;
            if (xMin > maze[yMin].length - 3) {
                yMin++;
                xMin = 2;
            }
        }
        int xMax = xMin;
        while (maze[yMin][xMax] != '#') {
            if (maze[yMin][xMax] != ' ') {
                String portal = "" + maze[yMin][xMax] + maze[yMin + 1][xMax];
                String coords = xMax + "," + (yMin - 1);
                portals.computeIfAbsent(portal, p -> new ArrayList<>()).add(coords);
            }
            xMax++;
        }
        xMax--;
        int yMax = yMin;
        while (maze[yMax][xMin] != '#') {
            if (maze[yMax][xMin] != ' ') {
                String portal = "" + maze[yMax][xMin] + maze[yMax][xMin + 1];
                String coords = (xMin - 1) + "," + yMax;
                portals.computeIfAbsent(portal, p -> new ArrayList<>()).add(coords);
            }
            yMax++;
        }
        yMax--;
        for (int x = xMin; x < xMax; x++) {
            if (maze[yMax][x] != ' ') {
                String portal = "" + maze[yMax - 1][x] + maze[yMax][x];
                String coords = x + "," + (yMax + 1);
                portals.computeIfAbsent(portal, p -> new ArrayList<>()).add(coords);
            }
        }
        for (int y = yMin; y < yMax; y++) {
            if (maze[y][xMax] != ' ') {
                String portal = "" + maze[y][xMax - 1] + maze[y][xMax];
                String coords = (xMax + 1) + "," + y;
                portals.computeIfAbsent(portal, p -> new ArrayList<>()).add(coords);
            }
        }
    }

    private void fillMappings() {
        outwardPortals = new HashMap<>();
        inwardPortals = new HashMap<>();
        for (List<String> coords : portals.values()) {
            if (coords.size() == 2) {
                outwardPortals.put(coords.get(0), coords.get(1));
                inwardPortals.put(coords.get(1), coords.get(0));
            }
        }
    }

    private void fillNeighbors(int x, int y, int steps) {
        fillOnLevel(x, y, steps, distanceArray);
        String coords = x + "," + y;
        if (inwardPortals.containsKey(coords)) {
            String[] otherCoords = inwardPortals.get(coords).split(",");
            distanceArray[Integer.parseInt(otherCoords[1])][Integer.parseInt(otherCoords[0])] = steps;
        }
        if (outwardPortals.containsKey(coords)) {
            String[] otherCoords = outwardPortals.get(coords).split(",");
            distanceArray[Integer.parseInt(otherCoords[1])][Integer.parseInt(otherCoords[0])] = steps;
        }
    }

    private void fillOnLevel(int x, int y, int steps, int[][] arr) {
        if (maze[y - 1][x] == '.' && arr[y - 1][x] > steps) {
            arr[y - 1][x] = steps;
        }
        if (maze[y + 1][x] == '.' && arr[y + 1][x] > steps) {
            arr[y + 1][x] = steps;
        }
        if (maze[y][x - 1] == '.' && arr[y][x - 1] > steps) {
            arr[y][x - 1] = steps;
        }
        if (maze[y][x + 1] == '.' && arr[y][x + 1] > steps) {
            arr[y][x + 1] = steps;
        }
    }

    private void fillNeighbors(int x, int y, int level, int steps) {
        int[][] arr = levelDistanceList.get(level);
        fillOnLevel(x, y, steps, arr);
        String coords = x + "," + y;
        if (outwardPortals.containsKey(coords) && level != 0) { // going UP
            String[] otherCoords = outwardPortals.get(coords).split(",");
            int[][] arrUp = levelDistanceList.get(level - 1);
            arrUp[Integer.parseInt(otherCoords[1])][Integer.parseInt(otherCoords[0])] = steps;
        }
        if (inwardPortals.containsKey(coords)) { // going DOWN
            String[] otherCoords = inwardPortals.get(coords).split(",");
            if (levelDistanceList.size() - 1 < level + 1) {
                int[][] arrUp = createIntArray();
                levelDistanceList.add(arrUp);
                arrUp[Integer.parseInt(otherCoords[1])][Integer.parseInt(otherCoords[0])] = steps;
            } else {
                int[][] arrUp = levelDistanceList.get(level + 1);
                arrUp[Integer.parseInt(otherCoords[1])][Integer.parseInt(otherCoords[0])] = steps;
            }
        }
    }
}
