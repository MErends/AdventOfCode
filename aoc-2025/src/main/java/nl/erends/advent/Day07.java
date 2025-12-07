package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.Arrays;
import java.util.List;

/**
 * <h1>--- Day 7: Laboratories ---</h1>
 * <p>Based on the large sign that says "teleporter hub", they seem to be
 * researching teleportation; you can't help but try it for yourself and step
 * onto the large yellow teleporter pad. To repair the teleporter, you first
 * need to understand the beam-splitting properties of the tachyon manifold.
 * Analyze your manifold diagram. How many times will the beam be split?</p>
 * <p><a href="https://adventofcode.com/2025/day/7">2025 Day 7</a></p>
 */
public class Day07 extends AbstractProblem<List<String>, Long> {

    void main() {
        new Day07().setAndSolve(Util.readInput(2025, 7));
    }

    @Override
    protected Long solve1() {
        long beamsSplit = 0;
        int start = input.getFirst().indexOf('S');
        char[][] grid = new char[input.size()][];
        long[][] beamWeight = new long[input.size()][input.getFirst().length()];
        for (int i = 0; i < input.size(); i++) {
            grid[i] = input.get(i).toCharArray();
        }
        grid[0][start] = '|';
        beamWeight[0][start] = 1;
        for (int y = 0; y < grid.length - 1; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == '|') {
                    if (grid[y + 1][x] == '^') {
                        grid[y + 1][x - 1] = '|';
                        beamWeight[y + 1][x - 1] += beamWeight[y][x];
                        grid[y + 1][x + 1] = '|';
                        beamWeight[y + 1][x + 1] += beamWeight[y][x];
                        beamsSplit++;
                    } else {
                        grid[y + 1][x] = '|';
                        beamWeight[y + 1][x] += beamWeight[y][x];
                    }
                }
            }
        }
        answer2 = Arrays.stream(beamWeight[beamWeight.length - 1]).sum();
        return beamsSplit;
    }
}
