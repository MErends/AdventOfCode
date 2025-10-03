package nl.erends.advent;


import nl.erends.advent.util.AbstractProblem;

import java.util.Arrays;

public class Day13 extends AbstractProblem<Integer, Integer> {
    
    private char[][] maze;
    private int[][] distances;

    void main() {
        new Day13().setAndSolve(1362);
    }
    
    @Override
    public Integer solve1() {
        initMaze();
        initDistances();
        distances[1][1] = 0;
        fillDistances(0);
        return distances[39][31];
    }
    
    @Override
    public Integer solve2() {
        if (distances == null) {
            solve1();
        }
        int inRange = 0;
        for (int[] row : distances) {
            for (int distance : row) {
                if (distance <= 50) {
                    inRange++;
                }
            }
        }
        return inRange;
    }
    
    private void initMaze() {
        maze = new char[51][51];
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze.length; x++) {
                if (isOpen(x, y)) {
                    maze[y][x] = '.';
                } else {
                    maze[y][x] = '#';
                }
            }
        }
    }
    
    private void initDistances() {
        distances = new int[51][51];
        for (int[] row : distances) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
    }
    
    private void fillDistances(int startFrom) {
        boolean foundStartFrom = false;
        for (int y = 0; y < maze.length; y++) {
            for (int x = 0; x < maze[y].length; x++) {
                if (distances[y][x] == startFrom) {
                    fillNeighbors(x, y, startFrom + 1);
                    foundStartFrom = true;
                }
            }
        }
        if (foundStartFrom) {
            fillDistances(startFrom + 1);
        }
    }
    
    private void fillNeighbors(int x, int y, int value) {
        int left = x - 1;
        if(left >= 0 && maze[y][left] != '#' && distances[y][left] > value) {
            distances[y][left] = value;
        }
        int right = x + 1;
        if (right < maze[0].length && maze[y][right] != '#' && distances[y][right] > value) {
            distances[y][right] = value;
        }
        int up = y - 1;
        if (up >= 0 && maze[up][x] != '#' && distances[up][x] > value) {
            distances[up][x] = value;
        }
        int down = y + 1;
        if (down < maze.length && maze[down][x] != '#' && distances[down][x] > value) {
            distances[down][x] = value;
        }
    }

    private boolean isOpen(int x, int y) {
        int number = x*x + 3*x + 2*x*y + y + y*y + input;
        String bits = Integer.toBinaryString(number);
        int ones = 0;
        for (char c : bits.toCharArray()) {
            if (c == '1') {
                ones += 1;
            } else {
                ones += 0;
            }
        }
        return ones % 2 == 0;
    }
}
