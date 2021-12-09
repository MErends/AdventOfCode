package nl.erends.advent.year2021;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Collections;
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
    private int[][] basins;
    
    @Override
    protected Integer solve1() {
        List<Integer> basinList = new ArrayList<>();
        grid = new int[input.size()][input.get(0).length()];
        basins = new int[input.size()][input.get(0).length()];
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                grid[y][x] = Integer.parseInt("" + input.get(y).charAt(x));
                basins[y][x] = grid[y][x];
            }
        }
        int totalRisk = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                 if (isMinimum(x, y)) {
                     totalRisk += grid[y][x] + 1;
                     basinList.add(calculateBasins(x, y));
                 }
             }
         }
        Collections.sort(basinList);
        answer2 = basinList.stream()
                .mapToInt(i -> i)
                .skip(basinList.size() - 3L)
                .reduce(1, (b1, b2) -> b1 * b2);
         return totalRisk;
    }
    
    private boolean isMinimum(int x, int y) {
        int value = grid[y][x];
        if (x > 0 && grid[y][x - 1] <= value) { // left
            return false;
        }
        if (x < grid[y].length - 1 && grid[y][x + 1] <= value) { // right
            return false;
        }
        if (y > 0 && grid[y - 1][x] <= value) { // up
            return false;
        }
        return y >= grid.length - 1 || grid[y + 1][x] > value; // down
    }
    
    private int calculateBasins(int x, int y) {
        if (x < 0 || y < 0 || y == grid.length || x == grid[y].length) {
            return 0;
        }
        if (basins[y][x] == 9) {
            return 0;
        } else {
            basins[y][x] = 9;
            return 1 + calculateBasins(x + 1, y)
                    + calculateBasins(x - 1, y)
                    + calculateBasins(x, y + 1)
                    + calculateBasins(x, y - 1);
        }
        
    }
}
