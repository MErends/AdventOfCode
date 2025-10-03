package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Coord;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import static nl.erends.advent.util.Direction.RIGHT;
import static nl.erends.advent.util.Util.ASCII_OFFSET;

/**
 * <h1>--- Day 17: Clumsy Crucible ---</h1>
 * <p>The lava starts flowing rapidly once the Lava Production Facility is
 * operational. As you leave, the reindeer offers you a parachute, allowing you
 * to quickly reach Gear Island. Lavaducts will eventually carry the lava
 * throughout the city, but to make use of it immediately, Elves are loading it
 * into large crucibles on wheels. The crucibles are top-heavy and pushed by
 * hand. Unfortunately, the crucibles become very difficult to steer at high
 * speeds, and so it can be hard to go in a straight line for very long.
 * Directing the crucible from the lava pool to the machine parts factory, but
 * not moving more than three consecutive blocks in the same direction, what is
 * the least heat loss it can incur?</p>
 * <p><a href="https://adventofcode.com/2023/day/17">2023 Day 17</a></p>
 */
public class Day17 extends AbstractProblem<List<String>, Number> {

    int[][] cityGrid;
    int[][] heuristicGrid;
    Map<Coord, Map<Direction, Map<Integer, Path>>> bestPaths;
    int minStreak = 0;
    int maxStreak = 3;

    void main() {
        new Day17().setAndSolve(Util.readInput(2023, 17));
    }

    @Override
    protected Number solve1() {
        return solvePuzzle();
    }

    @Override
    public Number solve2() {
        minStreak = 4;
        maxStreak = 10;
        return solvePuzzle();
    }

    private Number solvePuzzle() {
        int answer = Integer.MAX_VALUE;
        bestPaths = new HashMap<>();
        cityGrid = new int[input.size()][];
        for (int y = 0; y < input.size(); y++) {
            cityGrid[y] = new int[input.get(y).length()];
            for (int x = 0; x < input.get(y).length(); x++) {
                cityGrid[y][x] = input.get(y).charAt(x) - ASCII_OFFSET;
            }
        }
        createHeuristicGrid();
        List<Path> paths = new ArrayList<>();
        paths.add(new Path());
        while (!paths.isEmpty()) {
            paths.sort(Comparator.comparingInt(Path::getExpectedLoss));
            Path bestPath = paths.removeFirst();
            if (bestPath.location.y() == cityGrid.length - 1
                    && bestPath.location.x() == cityGrid[cityGrid.length - 1].length - 1
                    && bestPath.streak >= minStreak) {
                answer = bestPath.heatLoss;
                break;
            } else {
                paths.addAll(bestPath.getNextPaths());
            }
        }
        return answer;
    }

    void createHeuristicGrid() {
        heuristicGrid = new int[cityGrid.length][];
        for (int y = 0; y < cityGrid.length; y++) {
            heuristicGrid[y] = new int[cityGrid[y].length];
            Arrays.fill(heuristicGrid[y], Integer.MAX_VALUE);
        }
        List<Coord> coords = new ArrayList<>();
        coords.add(Coord.of(cityGrid[0].length - 1, cityGrid.length - 1));
        heuristicGrid[cityGrid.length - 1][cityGrid[0].length - 1] = 0;
        while (!coords.isEmpty()) {
            coords.sort((c1, c2) -> heuristicGrid[c1.y()][c1.x()] - heuristicGrid[c2.y()][c2.x()]);
            Coord c = coords.removeFirst();
            Coord n = c.addDirection(Direction.UP);
            if (validCoord(n) && heuristicGrid[n.y()][n.x()] == Integer.MAX_VALUE) {
                heuristicGrid[n.y()][n.x()] = heuristicGrid[c.y()][c.x()] + cityGrid[c.y()][c.x()];
                coords.add(n);
            }
            n = c.addDirection(Direction.DOWN);
            if (validCoord(n) && heuristicGrid[n.y()][n.x()] == Integer.MAX_VALUE) {
                heuristicGrid[n.y()][n.x()] = heuristicGrid[c.y()][c.x()] + cityGrid[c.y()][c.x()];
                coords.add(n);
            }
            n = c.addDirection(Direction.LEFT);
            if (validCoord(n) && heuristicGrid[n.y()][n.x()] == Integer.MAX_VALUE) {
                heuristicGrid[n.y()][n.x()] = heuristicGrid[c.y()][c.x()] + cityGrid[c.y()][c.x()];
                coords.add(n);
            }
            n = c.addDirection(RIGHT);
            if (validCoord(n) && heuristicGrid[n.y()][n.x()] == Integer.MAX_VALUE) {
                heuristicGrid[n.y()][n.x()] = heuristicGrid[c.y()][c.x()] + cityGrid[c.y()][c.x()];
                coords.add(n);
            }
        }
    }

    boolean validPoint(int x, int y) {
        return y >= 0 && y < input.size() && x >= 0 && x < input.get(y).length();
    }

    boolean validCoord(Coord coord) {
        return validPoint(coord.x(), coord.y());
    }

    class Path {
        final Direction d;
        final List<Coord> history;
        final List<Direction> pathList;
        final Coord location;
        final int heatLoss;
        final int streak;
        int expectedLoss = -1;

        Path() {
            d = RIGHT;
            history = new ArrayList<>();
            pathList = new ArrayList<>();
            streak = 0;
            location = Coord.ZERO;
            heatLoss = 0;
        }

        Path(Path parent, Coord newCoord, Direction newD) {
            this.d = newD;
            this.history = new ArrayList<>(parent.history);
            this.history.add(newCoord);
            this.pathList = new ArrayList<>(parent.pathList);
            this.pathList.add(newD);
            location = newCoord;
            this.heatLoss = parent.heatLoss + cityGrid[location.y()][location.x()];
            if (parent.d == this.d) {
                this.streak = parent.streak + 1;
            } else {
                this.streak = 1;
            }
        }

        int getExpectedLoss() {
            if (expectedLoss == -1) {
                expectedLoss = heatLoss + heuristicGrid[location.y()][location.x()];
            }
            return expectedLoss;
        }

        List<Path> getNextPaths() {
            List<Path> nextPaths = new ArrayList<>();
            if (streak < maxStreak) {
                Coord newLocation = location.addDirection(d);
                if (!history.contains(newLocation) && validCoord(newLocation)) {
                    Path newPath = new Path(this, newLocation, d);
                    Optional<Path> bestPath = getBestPath(newPath);
                    if (bestPath.isEmpty() || bestPath.get().heatLoss > newPath.heatLoss) {
                        addBestPath(newPath);
                        nextPaths.add(newPath);
                    }
                }
            }
            if (streak >= minStreak) {
                Coord newLocation = location.addDirection(d.turnLeft());
                if (!history.contains(newLocation) && validCoord(newLocation)) {
                    Path newPath = new Path(this, newLocation, d.turnLeft());
                    Optional<Path> bestPath = getBestPath(newPath);
                    if (bestPath.isEmpty() || bestPath.get().heatLoss > newPath.heatLoss) {
                        addBestPath(newPath);
                        nextPaths.add(newPath);
                    }
                }
                newLocation = location.addDirection(d.turnRight());
                if (!history.contains(newLocation) && validCoord(newLocation)) {
                    Path newPath = new Path(this, newLocation, d.turnRight());
                    Optional<Path> bestPath = getBestPath(newPath);
                    if (bestPath.isEmpty() || bestPath.get().heatLoss > newPath.heatLoss) {
                        addBestPath(newPath);
                        nextPaths.add(newPath);
                    }
                }
            }
            return nextPaths;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Path path = (Path) o;
            return streak == path.streak && d == path.d && Objects.equals(location, path.location);
        }

        @Override
        public int hashCode() {
            return Objects.hash(d, location, streak);
        }

        @Override
        public String toString() {
            return "Path{" +
                    "location=" + location +
                    ", d=" + d +
                    ", streak=" + streak +
                    '}';
        }
    }

    Optional<Path> getBestPath(Path path) {
        try {
            return Optional.of(bestPaths.get(path.location).get(path.d).get(path.streak));
        } catch (NullPointerException e) {
            return Optional.empty();
        }
    }

    void addBestPath(Path path) {
        Map<Direction, Map<Integer, Path>> coordMap = bestPaths.computeIfAbsent(path.location, k -> new HashMap<>());
        Map<Integer, Path> dMap = coordMap.computeIfAbsent(path.d, _ -> new HashMap<>());
        dMap.put(path.streak, path);
    }

}
