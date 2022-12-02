package nl.erends.advent.year2019;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day17 extends AbstractProblem<String, Integer> {

    private char[][] grid;

    public static void main(String[] args) {
        new Day17().setAndSolve(Util.readLine(2019, 17));
    }

    @Override
    protected Integer solve1() {
        List<String> output = new ArrayList<>();
        Intcode robot = new Intcode(input);
        StringBuilder sb = new StringBuilder();
        while (true) {
            robot.execute();
            if (robot.isHalted()) {
                break;
            }
            char c = (char) robot.getOutput().longValue();
            if (c == '\n') {
                output.add(sb.toString());
                sb = new StringBuilder();
            } else {
                sb.append(c);
            }
        }
        output.removeIf(String::isEmpty);
        grid = new char[output.size()][];
        for (int i = 0; i < output.size(); i++) {
            grid[i] = output.get(i).toCharArray();
        }
        int xMax = grid[0].length;
        int yMax = grid.length;
        int alignment = 0;
        for (int x = 1; x < xMax - 1; x++) {
            for (int y = 1; y < yMax - 1; y++) {
                char middle = grid[y][x];
                char left = grid[y][x - 1];
                char up = grid[y - 1][x];
                char right = grid[y][x + 1];
                char down = grid[y + 1][x];
                if (middle == '#' && left == '#' && up == '#' && right == '#' && down == '#') {
                    alignment += (x * y);
                }
            }
        }
        return alignment;
    }

    @Override
    public Integer solve2() {
        if (grid == null) {
            solve1();
        }

        Intcode robot = new Intcode(input);
        robot.setCode(0, 2);
        String commands = """
                A,B,A,B,C,C,B,A,B,C
                L,12,L,10,R,8,L,12
                R,8,R,10,R,12
                L,10,R,12,R,8
                n
                """;
        for (char c : commands.toCharArray()) {
            robot.addInput((int) c);
        }
        StringBuilder sb = new StringBuilder();
        List<String> output = new ArrayList<>();
        while (true) {
            robot.execute();
            if (robot.isHalted()) {
                break;
            }
            // char c = (char) robot.getOutput().longValue();
            // if (c == '\n') {
            //     if (sb.length() == 0) {
            //         for (String line : output) {
            //             System.out.println(line);
            //         }
            //         output = new ArrayList<>();
            //         sb = new StringBuilder();
            //     } else {
            //         output.add(sb.toString());
            //         sb = new StringBuilder();
            //     }
            // } else {
            //     sb.append(c);
            // }
        }
        return (int) robot.getOutput().longValue();
    }

    private char[][] expandGrid(char[][] oldGrid) {
        int srcY = oldGrid.length;
        int srcX = oldGrid[0].length;
        char[][] newGrid = new char[srcY + 2][srcX + 2];
        for (char[] line : newGrid) {
            Arrays.fill(line, '.');
        }
        for (int i = 0; i < oldGrid.length; i++) {
            System.arraycopy(oldGrid[i], 0, newGrid[i+1], 1, oldGrid[i].length);
        }
        return newGrid;
    }

    private String getMovement(char[][] grid) {
        int x = 0;
        int y;
        boolean foundStart = false;
        for (y = 0; y < grid.length && !foundStart; y++) {
            for (x = 0; x < grid[y].length; x++) {
                char c = grid[y][x];
                if (c != '.' && c != '#') {
                    foundStart = true;
                    break;
                }
            }
        }
        y--;
        Direction direction = Direction.UP;
        int steps = 0;
        List<String> path = new ArrayList<>();
        while (true) {
            char nextChar = switch (direction) {
                case UP -> grid[y - 1][x];
                case DOWN -> grid[y + 1][x];
                case LEFT -> grid[y][x - 1];
                case RIGHT -> grid[y][x + 1];
            };
            if (nextChar == '#') {
                steps++;
                switch (direction) {
                    case UP -> y--;
                    case DOWN -> y++;
                    case LEFT -> x--;
                    case RIGHT -> x++;
                }
            } else {
                path.add(Integer.toString(steps));
                steps = 0;
                switch (direction) {
                    case UP:
                        if (grid[y][x - 1] == '#') {
                            direction = Direction.LEFT;
                            path.add("L");
                            nextChar = '#';
                        } else if (grid[y][x + 1] == '#') {
                            direction = Direction.RIGHT;
                            path.add("R");
                            nextChar = '#';
                        }
                        break;
                    case DOWN:
                        if (grid[y][x - 1] == '#') {
                            direction = Direction.LEFT;
                            path.add("R");
                            nextChar = '#';
                        } else if (grid[y][x + 1] == '#') {
                            direction = Direction.RIGHT;
                            path.add("L");
                            nextChar = '#';
                        }
                        break;
                    case LEFT:
                        if (grid[y - 1][x] == '#') {
                            direction = Direction.UP;
                            path.add("R");
                            nextChar = '#';
                        } else if (grid[y + 1][x] == '#') {
                            direction = Direction.DOWN;
                            path.add("L");
                            nextChar = '#';
                        }
                        break;
                    case RIGHT:
                        if (grid[y - 1][x] == '#') {
                            direction = Direction.UP;
                            path.add("L");
                            nextChar = '#';
                        } else if (grid[y + 1][x] == '#') {
                            direction = Direction.DOWN;
                            path.add("R");
                            nextChar = '#';
                        }
                }
            }
            if (nextChar == '.') {
                break;
            }
        }
        path.remove(0);
        return String.join(",", path);
    }
}
