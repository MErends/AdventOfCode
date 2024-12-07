package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.Arrays;
import java.util.List;

/**
 * --- Day 18: Like a GIF For Your Yard ---
 * <p>You arrange 100x100 lights in a grid. The state a light should have next
 * is based on its current state (on or off) plus the number of neighbors that
 * are on. Given your initial configuration, how many lights are on after 100
 * steps?
 * <p><a href="https://adventofcode.com/2021/day/18">2015 Day 18</a>
 */
public class Day18 extends AbstractProblem<List<String>,Integer> {

    private int size = 0;
    private int[][] grid;
    private int iterations = 100;

    public static void main(String[] args) {
        new Day18().setAndSolve(Util.readInput(2015, 18));
    }

    @Override
    public Integer solve1() {
        readInput();
        for (int i = 0; i < iterations; i++) {
            iterate();
            if (part2) {
                lightCorners();
            }
        }
        return Arrays.stream(grid).flatMapToInt(Arrays::stream).sum();
    }

    private void readInput() {
        size = input.size();
        grid = new int[input.size()][input.get(0).length()];
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                grid[y][x] = input.get(y).charAt(x) == '#' ? 1 : 0;
            }
        }
        if (part2) {
            lightCorners();
        }
    }

    private void lightCorners() {
        grid[0][0] = 1;
        grid[0][size - 1] = 1;
        grid[size - 1][0] = 1;
        grid[size - 1][size - 1] = 1;
    }

    private void iterate() {
        int[][] newGrid = new int[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int value = grid[y][x];
                int neigbors = countNeighbors(x, y);
                if (value == 1 && (neigbors < 2 || neigbors > 3)) {
                    newGrid[y][x] = 0;
                } else if (value == 0 && neigbors == 3) {
                    newGrid[y][x] = 1;
                } else {
                    newGrid[y][x] = grid[y][x];
                }
            }
        }
        grid = newGrid;
    }

    private int countNeighbors(int x, int y) {
        return getgrid(x - 1, y - 1)
                + getgrid(x, y - 1)
                + getgrid(x + 1, y - 1)
                + getgrid(x - 1, y)
                + getgrid(x + 1, y)
                + getgrid(x - 1, y + 1)
                + getgrid(x, y + 1)
                + getgrid(x + 1, y + 1);
    }

    private int getgrid(int x, int y) {
        if (y < 0 || x < 0 || y > size - 1 || x > size - 1) {
            return 0;
        }
        return grid[y][x];
    }

    void setTestIterations(int iterations) {
        this.iterations = iterations;
    }
}
