package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

import static nl.erends.advent.util.Util.ASCII_OFFSET;

/**
 * <h1>--- Day 3: Gear Ratios ---</h1>
 * <p>You and the Elf eventually reach a gondola lift station, he says the
 * gondola lift will take you up to the water source. The engineer explains that
 * an engine part seems to be missing from the engine, but nobody can figure out
 * which one. If you can add up all the part numbers in the engine schematic, it
 * should be easy to work out which part is missing. The missing part isn't the
 * only issue. One of the gears in the engine is wrong. What is the sum of all
 * of the gear ratios in your engine schematic?</p>
 * <p><a href="https://adventofcode.com/2023/day/3">2023 Day 3</a></p>
 */
public class Day03 extends AbstractProblem<List<String>,  Integer> {

    char[][] grid;
    private List<Part> parts;

    public static void main(String[] args) {
        new Day03().setAndSolve(Util.readInput(2023, 3));
    }

    @Override
    protected Integer solve1() {
        grid = new char[input.size()][];
        for (int y = 0; y < input.size(); y++) {
            grid[y] = input.get(y).toCharArray();
        }
        parts = new ArrayList<>();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (Character.isDigit(grid[y][x])) {
                    Part part = new Part(x, y);
                    parts.add(part);
                    x += part.length;
                }
            }
        }
        return parts.stream()
                .filter(p -> p.connected)
                .mapToInt(p -> p.value)
                .sum();
    }

    @Override
    public Integer solve2() {
        int ratios = 0;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == '*') {
                    int[] c = new int[]{y, x};
                    List<Part> gears = parts.stream().filter(p -> p.sX == c[1] && p.sY == c[0]).toList();
                    if (gears.size() == 2) {
                        ratios += gears.get(0).value * gears.get(1).value;
                    }
                }
            }
        }
        return ratios;
    }

    private class Part {

        int x;
        int y;
        int value;
        int length = 1;
        boolean connected;
        int sX;
        int sY;

        private Part(int x, int y) {
            this.x = x;
            this.y = y;
            this.value = grid[y][x] - ASCII_OFFSET;
            while (x + 1 < grid[y].length && Character.isDigit(grid[y][x + 1])) {
                length++;
                this.value *= 10;
                x++;
                this.value += grid[y][x] - ASCII_OFFSET;
            }
            for (int dsX = this.x - 1; dsX <= this.x + length; dsX++) {
                char s = getGrid(this.y - 1, dsX);
                if (s != '.' && !Character.isDigit(s)) {
                    connected = true;
                    sX = dsX;
                    sY = y - 1;
                    return;
                }
                s = getGrid(this.y + 1, dsX);
                if (s != '.' && !Character.isDigit(s)) {
                    connected = true;
                    sX = dsX;
                    sY = y + 1;
                    return;
                }
            }
            char s = getGrid(this.y, this.x - 1);
            if (s != '.' && !Character.isDigit(s)) {
                connected = true;
                sX = this.x - 1;
                sY = y;
                return;
            }
            s = getGrid(this.y, this.x + length);
            if (s != '.' && !Character.isDigit(s)) {
                connected = true;
                sX = this.x + length;
                sY = y;
            }
        }

        private char getGrid(int y, int x) {
            try {
                return grid[y][x];
            } catch (ArrayIndexOutOfBoundsException e) {
                return '.';
            }
        }
    }
}
