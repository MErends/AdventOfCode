package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>--- Day 14: Parabolic Reflector Dish ---</h1>
 * <p>You reach the place where all of the mirrors were pointing: a massive
 * parabolic reflector dish attached to the side of another large mountain. If
 * you move the rocks, you can focus the dish. The platform even has a control
 * panel on the side that lets you tilt it in one of four directions! Tilt the
 * platform so that the rounded rocks all roll north. Afterward, what is the
 * total load on the north support beams?</p>
 * <p><a href="https://adventofcode.com/2023/day/14">2023 Day 14</a></p>
 */
public class Day14 extends AbstractProblem<List<String>, Number> {


    private char[][] grid;

    void main() {
        new Day14().setAndSolve(Util.readInput(2023, 14));
    }

    @Override
    protected Number solve1() {
        loadGrid();
        cycle(Direction.UP);
        return getLoad();
    }

    @Override
    public Number solve2() {
        loadGrid();
        int targetCycle = 1_000_000_000;
        Map<Integer, Integer> cycleLoadMap = new HashMap<>();
        Map<Integer, Integer> cycleHashMap = new HashMap<>();
        int cycleCount = 0;
        int hash;
        while (true) {
            cycle(Direction.UP);
            cycle(Direction.LEFT);
            cycle(Direction.DOWN);
            cycle(Direction.RIGHT);
            cycleCount += 1;
            hash = Arrays.deepHashCode(grid);
            if (cycleHashMap.containsValue(hash)) {
                break;
            }
            cycleHashMap.put(cycleCount, hash);
            cycleLoadMap.put(cycleCount, getLoad());
        }
        int loopHash = hash;
        int loopStart = cycleHashMap.entrySet().stream()
                .filter(e -> e.getValue() == loopHash)
                .findFirst().orElseThrow().getKey();
        int loopLength = cycleCount - loopStart;
        while (!cycleLoadMap.containsKey(targetCycle)) {
            targetCycle -= loopLength;
        }
        return cycleLoadMap.get(targetCycle);
    }

    private void loadGrid() {
        grid = new char[input.size()][];
        for (int y = 0; y < input.size(); y++) {
            grid[y] = input.get(y).toCharArray();
        }
    }

    private void cycle(Direction d) {
        int startY = 0;
        int dy = 1;
        int startX = 0;
        int dx = 1;
        if (d == Direction.RIGHT) {
            startX = grid[0].length - 1;
            dx *= -1;
        } else if (d == Direction.DOWN) {
            startY = grid.length - 1;
            dy *= -1;
        }
        for (int y = startY; y >= 0 && y < grid.length; y += dy) {
            for (int x = startX; x >= 0 && x < grid[y].length; x += dx) {
                if (grid[y][x] == 'O') {
                    int newY = y;
                    int newX = x;
                    while (newY + d.dy() >= 0 && newX + d.dx() >= 0 && newY + d.dy() < grid.length && newX + d.dx() < grid[newY].length && grid[newY + d.dy()][newX + d.dx()] == '.') {
                        newY += d.dy();
                        newX += d.dx();
                    }
                    grid[y][x] = '.';
                    grid[newY][newX] = 'O';
                }
            }
        }
    }

    private int getLoad() {
        int sum = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == 'O') {
                    sum += (grid.length - y);
                }
            }
        }
        return sum;
    }
}
