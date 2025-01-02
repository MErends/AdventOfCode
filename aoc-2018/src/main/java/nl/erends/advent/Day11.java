package nl.erends.advent;

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
        String answer1 = null;
        int globalMax = Integer.MIN_VALUE;
        for (int subGridSize = 1; subGridSize < SIZE; subGridSize++) {
            int maxPower = Integer.MIN_VALUE;
            int maxX = 0;
            int maxY = 0;
            for (int y = 1; y < SIZE - subGridSize; y++) {
                for (int x = 1; x < SIZE - subGridSize; x++) {
                    int power = subgridPower(x, y, subGridSize);
                    if (power > maxPower) {
                        maxPower = power;
                        maxX = x;
                        maxY = y;
                    }
                }
            }
            if (subGridSize == 3) {
                answer1 = maxX + "," + maxY;
            }
            if (maxPower > globalMax) {
                globalMax = maxPower;
                answer2 = maxX + "," + maxY + "," + subGridSize;
            } else if (maxPower < 0) {
                break;
            }
        }
        return answer1;
    }

    private void createGrid() {
        grid = new int[SIZE][SIZE];
        for (int y = 1; y < SIZE; y++) {
            for (int x = 1; x < SIZE; x++) {
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
