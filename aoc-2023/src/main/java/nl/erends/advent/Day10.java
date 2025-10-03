package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Util;

import java.util.List;

import static nl.erends.advent.util.Direction.*;

/**
 * <h1>--- Day 10: Pipe Maze ---</h1>
 * <p>Scanning the area, you discover that the entire field you're standing on
 * is densely packed with pipes. Based on the acoustics of the animal's
 * scurrying, you're confident the pipe that contains the animal is one large,
 * continuous loop. Find the single giant loop starting at S. How many steps
 * along the loop does it take to get from the starting position to the point
 * farthest from the starting position?</p>
 * <p><a href="https://adventofcode.com/2023/day/10">2023 Day 10</a></p>
 */
public class Day10 extends AbstractProblem<List<String>, Number> {

    private boolean[][] isPipe;
    private char[][] grid;

    void main() {
        new Day10().setAndSolve(Util.readInput(2023, 10));
    }

    @Override
    protected Number solve1() {
        int height = input.size();
        int width = input.getFirst().length();
        grid = new char[height][];
        for (int row = 0; row < height; row++) {
            grid[row] = input.get(row).toCharArray();
        }
        isPipe = new boolean[height][width];
        int y = 0;
        int x = 0;

        for (int i = 0; i < height * width; i++) {
            int row = i / width;
            int col = i % width;
            if (grid[row][col] == 'S') {
                y = row;
                x = col;
                break;
            }
        }
        Direction direction = setStartAndGetDirection(x, y);
        isPipe[y][x] = true;
        int steps = 1;
        x += direction.dx();
        y += direction.dy();
        while(!isPipe[y][x]) {
            isPipe[y][x] = true;
            direction = getDirection(grid[y][x], direction);
            x += direction.dx();
            y += direction.dy();
            steps++;
        }

        answer2 = getInsideCount();
        return steps / 2;
    }

    private int getInsideCount() {
        int insideCount = 0;
        for (int y = 0; y < grid.length; y++) {
            boolean inside = false;
            char lastPipe = '\0';
            for (int x = 0; x < grid[y].length; x++) {
                if (!isPipe[y][x]) {
                    insideCount += inside ? 1 : 0;
                    continue;
                }
                switch (grid[y][x]) {
                    case '|' -> inside = !inside;
                    case 'F', 'L' -> lastPipe = grid[y][x];
                    case '7' -> {
                        if (lastPipe == 'L') {
                            inside = !inside;
                        }
                    }
                    case 'J' -> {
                        if (lastPipe == 'F') {
                            inside = !inside;
                        }
                    }
                    default -> {
                        // '-' is no action
                    }
                }
            }
        }
        return insideCount;
    }

    private Direction setStartAndGetDirection(int x, int y) {
        Direction direction;
        if (y > 0 && List.of('|', 'F', '7').contains(grid[y-1][x])) {
            direction = UP;
            if (List.of('-', 'J', '7').contains(grid[y][x + 1])) {
                grid[y][x] = 'L';
            } else if (List.of('|', 'L', 'J').contains(grid[y+1][x])) {
                grid[y][x] = '|';
            } else {
                grid[y][x] = 'J';
            }
        } else if (List.of('|', 'L', 'J').contains(grid[y+1][x])) {
            direction = DOWN;
            if (List.of('-', 'J', '7').contains(grid[y][x + 1])) {
                grid[y][x] = 'F';
            } else {
                grid[y][x] = '7';
            }
        } else {
            direction = RIGHT;
            grid[y][x] = '-';
        }
        return direction;
    }

    private Direction getDirection(char pipe, Direction direction) {
        return switch (direction) {
            case UP -> switch (pipe) {
                case '7' -> direction.turnLeft();
                case 'F' -> direction.turnRight();
                default -> direction;
            };
            case DOWN -> switch (pipe) {
                case 'L' -> direction.turnLeft();
                case 'J' -> direction.turnRight();
                default -> direction;
            };
            case LEFT -> switch (pipe) {
                case 'L' -> direction.turnRight();
                case 'F' -> direction.turnLeft();
                default -> direction;
            };
            case RIGHT -> switch (pipe) {
                case '7' -> direction.turnRight();
                case 'J' -> direction.turnLeft();
                default -> direction;
            };
        };
    }
}