package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Coord;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h1>--- Day 18: RAM Run ---</h1>
 * <p>Just as you're about to check out your surroundings, a program runs up to
 * you. This region of memory isn't safe! You create a list of which bytes will
 * fall (your puzzle input) in the order they'll land in your memory space.
 * Simulate the first kilobyte (1024 bytes) falling onto your memory space.
 * Afterward, what is the minimum number of steps needed to reach the exit?</p>
 * <p><a href="https://adventofcode.com/2024/day/18">2024 Day 18</a></p>
 */
public class Day18 extends AbstractProblem<List<String>, String> {

    char[][] grid;
    int[][] steps;
    int dimension = 71;
    int byteLimit = 1024;

    public static void main(String[] args) {
        new Day18().setAndSolve(Util.readInput(2024, 18));
    }

    @Override
    protected String solve1() {
        steps = new int[dimension][dimension];
        grid = new char[dimension][dimension];
        for (int y = 0; y < dimension; y++) {
            Arrays.fill(grid[y], '.');
            Arrays.fill(steps[y], Integer.MAX_VALUE);
        }
        for (int byteNum = 0; byteNum < byteLimit; byteNum++) {
            String[] byteLine = input.get(byteNum).split(",");
            grid[Integer.parseInt(byteLine[1])][Integer.parseInt(byteLine[0])] = '#';
        }
        return Integer.toString(getStepsToFinish());
    }

    @Override
    public String solve2() {
        int low = byteLimit;
        int high = input.size();
        while (low != high) {
            byteLimit = (high + low) / 2;
            if (Integer.parseInt(solve1()) == Integer.MAX_VALUE) {
                high = byteLimit;
            } else {
                low = byteLimit + 1;
            }
        }
        return input.get(high - 1);
    }

    private int getStepsToFinish() {
        for (int[] line : steps) {
            Arrays.fill(line, Integer.MAX_VALUE);
        }
        steps[0][0] = 0;
        Set<Coord> heads = Set.of(Coord.ZERO);
        int stepCount = 0;
        while (!heads.isEmpty() && steps[dimension - 1][dimension - 1] == Integer.MAX_VALUE) {
            Set<Coord> newHeads = new HashSet<>();
            for (Coord head : heads) {
                    for (Direction d : Direction.values()) {
                        Coord neighbor = head.addDirection(d);
                        if (getGrid(neighbor) != '#' && steps[neighbor.y][neighbor.x] > stepCount + 1) {
                            steps[neighbor.y][neighbor.x] = stepCount + 1;
                            newHeads.add(neighbor);
                        }
                    }
                }
            heads = newHeads;
            stepCount++;
        }
        return steps[dimension - 1][dimension - 1];
    }

    char getGrid(Coord c) {
        try {
            return grid[c.y][c.x];
        } catch (IndexOutOfBoundsException e) {
            return '#';
        }
    }

    void setTestRanges() {
        dimension = 7;
        byteLimit = 12;
    }
}
