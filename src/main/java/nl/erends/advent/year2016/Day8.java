package nl.erends.advent.year2016;


import nl.erends.advent.util.FileIO;

import java.util.ArrayList;
import java.util.List;

public class Day8 {

    public static void main(String[] args) {
        List<String> lines = FileIO.getFileAsList("2016day8.txt");
        Display display = new Display(50, 6);
        for (String line : lines) {
            display.processCommand(line);
        }
        System.out.println(display.pixelsLit());
        System.out.println(display);
    }

}

class Display {
    int width;
    int height;
    boolean[][] grid;

    public Display(int width, int height) {
        this.width = width;
        this.height = height;
        grid = new boolean[height][width];
    }

    public void processCommand(String input) {
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
        for (int column = 0; column < width; column++) {
            copyRow[column] = grid[row][column];
        }
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

    public int pixelsLit() {
        int pixelsLit = 0;
        for (boolean[] row : grid) {
            for (boolean light : row) {
                if (light) pixelsLit++;
            }
        }
        return pixelsLit;
    }
}