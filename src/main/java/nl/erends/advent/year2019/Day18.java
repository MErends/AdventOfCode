package nl.erends.advent.year2019;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Day18 extends AbstractProblem<List<String>, Integer> {
    
    int minimalSteps = Integer.MAX_VALUE;
    String keys = "abcdefghijklmnopqrstuvwxyz";

    public static void main(String[] args) {
        new Day18().setAndSolve(Util.readInput(2019, 18));
    }

    @Override
    public Integer solve1() {
        char[][] maze = readMaze();
        int startX = -1;
        int startY = -1;
        for (int y = 0; y < maze.length && startY == -1; y++) {
            for (int x = 0; x < maze[y].length; x++) {
                if (maze[y][x] == '@') {
                    startX = x;
                    startY = y;
                    break;
                }
            }
        }
        maze[startY][startX] = '.';
        List<State> states = new ArrayList<>();
        states.add(new State(startX, startY, 0, maze, ""));
        while (minimalSteps == Integer.MAX_VALUE) {
            states.sort(Comparator.comparingDouble(s -> s.average));
            State state = states.remove(0);
            System.out.printf("%-20s\t%f%n", state.sequence, state.average);
            if (!keysLeft(state.maze)) {
                System.out.println("Found a solution: " + state.sequence + ". Steps was " + state.steps);
                minimalSteps = Math.min(minimalSteps, state.steps);
                System.out.println("Min steps now: " + minimalSteps);
            } else {
                states.addAll(getNextStates(state));
            }
        }
        return minimalSteps; // 3622 too high
    }
    
    private char[][] readMaze() {
        char[][] maze = new char[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            maze[i] = input.get(i).toCharArray();
        }
        return maze;
    }
    
    private char[][] cloneMazeWithout(char key, char[][] oldMaze) {
        char[][] newMaze = new char[oldMaze.length][oldMaze[0].length];
        for (int y = 0; y < oldMaze.length; y++) {
            for (int x = 0; x < oldMaze[y].length; x++) {
                if (oldMaze[y][x] == key || oldMaze[y][x] == Character.toUpperCase(key)) {
                    newMaze[y][x] = '.';
                } else {
                    newMaze[y][x] = oldMaze[y][x];
                }
            }
        }
        return newMaze;
    }
    
    private List<State> getNextStates(State oldState) {
        List<State> newStates = new ArrayList<>();
        if (oldState.steps > minimalSteps) {
            System.out.println("Stopped with " + oldState.sequence + " because " + oldState.steps + ">" + minimalSteps);
            return newStates;
        }
        char[][] maze = oldState.maze;
        int[][] distanceMaze = new int[maze.length][maze[0].length];
        for (int[] line : distanceMaze) {
            Arrays.fill(line, Integer.MAX_VALUE);
        }
        int currentDistance = 0;
        distanceMaze[oldState.y][oldState.x] = currentDistance;
        boolean distancesDone = false;
        while (!distancesDone) {
            distancesDone = true;
            for (int y = 0; y < maze.length; y++) {
                for (int x = 0; x < maze[y].length; x++) {
                    if (distanceMaze[y][x] == currentDistance) {
                        fillNeighbors(x, y, maze, distanceMaze, currentDistance + 1);
                        distancesDone = false;
                        if (isKey(maze[y][x])) {
                            newStates.add(new State(x, y, oldState.steps + currentDistance, cloneMazeWithout(maze[y][x], maze), oldState.sequence + maze[y][x]));
                        }
                    }
                }
            }
            currentDistance++;
        }
        return newStates;
    }
    
    private void fillNeighbors(int x, int y, char[][] maze, int[][] distanceMaze, int steps) {
        if (distanceMaze[y][x - 1] > steps && (maze[y][x - 1] == '.' || isKey(maze[y][x - 1]))) {
            distanceMaze[y][x - 1] = steps;
        }
        if (distanceMaze[y][x + 1] > steps && (maze[y][x + 1] == '.' || isKey(maze[y][x + 1]))) {
            distanceMaze[y][x + 1] = steps;
        }
        if (distanceMaze[y - 1][x] > steps && (maze[y - 1][x] == '.' || isKey(maze[y - 1][x]))) {
            distanceMaze[y - 1][x] = steps;
        }
        if (distanceMaze[y + 1][x] > steps && (maze[y + 1][x] == '.' || isKey(maze[y + 1][x]))) {
            distanceMaze[y + 1][x] = steps;
        }
    }
    
    private boolean keysLeft(char[][] maze) {
        for (char[] line : maze) {
            for (char c : line) {
                if (isKey(c)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    private boolean isKey(char c) {
        return keys.contains("" + c);
    }
    
    private static class State {
        int x;
        int y;
        int steps;
        char[][] maze;
        String sequence;
        double average;

        public State(int x, int y, int steps, char[][] maze, String sequence) {
            this.x = x;
            this.y = y;
            this.steps = steps;
            this.maze = maze;
            this.sequence = sequence;
            average = (double) steps / sequence.length();
        }
    }
}
