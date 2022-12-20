package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day08 extends AbstractProblem<List<String>, String> {

    public static void main(String[] args) {
        new Day08().setAndSolve(Util.readInput(2016, 8));
    }
    
    @Override
    public String solve1() {
        Display display = new Display(50, 6);
        for (String line : input) {
            display.processCommand(line);
        }
        answer2 = display.toString();
        return Integer.toString(display.pixelsLit());
    }


    private static class Display {
        final int width;
        final int height;
        final boolean[][] grid;

        Display(int width, int height) {
            this.width = width;
            this.height = height;
            grid = new boolean[height][width];
        }

        void processCommand(String input) {
            String[] words = input.split(" ");
            if (words[0].equals("rect")) {
                turnOn(Integer.parseInt(words[1].split("x")[0]), Integer.parseInt(words[1].split("x")[1]));
            } else {
                if (words[1].equals("row")) {
                    rotateRow(Integer.parseInt(words[2].split("=")[1]), Integer.parseInt(words[4]));
                } else {
                    rotateColumn(Integer.parseInt(words[2].split("=")[1]), Integer.parseInt(words[4]));
                }
            }
        }

        private void turnOn(int width, int height) {
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    grid[y][x] = true;
                }
            }
        }

        private void rotateColumn(int column, int displace) {
            boolean[] copyColumn = new boolean[height];
            for (int row = 0; row < height; row++) {
                copyColumn[row] = grid[row][column];
            }
            for (int row = 0; row < height; row++) {
                int copyLocation = row - displace;
                if (copyLocation < 0) {
                    copyLocation += height;
                }
                grid[row][column] = copyColumn[copyLocation];
            }
        }

        private void rotateRow(int row, int displace) {
            boolean[] copyRow = new boolean[width];
            System.arraycopy(grid[row], 0, copyRow, 0, width);
            for (int column = 0; column < width; column++) {
                int copyLocation = column - displace;
                if (copyLocation < 0) {
                    copyLocation += width;
                }
                grid[row][column] = copyRow[copyLocation];
            }
        }

        @Override
        public String toString() {
            StringBuilder output = new StringBuilder();
            for (boolean[] row : grid) {
                for (boolean light : row) {
                    output.append(light ? "#" : ".");
                }
                output.append("\n");
            }
            return output.toString();
        }

        int pixelsLit() {
            int pixelsLit = 0;
            for (boolean[] row : grid) {
                for (boolean light : row) {
                    if (light) pixelsLit++;
                }
            }
            return pixelsLit;
        }
    }
}
