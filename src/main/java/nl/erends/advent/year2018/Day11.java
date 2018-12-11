package nl.erends.advent.year2018;

public class Day11 {

    private static int size = 300 + 1;
    private static int[][] grid = new int[size][size];

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                int rackId = x + 10;
                int power = rackId * y;
                int serial = 8141;
                power += serial;
                power *= rackId;
                power /= 100;
                power %= 10;
                power -= 5;
                grid[y][x] = power;
            }
        }
        int maxPower = Integer.MIN_VALUE;
        int maxX = 0;
        int maxY = 0;
        for (int y = 1; y < size - 2; y++) {
            for (int x = 1; x < size - 2; x++) {
                int power = subgridPower(x, y, 3);
                if (power > maxPower) {
                    maxPower = power;
                    maxX = x;
                    maxY = y;
                }
            }
        }
        long mid = System.currentTimeMillis();
        System.out.println(maxX + "," + maxY);
        maxPower = Integer.MIN_VALUE;
        int maxSubgridZise = 0;
        for (int subgridSize = 1; subgridSize <= 300; subgridSize++) {
            for (int y = 1; y < size - subgridSize + 1; y++) {
                for (int x = 1; x < size - subgridSize + 1; x++) {
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
        System.out.println(maxX + "," + maxY + "," + maxSubgridZise);
        long end = System.currentTimeMillis();
        System.out.println("Part 1: " + (mid - start) + " millis.\nPart 2: " + (end - mid) + " millis.");
    }


    private static int subgridPower(int x, int y, int subgridSize) {
        int power = 0;
        for (int dy = y; dy < y + subgridSize; dy++) {
            for (int dx = x; dx < x + subgridSize; dx++) {
                power += grid[dy][dx];
            }
        }
        return power;
    }
}
