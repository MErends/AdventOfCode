package nl.erends.advent.year2019;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day24 extends AbstractProblem<List<String>, Integer> {
    
    private int[][] grid;
    private List<int[][]> levels;
    private int minLevel;

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
        printGrid();
        return calculateDiversity();
    }

    @Override
    public Integer solve2() {
        return null;
    }

    private int[][] readInput() {
        int[][] newGrid = new int[input.size()][input.get(0).length()];
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                newGrid[y][x] = input.get(y).charAt(x) == '#' ? 1 : 0;
            }
        }
        return newGrid;
    }
    
    private void printGrid() {
        for (int[] line : grid) {
            for (int i : line) {
                System.out.print(i == 1 ? '#' : '.');
            }
            System.out.println();
        }
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
    
    private int[][] getLevel(int level) {
        return null;
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
    
    private int countNeighbors(int x, int y, int level) {
        switch (y) {
            case 0:
                return countNeighbors0(x, level);
        }
        return 0;
    }
    
    private int countNeighbors0(int x, int level) {
        int neighbors = 0;
        if (level > 0) {
            int[][] uppperGrid = levels.get(level - 1);
            neighbors += uppperGrid[1][2]; // UP
            if (x == 0) {
                neighbors += uppperGrid[2][1]; // LEFT
            }
            if (x == 4) {
                neighbors += uppperGrid[2][3]; // RIGHT
            }
        }
        int[][] thisGrid = levels.get(level);
        if (x > 0) {
            neighbors += thisGrid[0][x - 1]; // LEFT
        }
        if (x < 4) {
            neighbors += thisGrid[0][x + 1]; // RIGHT
        }
        neighbors += thisGrid[1][x]; // DOWN
        return neighbors;
    }
    
    private int countNeighbors1(int x, int level) {
        int neighbors = 0;
        if (level > 0) {
            int[][] uppperGrid = levels.get(level - 1);
            if (x == 0) {
                neighbors += uppperGrid[2][1]; // LEFT
            }
            if (x == 4) {
                neighbors += uppperGrid[2][3]; // RIGHT
            }
        }
        int[][] thisGrid = levels.get(level);
        if (x > 0) {
            neighbors += thisGrid[1][x - 1]; // LEFT
        }
        if (x < 4) {
            neighbors += thisGrid[1][x + 1]; // RIGHT
        }
        neighbors += thisGrid[0][x]; // UP
        neighbors += thisGrid[2][x]; // DOWN
        return neighbors;
    }
    
    private int countNeighbors2(int x, int level) {
        return 0;
    }
}
