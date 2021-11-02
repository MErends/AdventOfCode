package nl.erends.advent.year2018;

import nl.erends.advent.util.AbstractProblem;

public class Day11 extends AbstractProblem<Integer, String> {

    private static final int SIZE = 300 + 1;
    private int[][] grid;

    public static void main(String[] args) {
        new Day11().setAndSolve(8141);
    }
    
    @Override
    public String solve1() {
        createGrid();
        int maxPower = Integer.MIN_VALUE;
        int maxX = 0;
        int maxY = 0;
        for (int y = 1; y < SIZE - 2; y++) {
            for (int x = 1; x < SIZE - 2; x++) {
                int power = subgridPower(x, y, 3);
                if (power > maxPower) {
                    maxPower = power;
                    maxX = x;
                    maxY = y;
                }
            }
        }
        return maxX + "," + maxY;
    }
    
    @Override
    public String solve2() {
        int maxPower = Integer.MIN_VALUE;
        int maxX = 0;
        int maxY = 0;
        int maxSubgridZise = 0;
        for (int subgridSize = 1; subgridSize <= 300; subgridSize++) {
            for (int y = 1; y < SIZE - subgridSize + 1; y++) {
                for (int x = 1; x < SIZE - subgridSize + 1; x++) {
                    int power = subgridPower(x, y, subgridSize);
                    if (power > maxPower) {
                        maxPower = power;
                        maxX = x;
                        maxY = y;
                        maxSubgridZise = subgridSize;
                    }
                }
            }
        }
        return maxX + "," + maxY + "," + maxSubgridZise;
    }

    private void createGrid() {
        grid = new int[SIZE][SIZE];
        for (int y = 0; y < SIZE; y++) {
            for (int x = 0; x < SIZE; x++) {
                int rackId = x + 10;
                int power = rackId * y;
                int serial = input;
                power += serial;
                power *= rackId;
                power /= 100;
                power %= 10;
                power -= 5;
                grid[y][x] = power;
            }
        }
    }

    private int subgridPower(int x, int y, int subgridSize) {
        int power = 0;
        for (int dy = y; dy < y + subgridSize; dy++) {
            for (int dx = x; dx < x + subgridSize; dx++) {
                power += grid[dy][dx];
            }
        }
        return power;
    }
}
