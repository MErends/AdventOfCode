package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Point;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiPredicate;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * --- Day 12: Hill Climbing Algorithm ---
 * <p>You try contacting the Elves using your handheld device, but the river
 * you're following must be too low to get a decent signal. You ask the device
 * for a heightmap of the surrounding area (your puzzle input). What is the
 * fewest steps required to move from your current position to the location that
 * should get the best signal?
 * <p><a href="https://adventofcode.com/2022/day/12">2022 Day 12</a>
 */
public class Day12 extends AbstractProblem<List<String>, Integer> {

    private char[][] maze;
    private final Point[] end = new Point[1];
    void main() {
        new Day12().setAndSolve(Util.readInput(2022, 12));
    }

    @Override
    protected Integer solve1() {
        maze = new char[input.size()][];
        Point start = null;
        for (int y = 0; y < input.size(); y++) {
            maze[y] = input.get(y).toCharArray();
        }
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[y].length; x++) {
                if (maze[y][x] == 'S') {
                    start = new Point(x, y);
                    maze[y][x] = 'a';
                } else if (maze[y][x] == 'E') {
                    end[0] = new Point(x, y);
                    maze[y][x] = 'z';
                }
            }
        }
        return solveMaze(start, p -> p.equals(end[0]), (p1, p2) -> maze[p1.y()][p1.x()] + 1 >= maze[p2.y()][p2.x()]);
    }

    @Override
    public Integer solve2() {
        return solveMaze(end[0], p -> maze[p.y()][p.x()] == 'a', (p1, p2) -> maze[p1.y()][p1.x()] - 1 <= maze[p2.y()][p2.x()]);
    }

    private int solveMaze(Point start, Predicate<Point> winCondition, BiPredicate<Point, Point> stepCondition) {
        int[][] distances = new int[maze.length][maze[0].length];
        for (int[] line : distances) {
            Arrays.fill(line, Integer.MAX_VALUE);
        }
        int currentDistance = 0;
        List<Point> currentPoints = new ArrayList<>();
        currentPoints.add(start);
        distances[start.y()][start.x()] = currentDistance;
        while (!currentPoints.isEmpty()) {
            Set<Point> nextPoints = new HashSet<>();
            for (Point currentPoint : currentPoints) {
                List<Point> neighbors = Stream.of(Direction.values())
                        .map(d -> new Point(currentPoint.x() + d.dx(), currentPoint.y() + d.dy()))
                        .filter(p -> p.x() >= 0 && p.y() >= 0 && p.y() < maze.length && p.x() < maze[p.y()].length)
                        .toList();
                for (Point neighbor : neighbors) {
                    if (stepCondition.test(currentPoint, neighbor) && distances[neighbor.y()][neighbor.x()] > currentDistance) {
                        if (winCondition.test(neighbor)) {
                            return currentDistance + 1;
                        }
                        distances[neighbor.y()][neighbor.x()] = currentDistance + 1;
                        nextPoints.add(neighbor);
                    }
                }
            }
            currentPoints.clear();
            currentPoints.addAll(nextPoints);
            currentDistance++;
        }
        throw new IllegalStateException();
    }
}
