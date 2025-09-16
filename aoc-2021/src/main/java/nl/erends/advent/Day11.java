package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.Arrays;
import java.util.List;

import static nl.erends.advent.util.Util.ASCII_OFFSET;

/**
 * --- Day 11: Dumbo Octopus ---
 * <p>You enter a large cavern full of rare bioluminescent dumbo octopuses! Each
 * octopus slowly gains energy over time and flashes brightly for a moment when
 * its energy is full. This increases the energy level of all adjacent
 * octopuses. How many total flashes are there after 100 steps, and what is the
 * first step during which all octopuses flash?
 * <p><a href="https://adventofcode.com/2021/day/11">2021 Day 11</a>
 */
public class Day11 extends AbstractProblem<List<String>, Integer> {

    private int[][] grid;
    
    static void main() {
        new Day11().setAndSolve(Util.readInput(2021, 11));
    }
    
    @Override
    protected Integer solve1() {
        int pulseCount = 0;
        grid = new int[input.size()][];
        for (int y = 0; y < grid.length; y++) {
            grid[y] = input.get(y).chars()
                    .map(c -> c - ASCII_OFFSET)
                    .toArray();
        }
        for (int i = 1; ; i++) {
            iterate();
            int currentPulses = countPulses();
            if (i <= 100) {
                pulseCount += currentPulses;
            }
            if (currentPulses == grid.length * grid[0].length) {
                answer2 = i;
                break;
            }
        }
        return pulseCount;
    }
    
    private void iterate() {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                increaseEnergy(x, y);
            }
        }
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] >= 10) {
                    grid[y][x] = 0;
                }
            }
        }
    }
    
    private int countPulses() {
        return (int) Arrays.stream(grid)
                .flatMapToInt(Arrays::stream)
                .filter(i -> i == 0)
                .count();
    }
    
    private void increaseEnergy(int x, int y) {
        if (y <0 || y >= grid.length ||x < 0 || x >= grid[y].length) {
            return;
        }
        int energy = ++grid[y][x];
        if (energy == 10) {
            increaseEnergy(x - 1, y);
            increaseEnergy(x + 1, y);
            increaseEnergy(x, y - 1);
            increaseEnergy(x, y + 1);
            increaseEnergy(x + 1, y + 1);
            increaseEnergy(x - 1, y - 1);
            increaseEnergy(x + 1, y - 1);
            increaseEnergy(x - 1, y + 1);
        }
    }
}
