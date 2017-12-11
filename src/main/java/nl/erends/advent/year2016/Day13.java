package nl.erends.advent.year2016;


import nl.erends.advent.util.FileIO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day13 {

    public static void main(String[] args) {
        printGrid(40, 50, 1362);
    }

    public static void printGrid(int width, int height, int favNumber) {
        boolean[][] grid = new boolean[height][width];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int number = x*x + 3*x + 2*x*y + y + y*y + favNumber;
                String bits = Integer.toBinaryString(number);
                int ones = 0;
                for (char c : bits.toCharArray()) {
                    ones += c == '1' ? 1 : 0;
                }
                grid[y][x] = ones % 2 == 0;
            }
        }

        for (boolean[] row : grid) {
            for (boolean openSpace : row) {
                System.out.print(openSpace ? "." : "#");
            }
            System.out.print("\n");
        }
    }
}
