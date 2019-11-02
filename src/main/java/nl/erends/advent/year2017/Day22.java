package nl.erends.advent.year2017;


//     ..#
//     #..
//     ...

import nl.erends.advent.util.Util;

import java.util.List;

import static nl.erends.advent.year2017.Day19.Direction.*;

public class Day22 {
    
    public static void main(String[] args) {
        List<String> input = Util.getFileAsList("2017day22.txt");
        boolean[][]grid = new boolean[input.size()][input.get(0).length()];
        for (int y = 0; y < input.size(); y++) {
            for (int x = 0; x < input.get(y).length(); x++) {
                grid[y][x] = input.get(y).charAt(x) == '#';
            }
        }
        int x = grid.length / 2;
        int y = grid[0].length / 2;
        Day19.Direction direction = UP;
        int infections = 0;
        int totalSteps = 10000;
        for (int step = 0; step < totalSteps; step++) {
            direction = grid[y][x] ? turnRight(direction) : turnLeft(direction);
            if (!grid[y][x]) {
                infections++;
            }
            grid[y][x] = !grid[y][x];
            switch (direction) {
                case UP:
                    if (y == 0) {
                        grid = addTopRow(grid);
                    } else {
                        y--;
                    }
                    break;
                case DOWN:
                    if (y == grid.length - 1) {
                        grid = addBottomRow(grid);
                    }
                    y++;
                    break;
                case LEFT:
                    if (x == 0) {
                        grid = addLeftColumn(grid);
                    } else {
                        x--;
                    }
                    break;
                case RIGHT:
                    if (x == grid[0].length - 1) {
                        grid = addRightColumn(grid);
                    }
                    x++;
                    break;
            }
        }
        System.out.println(infections);

        char[][]grid2 = new char[input.size()][input.get(0).length()];
        for (y = 0; y < input.size(); y++) {
            for (x = 0; x < input.get(y).length(); x++) {
                grid2[y][x] = input.get(y).charAt(x) == '#' ? '#' : '\0';
            }
        }
        x = grid2.length / 2;
        y = grid2[0].length / 2;
        direction = UP;
        infections = 0;
        totalSteps = 10000000;
        for (int step = 0; step < totalSteps; step++) {
            switch(grid2[y][x]) {
                case '\0':
                    direction = turnLeft(direction);
                    break;
                case '#':
                    direction = turnRight(direction);
                    break;
                case 'F':
                    direction = turnRight(direction);
                    direction = turnRight(direction);
                    break;
            }
            switch(grid2[y][x]) {
                case '\0':
                    grid2[y][x] = 'W';
                    break;
                case 'W':
                    infections++;
                    grid2[y][x] = '#';
                    break;
                case '#':
                    grid2[y][x] = 'F';
                    break;
                default:
                    grid2[y][x] = '\0';
                    break;
            }
            switch (direction) {
                case UP:
                    if (y == 0) {
                        grid2 = addTopRow(grid2);
                    } else {
                        y--;
                    }
                    break;
                case DOWN:
                    if (y == grid2.length - 1) {
                        grid2 = addBottomRow(grid2);
                    }
                    y++;
                    break;
                case LEFT:
                    if (x == 0) {
                        grid2 = addLeftColumn(grid2);
                    } else {
                        x--;
                    }
                    break;
                case RIGHT:
                    if (x == grid2[0].length - 1) {
                        grid2 = addRightColumn(grid2);
                    }
                    x++;
                    break;
            }
        }
        System.out.println(infections);
    }
    
    
    private static void printGrid(boolean[][] grid) {
        for (boolean[] row : grid) {
            for (boolean b : row) {
                System.out.print(b ? "# " : ". ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    private static void printGrid(char[][] grid) {
        for (char[] row : grid) {
            for (char c : row) {
                System.out.print(c == '\0' ? ". " : c + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    private static boolean[][] addTopRow(boolean[][] grid) {
        boolean[][] target = new boolean[grid.length + 1][grid[0].length];
        System.arraycopy(grid, 0, target, 1, grid.length);
        return target;
    }
    
    private static boolean[][] addBottomRow(boolean[][] grid) {
        boolean[][] target = new boolean[grid.length + 1][grid[0].length];
        System.arraycopy(grid, 0, target, 0, grid.length);
        return target;
    }
    
    private static boolean[][] addLeftColumn(boolean[][] grid) {
        boolean[][] target = new boolean[grid.length][grid[0].length + 1];
        for (int y = 0; y < grid.length; y++) {
            System.arraycopy(grid[y], 0, target[y], 1, grid[y].length);
        }
        return target;
    }
    
    private static boolean[][] addRightColumn(boolean[][] grid) {
        boolean[][] target = new boolean[grid.length][grid[0].length + 1];
        for (int y = 0; y < grid.length; y++) {
            System.arraycopy(grid[y], 0, target[y], 0, grid[y].length);
        }
        return target;
    }

    private static char[][] addTopRow(char[][] grid) {
        char[][] target = new char[grid.length + 1][grid[0].length];
        System.arraycopy(grid, 0, target, 1, grid.length);
        return target;
    }

    private static char[][] addBottomRow(char[][] grid) {
        char[][] target = new char[grid.length + 1][grid[0].length];
        System.arraycopy(grid, 0, target, 0, grid.length);
        return target;
    }

    private static char[][] addLeftColumn(char[][] grid) {
        char[][] target = new char[grid.length][grid[0].length + 1];
        for (int y = 0; y < grid.length; y++) {
            System.arraycopy(grid[y], 0, target[y], 1, grid[y].length);
        }
        return target;
    }

    private static char[][] addRightColumn(char[][] grid) {
        char[][] target = new char[grid.length][grid[0].length + 1];
        for (int y = 0; y < grid.length; y++) {
            System.arraycopy(grid[y], 0, target[y], 0, grid[y].length);
        }
        return target;
    }
    
    private static Day19.Direction turnLeft(Day19.Direction direction) {
        switch (direction) {
            case UP:
                return LEFT;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            default:
                return UP;
        } 
    }

    private static Day19.Direction turnRight(Day19.Direction direction) {
        switch (direction) {
            case UP:
                return RIGHT;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            default:
                return DOWN;
        }
    }
}