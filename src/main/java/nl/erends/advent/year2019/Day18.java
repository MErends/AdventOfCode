package nl.erends.advent.year2019;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Point;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

public class Day18 extends AbstractProblem<List<String>, Integer> {

    private Point start;
    private State winState = new State(null, Integer.MAX_VALUE);
    private int keyCount = 0;
    private final Queue<State> states = new LinkedList<>();
    private char[][] maze;

    public static void main(String[] args) {
        new Day18().setAndSolve(Util.readInput(2019, 18));
    }

    @Override
    public Integer solve1() {
        readMaze();
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[y].length; x++) {
                if (maze[y][x] == '@') {
                    start = new Point(x, y);
                    maze[y][x] = '.';
                } else if (Character.isLowerCase(maze[y][x])) {
                    keyCount++;
                }
            }
        }
        states.add(new State(Set.of(start), 0));
        return findWinState().steps;
    }

    @Override
    public Integer solve2() {
        readMaze();
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[y].length; x++) {
                if (maze[y][x] == '@') {
                    start = new Point(x, y);
                    maze[y][x] = '#';
                }
            }
        }
        for (Direction d : Direction.values()) {
            maze[start.y() + d.dy()][start.x() + d.dx()] = '#';
        }
        Set<Point> startSet = new HashSet<>();
        startSet.add(new Point(start.x() + 1, start.y() + 1));
        startSet.add(new Point(start.x() + 1, start.y() - 1));
        startSet.add(new Point(start.x() - 1, start.y() + 1));
        startSet.add(new Point(start.x() - 1, start.y() - 1));
        states.add(new State(startSet, 0));
        return findWinState().steps;
    }

    private State findWinState() {
        while (!states.isEmpty()) {
            State nextState = states.remove();
            List<State> duplicates = states.stream()
                    .filter(s -> s.points.equals(nextState.points) && s.keySet.equals(nextState.keySet))
                    .toList();
            boolean stateGood = true;
            for (State duplicate : duplicates) {
                if (duplicate.steps < nextState.steps) {
                    stateGood = false;
                } else {
                    states.remove(duplicate);
                }
            }
            if (stateGood) {
                resolveState(nextState);
            }
        }
        return winState;
    }
    
    private void readMaze() {
        maze = new char[input.size()][];
        for (int y = 0; y < input.size(); y++) {
            maze[y] = input.get(y).toCharArray();
        }
    }

    private void resolveState(State oldState) {
        if (oldState.steps >= winState.steps) {
            return;
        }
        if (oldState.keySet.size() == keyCount) {
            winState = oldState;
            return;
        }
        int[][] distanceMaze = new int[maze.length][maze[0].length];
        for (int[] line : distanceMaze) {
            Arrays.fill(line, Integer.MAX_VALUE);
        }
        for (Point point : oldState.points) {
            int currentDistance = 0;
            List<Point> findNeighborsOf = new ArrayList<>();
            findNeighborsOf.add(point);
            distanceMaze[point.y()][point.x()] = currentDistance;
            while (!findNeighborsOf.isEmpty()) {
                currentDistance++;
                Set<Point> newNeighbors = new HashSet<>();
                for (Point neighbor : findNeighborsOf) {
                    for (Direction d : Direction.values()) {
                        newNeighbors.add(new Point(neighbor.x() + d.dx(), neighbor.y() + d.dy()));
                    }
                }
                findNeighborsOf.clear();
                for (Point neighbor : newNeighbors) {
                    int x = neighbor.x();
                    int y = neighbor.y();
                    if (distanceMaze[y][x] > currentDistance &&
                            (maze[y][x] == '.'
                                    || oldState.keyList.contains(maze[y][x])
                                    || oldState.keyList.contains(Character.toLowerCase(maze[y][x])))) {
                        findNeighborsOf.add(neighbor);
                        distanceMaze[y][x] = currentDistance;
                    } else if (Character.isLowerCase(maze[y][x]) && !oldState.keyList.contains(maze[y][x])) {
                        Set<Point> newPoints = new HashSet<>(oldState.points);
                        newPoints.remove(point);
                        newPoints.add(neighbor);
                        states.add(new State(newPoints, oldState.steps + currentDistance, oldState.keyList, maze[y][x]));
                    }
                }
            }
        }
    }
    private static class State {
        final Set<Point> points;
        final int steps;
        List<Character> keyList = new ArrayList<>();
        Set<Character> keySet = new HashSet<>();

        State(Set<Point> points, int steps, List<Character> keys, char newKey) {
            this.points = points;
            this.steps = steps;
            this.keyList.addAll(keys);
            this.keySet.addAll(keys);
            keyList.add(newKey);
            keySet.add(newKey);
        }

        State(Set<Point> points, int steps) {
            this.points = points;
            this.steps = steps;
        }
    }
}
