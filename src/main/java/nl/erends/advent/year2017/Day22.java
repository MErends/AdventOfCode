package nl.erends.advent.year2017;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day22 extends AbstractProblem<List<String>, Integer> {
    
    private int x;
    private int y;
    
    public static void main(String[] args) {
        new Day22().setAndSolve(Util.readInput(2017, 22));
    }
    
    @Override
    public Integer solve1() {
        boolean[][] grid = new boolean[input.size()][input.get(0).length()];
        for (y = 0; y < input.size(); y++) {
            for (x = 0; x < input.get(y).length(); x++) {
                grid[y][x] = input.get(y).charAt(x) == '#';
            }
        }
        x = grid.length / 2;
        y = grid[0].length / 2;
        Direction direction = Direction.UP;
        int infections = 0;
        int totalSteps = 10000;
        for (int step = 0; step < totalSteps; step++) {
            direction = grid[y][x] ? turnRight(direction) : turnLeft(direction);
            if (!grid[y][x]) {
                infections++;
            }
            grid[y][x] = !grid[y][x];
            grid = updateGrid(grid, direction);
        }
        return infections;
    }

    @Override
    public Integer solve2() {
        char[][]grid = new char[input.size()][input.get(0).length()];
        for (y = 0; y < input.size(); y++) {
            for (x = 0; x < input.get(y).length(); x++) {
                grid[y][x] = input.get(y).charAt(x) == '#' ? '#' : '\0';
            }
        }
        x = grid.length / 2;
        y = grid[0].length / 2;
        Direction direction = Direction.UP;
        int infections = 0;
        int totalSteps = 10000000;
        for (int step = 0; step < totalSteps; step++) {
            switch(grid[y][x]) {
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
                default:
            }
            switch(grid[y][x]) {
                case '\0':
                    grid[y][x] = 'W';
                    break;
                case 'W':
                    infections++;
                    grid[y][x] = '#';
                    break;
                case '#':
                    grid[y][x] = 'F';
                    break;
                default:
                    grid[y][x] = '\0';
                    break;
            }
            grid = updateGrid(grid, direction);
        }
        return infections;
    }

    private boolean[][] updateGrid(boolean[][] grid, Direction direction) {
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
        return grid;
    }

    private char[][] updateGrid(char[][] grid, Direction direction) {
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
        return grid;
    }
    
    private boolean[][] addTopRow(boolean[][] grid) {
        boolean[][] target = new boolean[grid.length + 1][grid[0].length];
        System.arraycopy(grid, 0, target, 1, grid.length);
        return target;
    }
    
    private boolean[][] addBottomRow(boolean[][] grid) {
        boolean[][] target = new boolean[grid.length + 1][grid[0].length];
        System.arraycopy(grid, 0, target, 0, grid.length);
        return target;
    }
    
    private boolean[][] addLeftColumn(boolean[][] grid) {
        boolean[][] target = new boolean[grid.length][grid[0].length + 1];
        for (int localY = 0; localY < grid.length; localY++) {
            System.arraycopy(grid[localY], 0, target[localY], 1, grid[localY].length);
        }
        return target;
    }
    
    private boolean[][] addRightColumn(boolean[][] grid) {
        boolean[][] target = new boolean[grid.length][grid[0].length + 1];
        for (int localY = 0; localY < grid.length; localY++) {
            System.arraycopy(grid[localY], 0, target[localY], 0, grid[localY].length);
        }
        return target;
    }

    private char[][] addTopRow(char[][] grid) {
        char[][] target = new char[grid.length + 1][grid[0].length];
        System.arraycopy(grid, 0, target, 1, grid.length);
        return target;
    }

    private char[][] addBottomRow(char[][] grid) {
        char[][] target = new char[grid.length + 1][grid[0].length];
        System.arraycopy(grid, 0, target, 0, grid.length);
        return target;
    }

    private char[][] addLeftColumn(char[][] grid) {
        char[][] target = new char[grid.length][grid[0].length + 1];
        for (int localY = 0; localY < grid.length; localY++) {
            System.arraycopy(grid[localY], 0, target[localY], 1, grid[localY].length);
        }
        return target;
    }

    private char[][] addRightColumn(char[][] grid) {
        char[][] target = new char[grid.length][grid[0].length + 1];
        for (int localY = 0; localY < grid.length; localY++) {
            System.arraycopy(grid[localY], 0, target[localY], 0, grid[localY].length);
        }
        return target;
    }
    
    private Direction turnLeft(Direction direction) {
        switch (direction) {
            case UP:
                return Direction.LEFT;
            case DOWN:
                return Direction.RIGHT;
            case LEFT:
                return Direction.DOWN;
            default:
                return Direction.UP;
        } 
    }

    private Direction turnRight(Direction direction) {
        switch (direction) {
            case UP:
                return Direction.RIGHT;
            case DOWN:
                return Direction.LEFT;
            case LEFT:
                return Direction.UP;
            default:
                return Direction.DOWN;
        }
    }
}