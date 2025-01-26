package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day24 extends AbstractProblem<List<String>, Integer> {
    
    private int[][] grid;
    int iterations = 200;

    public static void main(String[] args) {
        new Day24().setAndSolve(Util.readInput(2019, 24));
    }

    @Override
    public Integer solve1() {
        grid = readInput();
        Set<Integer> previousStates = new HashSet<>();
        while (previousStates.add(calculateDiversity())) {
            iterate();
        }
        return calculateDiversity();
    }

    @Override
    public Integer solve2() {
        List<Depth> depths = new ArrayList<>();
        depths.add(new Depth(readInput()));
        for (int iteration = 0; iteration < iterations; iteration++) {
            if (depths.getFirst().getValue() != 0) {
                depths.addFirst(new Depth());
            }
            if (depths.getLast().getValue() != 0) {
                depths.add(new Depth());
            }
            int dSize = depths.size();
            List<Depth> newDepths = new ArrayList<>();
            newDepths.add(depths.get(0).iterate(new int[5][5], depths.get(1).grid));
            for (int d = 1; d < depths.size() - 1; d++) {
                newDepths.add(depths.get(d).iterate(depths.get(d - 1).grid, depths.get(d + 1).grid));
            }
            newDepths.add(depths.get(dSize - 1).iterate(depths.get(dSize - 2).grid, new int[5][5]));
            if (newDepths.getFirst().getValue() == 0) {
                newDepths.removeFirst();
            }
            if (newDepths.getLast().getValue() == 0) {
                newDepths.removeLast();
            }
            depths = newDepths;
        }
        return depths.stream().mapToInt(Depth::getValue).sum();
    }

    private int[][] readInput() {
        int[][] newGrid = new int[input.size()][input.getFirst().length()];
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                newGrid[y][x] = input.get(y).charAt(x) == '#' ? 1 : 0;
            }
        }
        return newGrid;
    }
    
    private int calculateDiversity() {
        StringBuilder stringBuilder = new StringBuilder();
        for (int[] line : grid) {
            for (int i : line) {
                stringBuilder.append(i);
            }
        }
        stringBuilder.reverse();
        return Integer.parseInt(stringBuilder.toString(), 2);
    }
    
    private void iterate() {
        int[][] newGrid = new int[grid.length][grid[0].length];
        for (int i = 0; i < newGrid.length; i++) {
            System.arraycopy(grid[i], 0, newGrid[i], 0, newGrid[i].length);
        }
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                int neighbors = countNeighbors(x, y);
                if (newGrid[y][x] == 1 && neighbors != 1) {
                    newGrid[y][x] = 0;
                } else if (newGrid[y][x] == 0 && (neighbors == 1 || neighbors == 2)) {
                    newGrid[y][x] = 1;
                }
            }
        }
        grid = newGrid;
    }

    private int countNeighbors(int x, int y) {
        int neighbors = 0;
        if (y > 0) {
            neighbors += grid[y - 1][x];
        }
        if (y < 4) {
            neighbors += grid[y+1][x];
        }
        if (x > 0) {
            neighbors += grid[y][x - 1];
        }
        if (x < 4) {
            neighbors += grid[y][x + 1];
        }
        return neighbors;
    }
    
    private static class Depth {
        
        final int[][] grid;
        
        Depth() {
            this(new int[5][5]);
        }
        
        Depth(int[][] grid) {
            this.grid = grid;
        }

        int getValue() {
            int value = 0;
            for (int[] row : grid) {
                for (int i : row) {
                    value += i;
                }
            }
            return value;
        }
        
        Depth iterate(int[][] down, int[][] up) {
            int[][] newGrid = new int[5][5];
            newGrid[0][0] = newValue(grid[0][0], up[1][2] + grid[0][1] + grid[1][0] + up[2][1]);
            newGrid[0][1] = newValue(grid[0][1], up[1][2] + grid[0][2] + grid[1][1] + grid[0][0]);
            newGrid[0][2] = newValue(grid[0][2], up[1][2] + grid[0][3] + grid[1][2] + grid[0][1]);
            newGrid[0][3] = newValue(grid[0][3], up[1][2] + grid[0][4] + grid[1][3] + grid[0][2]);
            newGrid[0][4] = newValue(grid[0][4], up[1][2] + up[2][3] + grid[1][4] + grid[0][3]);
            
            newGrid[1][0] = newValue(grid[1][0], grid[0][0] + grid[1][1] + grid[2][0] + up[2][1]);
            newGrid[1][1] = newValue(grid[1][1], grid[0][1] + grid[1][2] + grid[2][1] + grid[1][0]);
            newGrid[1][2] = newValue(grid[1][2], grid[0][2] + grid[1][3] + down[0][0] + down[0][1] + down[0][2] + down[0][3] + down[0][4] + grid[1][1]);
            newGrid[1][3] = newValue(grid[1][3], grid[0][3] + grid[1][4] + grid[2][3] + grid[1][2]);
            newGrid[1][4] = newValue(grid[1][4], grid[0][4] + up[2][3] + grid[2][4] + grid[1][3]);

            newGrid[2][0] = newValue(grid[2][0], grid[1][0] + grid[2][1] + grid[3][0] + up[2][1]);
            newGrid[2][1] = newValue(grid[2][1], grid[1][1] + down[0][0] + down[1][0] + down[2][0] + down[3][0] + down[4][0] + grid[3][1] + grid[2][0]);
            newGrid[2][3] = newValue(grid[2][3], grid[1][3] + grid[2][4] + grid[3][3] + down[0][4] + down[1][4] + down[2][4] + down[3][4] + down[4][4]);
            newGrid[2][4] = newValue(grid[2][4], grid[1][4] + up[2][3] + grid[3][4] + grid[2][3]);

            newGrid[3][0] = newValue(grid[3][0], grid[2][0] + grid[3][1] + grid[4][0] + up[2][1]);
            newGrid[3][1] = newValue(grid[3][1], grid[2][1] + grid[3][2] + grid[4][1] + grid[3][0]);
            newGrid[3][2] = newValue(grid[3][2], down[4][0] + down[4][1] + down[4][2] + down[4][3] + down[4][4] + grid[3][3] + grid[4][2] + grid[3][1]);
            newGrid[3][3] = newValue(grid[3][3], grid[2][3] + grid[3][4] + grid[4][3] + grid[3][2]);
            newGrid[3][4] = newValue(grid[3][4], grid[2][4] + up[2][3] + grid[4][4] + grid[3][3]);

            newGrid[4][0] = newValue(grid[4][0], grid[3][0] + grid[4][1] + up[3][2] + up[2][1]);
            newGrid[4][1] = newValue(grid[4][1], grid[3][1] + grid[4][2] + up[3][2] + grid[4][0]);
            newGrid[4][2] = newValue(grid[4][2], grid[3][2] + grid[4][3] + up[3][2] + grid[4][1]);
            newGrid[4][3] = newValue(grid[4][3], grid[3][3] + grid[4][4] + up[3][2] + grid[4][2]);
            newGrid[4][4] = newValue(grid[4][4], grid[3][4] + up[2][3] + up[3][2] + grid[4][3]);
            
            return new Depth(newGrid);
        }
        
        int newValue(int currentValue, int neighbors) {
            if (currentValue == 1 && neighbors != 1) {
                return 0;
            } else if (currentValue == 0 && (neighbors == 1 || neighbors == 2)) {
                return 1;
            }
            return currentValue;
        }
    }
}
