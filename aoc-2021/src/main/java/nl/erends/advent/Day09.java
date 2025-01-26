package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 9: Smoke Basin ---
 * <p>The submarine generates a heightmap of the floor of the nearby caves for
 * you. Smoke flows to the lowest point of the area it's in. Your first goal is
 * to find the low points, the locations that are lower than any of its
 * adjacent locations. Next, you need to find the largest basins so you know
 * what areas are most important to avoid.
 * <p><a href="https://adventofcode.com/2021/day/9">2021 Day 9</a>
 */
public class Day09 extends AbstractProblem<List<String>, Integer> {

    public static void main(String[] args) {
        new Day09().setAndSolve(Util.readInput(2021, 9));
    }
    
    private int[][] grid;
    private int[][] basinGrid;
    
    @Override
    protected Integer solve1() {
        
        List<Integer> basins = new ArrayList<>();
        grid = new int[input.size()][input.getFirst().length()];
        basinGrid = new int[input.size()][input.getFirst().length()];
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                grid[y][x] = Integer.parseInt("" + input.get(y).charAt(x));
                basinGrid[y][x] = grid[y][x];
            }
        }
        
        int totalRisk = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                 if (isMinimum(x, y)) {
                     totalRisk += grid[y][x] + 1;
                     basins.add(calculateBasin(x, y));
                 }
             }
         }
        
        answer2 = basins.stream()
                .sorted()
                .skip(basins.size() - 3L)
                .reduce(1, (b1, b2) -> b1 * b2);
         return totalRisk;
    }
    
    private boolean isMinimum(int x, int y) {
        int value = grid[y][x];
        return getValue(x - 1, y) > value
                && getValue(x + 1, y) > value
                && getValue(x, y - 1) > value
                && getValue(x, y + 1) > value;
    }
    
    private int getValue(int x, int y) {
        if (x < 0 || y < 0 || y == grid.length || x == grid[y].length) {
            return 9;
        } else {
            return grid[y][x];
        }
    }
    
    private int calculateBasin(int x, int y) {
        if (x < 0 || y < 0 || y == grid.length || x == grid[y].length) {
            return 0;
        }
        if (basinGrid[y][x] == 9) {
            return 0;
        } else {
            basinGrid[y][x] = 9;
            return 1 + calculateBasin(x + 1, y)
                    + calculateBasin(x - 1, y)
                    + calculateBasin(x, y + 1)
                    + calculateBasin(x, y - 1);
        }
    }
}
