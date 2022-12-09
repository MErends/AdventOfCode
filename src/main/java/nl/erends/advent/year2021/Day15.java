package nl.erends.advent.year2021;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static nl.erends.advent.util.Util.ASCII_OFFSET;

/**
 * --- Day 15: Chiton ---
 * <p>The cavern is large, but has a very low ceiling, restricting your motion
 * to two dimensions. The shape of the cavern resembles a square. A quick scan
 * of chiton density produces a map of risk level throughout the cave. You start
 * in the top left position, your destination is the bottom right position, and
 * you cannot move diagonally. Your goal is to find a path with the lowest total
 * risk.
 * <p><a href="https://adventofcode.com/2021/day/15">2021 Day 15</a>
 */
public class Day15 extends AbstractProblem<List<String>,Integer> {
    
    private int size = 0;
    private int[][] grid;
    private List<GridPoint> gridPoints;
    private Map<String, Integer> riskMap;

    public static void main(String[] args) {
        new Day15().setAndSolve(Util.readInput(2021, 15));
    }

    @Override
    public Integer solve1() {
        if (size == 0) {
            size = input.size();
        }
        grid = new int[input.size()][];
        for (int index = 0; index < input.size(); index++) {
            grid[index] = input.get(index).chars()
                    .map(i -> i - ASCII_OFFSET)
                    .toArray();
        }
        riskMap = new HashMap<>();
        gridPoints = new ArrayList<>();
        riskMap.put("0,0", 0);
        gridPoints.add(new GridPoint(0, 0, 0));
        while (!gridPoints.isEmpty()) {
            gridPoints.sort(Comparator.comparingInt(g -> g.risk));
            GridPoint gridPoint = gridPoints.remove(0);
            if (gridPoint.x == size - 1 && gridPoint.y == size - 1) {
                return gridPoint.risk;
            }
            determineNeighbors(gridPoint.x, gridPoint.y);
        }
        throw new IllegalStateException();
    }

    @Override
    public Integer solve2() {
        size *= 5;
        return solve1();
    }

    private void determineNeighbors(int x, int y) {
        int localValue = getgrid(x, y);
        if (localValue == Integer.MAX_VALUE) {
            return;
        }
        int localRisk = getRisk(x, y);

        addGridPoint(x, y - 1, localRisk);
        addGridPoint(x - 1, y, localRisk);
        addGridPoint(x, y + 1, localRisk);
        addGridPoint(x + 1, y, localRisk);
    }
    
    private void addGridPoint(int x, int y, int localRisk) {
        int neighborValue = getgrid(x, y);
        if (neighborValue != Integer.MAX_VALUE) {
            int neighborRisk = localRisk + neighborValue;
            if (neighborRisk < getRisk(x, y)) {
                riskMap.put(x + "," + y, neighborRisk);
                gridPoints.add(new GridPoint(x, y, neighborRisk));
            }
        }
    }
    
    private int getgrid(int x, int y) {
        if (y < 0 || x < 0 || y > size - 1 || x > size - 1) {
            return Integer.MAX_VALUE;
        }
        int yInc = y / grid.length;
        int xInc = x / grid.length;
        y = y % grid.length;
        x = x % grid.length;
        int value = grid[y][x] + xInc + yInc;
        return value > 9 ? value - 9 : value;
    }
    
    private int getRisk(int x, int y) {
        Integer risk = riskMap.get(x + "," + y);
        return risk == null ? Integer.MAX_VALUE : risk;
    }
    
    private record GridPoint(int x, int y, int risk) {
    }
}
