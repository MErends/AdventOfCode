package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Timer;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * <h1>--- Day 23: A Long Walk ---</h1>
 * <p>Work in progress</p>
 * <p><a href="https://adventofcode.com/2023/day/23">2023 Day 23</a></p>
 */
public class Day23 extends AbstractProblem<List<String>, Number> {

    char[][] grid;
    int longestPath = 0;
    boolean slippery = true;

    void main() {
        new Day23().setAndSolve(Util.readInput(2023, 23));
    }

    @Override
    protected Number solve1() {
        return solvePuzzle();
    }

    @Override
    public Number solve2() {
        slippery = false;
        return solvePuzzle();
    }

    private Integer solvePuzzle() {
        grid = new char[input.size()][];
        for (int y = 0; y < input.size(); y++) {
            grid[y] = input.get(y).toCharArray();
        }
        List<Path> paths = new LinkedList<>();
        paths.add(new Path());
        while (!paths.isEmpty()) {
            Timer.tick(paths.size());
            Path path = paths.removeFirst();
            paths.addAll(path.resolvePath());
        }
        return longestPath;
    }

    private class Path {

        int steps = 0;
        int x = 1;
        int y = 0;
        final List<String> intersectionsSeen;
        Direction d;

        Path() {
            intersectionsSeen = new ArrayList<>();
            d = Direction.DOWN;
        }

        Path(Path parent, Direction newD) {
            this.intersectionsSeen = new ArrayList<>(parent.intersectionsSeen);
            this.steps = parent.steps + 1;
            this.x = parent.x + newD.dx();
            this.y = parent.y + newD.dy();
            this.d = newD;
        }


        List<Path> resolvePath() {
            List<Path> nextPaths = new ArrayList<>();
            while (true) {
                if (y == grid.length - 1) {
//                    LOG.info("Got at end with steps: {}", steps);
                    longestPath = Math.max(longestPath, steps);
                    return nextPaths;
                }
                List<Direction> possibleDirections = new ArrayList<>();
                for (Direction newD : Direction.values()) {
                    int nextX = x + newD.dx();
                    int nextY = y + newD.dy();
                    if (nextY != -1 && grid[nextY][nextX] != '#') {
                        possibleDirections.add(newD);
                    }
                }
                switch (d) {

                    case UP -> possibleDirections.remove(Direction.DOWN);
                    case DOWN ->  possibleDirections.remove(Direction.UP);
                    case LEFT ->  possibleDirections.remove(Direction.RIGHT);
                    case RIGHT ->  possibleDirections.remove(Direction.LEFT);
                }
                if (slippery) {
                    switch (grid[y][x]) {
                        case '<' -> possibleDirections.removeIf(newD -> newD != Direction.LEFT);
                        case '^' -> possibleDirections.removeIf(newD -> newD != Direction.UP);
                        case 'v' -> possibleDirections.removeIf(newD -> newD != Direction.DOWN);
                        case '>' -> possibleDirections.removeIf(newD -> newD != Direction.RIGHT);
                        default -> {
                            // We good
                        }
                    }
                }
                if (possibleDirections.size() == 1) {
                    Direction newD = possibleDirections.getFirst();
                    x += newD.dx();
                    y += newD.dy();
                    steps++;
                    d = newD;
                } else {
                    String coord = x + "," + y;
                    if (!intersectionsSeen.contains(coord)) {
                        intersectionsSeen.add(coord);
                        for (Direction newD : possibleDirections) {
                            nextPaths.add(new Path(this, newD));
                        }
                    }
                    break;
                }
            }
            return nextPaths;
        }

    }

    char[][] cloneGrid(char[][] other) {
        char[][] newGrid = new char[other.length][];
        for (int y = 0; y < other.length; y++) {
            newGrid[y] = Arrays.copyOf(other[y], other[y].length);
        }
        return newGrid;
    }

}
