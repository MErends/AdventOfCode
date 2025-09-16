package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day19 extends AbstractProblem<List<String>, String> {
    
    private int x;
    private int y;
    private char[][] maze;
    private Direction direction;

    static void main() {
        new Day19().setAndSolve(Util.readInput(2017, 19));
    }
    
    @Override
    public String solve1() {
        readMaze();
        direction = Direction.DOWN;
        findStartX();
        StringBuilder word = new StringBuilder();
        int stepsTaken = 0;
        while (true) {
            stepsTaken++;
            updatePosition();
            if (maze[y][x] == ' ') {
                break;
            }
            if (!"|-+".contains("" + maze[y][x])) {
                word.append(maze[y][x]);
            }
            makeTurn();
        }
        answer2 = Integer.toString(stepsTaken);
        return word.toString();
    }

    private void makeTurn() {
        switch (direction) {
            case UP -> turnFromUp();
            case DOWN -> turnFromDown();
            case LEFT -> turnFromLeft();
            case RIGHT -> turnFromRight();
        }
    }

    private void turnFromRight() {
        if (maze[y][x + 1] == ' ') {
            if (maze[y - 1][x] != ' ') {
                direction = Direction.UP;
            } else if (maze[y + 1][x] != ' ') {
                direction = Direction.DOWN;
            }
        }
    }

    private void turnFromLeft() {
        if (maze[y][x - 1] == ' ') {
            if (maze[y - 1][x] != ' ') {
                direction = Direction.UP;
            } else if (maze[y + 1][x] != ' ') {
                direction = Direction.DOWN;
            }
        }
    }

    private void turnFromDown() {
        if (maze[y + 1][x] == ' ') {
            if (maze[y][x - 1] != ' ') {
                direction = Direction.LEFT;
            } else if (maze[y][x + 1] != ' ') {
                direction = Direction.RIGHT;
            }
        }
    }

    private void turnFromUp() {
        if (maze[y - 1][x] == ' ') {
            if (maze[y][x - 1] != ' ') {
                direction = Direction.LEFT;
            } else if (maze[y][x + 1] != ' ') {
                direction = Direction.RIGHT;
            }
        }
    }

    private void updatePosition() {
        switch (direction) {
            case UP -> y--;
            case DOWN -> y++;
            case LEFT -> x--;
            case RIGHT -> x++;
        }
    }

    private void findStartX() {
        for (; x < maze[y].length; x++) {
            if (maze[y][x] == '|') {
                break;
            }
        }
    }

    private void readMaze() {
        maze = new char[input.size()][];
        for (int tempY = 0; tempY < maze.length; tempY++) {
            maze[tempY] = new char[input.get(tempY).length()];
            for (int tempX = 0; tempX< maze[tempY].length; tempX++) {
                maze[tempY][tempX] = input.get(tempY).charAt(tempX);
            }
        }
    }
}
