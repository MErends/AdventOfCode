package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import java.util.stream.IntStream;

/**
 * <h1>--- Day 13: Point of Incidence ---</h1>
 * <p>With your help, the hot springs team locates an appropriate spring which
 * launches you neatly and precisely up to the edge of Lava Island. After a
 * while, you make your way to a nearby cluster of mountains only to discover
 * that the valley between them is completely full of large mirrors. Find the
 * line of reflection in each of the patterns in your notes. What number do you
 * get after summarizing all of your notes?</p>
 * <p><a href="https://adventofcode.com/2023/day/13">2023 Day 13</a></p>
 */
public class Day13 extends AbstractProblem<List<String>, Number> {


    public static void main(String[] args) {
        new Day13().setAndSolve(Util.readInput(2023, 13));
    }

    @Override
    protected Number solve1() {
        return solve(false);
    }

    @Override
    public Number solve2() {
        return solve(true);
    }

    protected Number solve(boolean smudged) {
        List<String> lines = new ArrayList<>(input);
        lines.add("");
        int sum = 0;
        while (!lines.isEmpty()) {
            List<String> gridLines = new ArrayList<>();
            while (!lines.get(0).isEmpty()) {
                gridLines.add(lines.remove(0));
            }
            lines.remove(0);
            char[][] grid = new char[gridLines.size()][];
            for (int y = 0; y < gridLines.size(); y++) {
                grid[y] = gridLines.get(y).toCharArray();
            }
            OptionalInt rowSym = IntStream.range(1, grid.length)
                    .filter(rowNum -> rowSymm(grid, rowNum, !smudged))
                    .findFirst();
            sum += 100 * rowSym.orElse(0);

            OptionalInt colSym = IntStream.range(1, grid[0].length)
                    .filter(colNum -> colSymm(grid, colNum, !smudged))
                    .findFirst();
            sum += colSym.orElse(0);
        }
        return sum;
    }

    private boolean rowSymm(char[][] grid, int rowNum, boolean smudgeFixed) {
        int lowRow = rowNum - 1;
        int highRow = rowNum;
        while (lowRow >= 0 && highRow < grid.length) {
            for (int x = 0; x < grid[lowRow].length; x++) {
                if (grid[lowRow][x] != grid[highRow][x]) {
                    if (smudgeFixed) {
                        return false;
                    }
                    smudgeFixed = true;
                }
            }
            lowRow--;
            highRow++;
        }
        return smudgeFixed;
    }


    private boolean colSymm(char[][] grid, int colNum, boolean smudgeFixed) {
        int lowCol = colNum - 1;
        int highCol = colNum;
        while (lowCol >= 0 && highCol < grid[0].length) {
            for (char[] row : grid) {
                if (row[lowCol] != row[highCol]) {
                    if (smudgeFixed) {
                        return false;
                    }
                    smudgeFixed = true;
                }
            }
            lowCol--;
            highCol++;
        }
        return smudgeFixed;
    }
}
