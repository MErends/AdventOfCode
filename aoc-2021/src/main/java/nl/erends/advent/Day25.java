package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.Arrays;
import java.util.List;

/**
 * --- Day 25: Sea Cucumber ---
 * <p>At last, you touch down on the seafloor. Unfortunately, it's completely
 * covered by two large herds of sea cucumbers, and there isn't an open space
 * large enough for your submarine. What is the first step on which no sea
 * cucumbers move?
 * <p><a href="https://adventofcode.com/2021/day/25">2021 Day 25</a>
 */
public class Day25 extends AbstractProblem<List<String>, Integer> {
    
    private char[][] grid;
    
    static void main() {
        new Day25().setAndSolve(Util.readInput(2021, 25));
    }

    @Override
    protected Integer solve1() {
        grid = new char[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            grid[i] = input.get(i).toCharArray();
        }
        int iterations = 0;
        do {
            iterations++;
        } while (iterate());
        return iterations;
    }
    
    private boolean iterate() {
        boolean moved = false;
        char[][] newGrid = copyGrid(grid);
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                char cucumber = grid[y][x];
                int newX = (x + 1) % grid[y].length;
                if (cucumber == '>' && grid[y][newX] == '.') {
                    moved = true;
                    newGrid[y][newX] = cucumber;
                    newGrid[y][x] = '.';
                }
            }
        }
        grid = newGrid;
        newGrid = copyGrid(grid);
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                char cucumber = grid[y][x];
                int newY = (y + 1) % grid.length;
                if (cucumber == 'v' && grid[newY][x] == '.') {
                    moved = true;
                    newGrid[newY][x] = cucumber;
                    newGrid[y][x] = '.';
                }
            }
        }
        grid = newGrid;
        return moved;
    }
    
    private char[][] copyGrid(char[][] grid) {
        char[][] newGrid = new char[grid.length][];
        for (int y = 0; y < grid.length; y++) {
            newGrid[y] = Arrays.copyOf(grid[y], grid[y].length);
        }
        return newGrid;
    }
}
