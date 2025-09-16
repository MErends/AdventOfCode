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
 * <h1>--- Day 21: Step Counter ---</h1>
 * <p>Work in progress</p>
 * <p><a href="https://adventofcode.com/2023/day/21">2023 Day 21</a></p>
 */
public class Day21 extends AbstractProblem<List<String>, Number> {

    private char[][] grid;
    private Coord start;

    static void main() {
        new Day21().setAndSolve(Util.readInput(2023, 21));
    }

    @Override
    protected Integer solve1() {
        int maxStep = 64;
        grid = new char[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            grid[i] = input.get(i).toCharArray();
        }
        start = null;
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == 'S') {
                    start = Coord.of(x, y);
                    grid[y][x] = '.';
                    break;
                }
            }
            if (start != null) break;
        }
        int[][] stepGrid = getStepGrid(start, 0);

        return getReachable(stepGrid, 64);
    }

    @Override
    public Number solve2() {    // 607328310777331 too low
                                // 607334315041377 too low
        int maxStep = 26_501_365;
        long answer = 0;
        int dimension = grid.length;
        int[][] evenGrid = getStepGrid(start, 0);
        int[][] oddGrid = getStepGrid(start, 1);
        int evenGridCount = getReachable(evenGrid, maxStep);
        int oddGridCount = getReachable(oddGrid, maxStep);
        int startStep = 66;
        long gridRepeats = 1;
        while (startStep + dimension < maxStep) {
            startStep += dimension;
            gridRepeats++;
        }
        answer += gridRepeats * gridRepeats * evenGridCount;
        answer += (gridRepeats - 1) * (gridRepeats - 1) * oddGridCount;


        int[][] upGrid = getStepGrid(Coord.of(dimension / 2, dimension - 1), startStep);
        int upGridCount = getReachable(upGrid, maxStep);
        answer += upGridCount;
        int[][] upRightEvenGrid = getStepGrid(Coord.of(0, dimension - 1), startStep - 65);
        int upRightEvenCount = getReachable(upRightEvenGrid, maxStep);
        answer += upRightEvenCount * (gridRepeats - 1);
        int[][] upRightOddGrid = getStepGrid(Coord.of(0, dimension - 1), startStep + 66);
        int upRightOddCount = getReachable(upRightOddGrid, maxStep);
        answer += upRightOddCount * (gridRepeats);

        int[][] rightGrid = getStepGrid(Coord.of(0, dimension / 2), startStep);
        int rightGridCount = getReachable(rightGrid, maxStep);
        answer += rightGridCount;
        int[][] rightDownEvenGrid = getStepGrid(Coord.of(0, 0), startStep - 65);
        int rightDownEvenCount = getReachable(rightDownEvenGrid, maxStep);
        answer += rightDownEvenCount * (gridRepeats - 1);
        int[][] rightDownOddGrid = getStepGrid(Coord.of(0, 0), startStep + 66);
        int rightDownOddCount = getReachable(rightDownOddGrid, maxStep);
        answer += rightDownOddCount * (gridRepeats);

        int[][] downGrid = getStepGrid(Coord.of(dimension / 2, 0), startStep);
        int downCount = getReachable(downGrid, maxStep);
        answer += downCount;
        int[][] downLeftEvenGrid = getStepGrid(Coord.of(dimension - 1, 0), startStep - 65);
        int downLeftEvenCount = getReachable(downLeftEvenGrid, maxStep);
        answer += downLeftEvenCount * (gridRepeats - 1);
        int[][] downLeftOddGrid = getStepGrid(Coord.of(dimension - 1, 0), startStep + 66);
        int downLeftOddCount = getReachable(downLeftOddGrid, maxStep);
        answer += downLeftOddCount * (gridRepeats);

        int[][] leftGrid = getStepGrid(Coord.of(dimension - 1, dimension / 2), startStep);
        int leftCount = getReachable(leftGrid, maxStep);
        answer += leftCount;
        int[][] leftUpEvenGrid = getStepGrid(Coord.of(dimension - 1, dimension - 1), startStep - 65);
        int leftUpEvenCount = getReachable(leftUpEvenGrid, maxStep);
        answer += leftUpEvenCount * (gridRepeats - 1);
        int[][] leftUpOddGrid = getStepGrid(Coord.of(dimension - 1, dimension - 1), startStep + 66);
        int leftUpOddCount = getReachable(leftUpOddGrid, maxStep);
        answer += leftUpOddCount * (gridRepeats);
        return answer;
    }

    int[][] getStepGrid(Coord start, int startStep) {
        int[][] stepGrid = new int[grid.length][grid[0].length];
        for (int[] row : stepGrid) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        Set<Coord> coords = Set.of(start);
        stepGrid[start.y()][start.x()] = startStep;
        int step = startStep;
        while (!coords.isEmpty()) {
            step++;
            Set<Coord> nextCoords = new HashSet<>();
            for (Coord c : coords) {
                nextCoords.addAll(Arrays.stream(Direction.values())
                        .map(c::addDirection)
                        .filter(n -> n.x() >= 0 && n.y() >= 0 && n.y() < grid.length && n.x() < grid[n.y()].length)
                        .filter(n -> grid[n.y()][n.x()] != '#' && stepGrid[n.y()][n.x()] == Integer.MAX_VALUE)
                        .toList());

            }
            for (Coord c : nextCoords) {
                stepGrid[c.y()][c.x()] = step;
            }
            coords = nextCoords;
        }
        return stepGrid;
    }

    private int getReachable(int[][] stepGrid, int maxStep) {
        int plotsHandy = 0;
        for (int[] line : stepGrid) {
            for (int i : line) {
                if (i <= maxStep && i % 2 == 0) {
                    plotsHandy++;
                }
            }
        }
        return plotsHandy;
    }
}


