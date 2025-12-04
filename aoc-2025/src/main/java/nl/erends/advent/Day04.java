package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

/**
 * <h1>--- Day 4: Printing Department ---</h1>
 * <p>You ride the escalator down to the printing department. they have lots of
 * large rolls of paper everywhere. If you can optimize the work the forklifts
 * are doing, maybe they would have time to spare to break through the wall. How
 * many rolls of paper can be accessed by a forklift?</p>
 * <p><a href="https://adventofcode.com/2025/day/4">2025 Day 4</a></p>
 */
public class Day04 extends AbstractProblem<List<String>, Integer> {

    private char[][] grid;

    void main() {
        new Day04().setAndSolve(Util.readInput(2025, 4));
    }

    @Override
    protected Integer solve1() {
        grid = new char[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            grid[i] = input.get(i).toCharArray();
        }
        int answer1 = getCountAndUpdateGrid();
        answer2 = answer1;
        while (true) {
            int rollsRemoved = getCountAndUpdateGrid();
            if (rollsRemoved > 0) {
                answer2 += rollsRemoved;
            } else {
                break;
            }
        }
        return answer1;
    }

    private int getCountAndUpdateGrid() {
        char[][] newGrid = new char[input.size()][input.size()];
        int reachableRolls = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == '@' && neighborCount(x, y) < 4) {
                    reachableRolls++;
                    newGrid[y][x] = '.';
                } else {
                    newGrid[y][x] = grid[y][x];
                }
            }
        }
        grid = newGrid;
        return reachableRolls;

    }

    private int neighborCount(int x, int y) {
        int count = 0;
        for (int dy = -1; dy <=1; dy++) {
            for (int dx = -1; dx <= 1; dx++) {
                if (dx == 0 && dy == 0) continue;
                if(getGrid(x + dx, y + dy) == '@') {
                    count++;
                }
            }
        }
        return count;
    }

    private char getGrid(int x, int y) {
        if (y < 0 || y >= grid.length || x < 0 || x >= grid[y].length) {
            return '.';
        }
        return grid[y][x];
    }
}

