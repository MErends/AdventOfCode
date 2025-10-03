package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Util;

import java.util.List;

/**
 * --- Day 22: Monkey Map ---
 * <p>As you walk, the monkeys explain that the grove is protected by a force
 * field. To pass through the force field, you have to enter a password; doing
 * so involves tracing a specific path on a strangely-shaped board. Follow the
 * path given in the monkeys' notes. What is the final password?
 * <p><a href="https://adventofcode.com/2022/day/22">2022 Day 22</a>
 */
public class Day22 extends AbstractProblem<List<String>, Number> {

    private char[][] board;

    void main() {
        new Day22().setAndSolve(Util.readInput(2022, 22));
    }

    @Override
    public Number solve1() {
        board = new char[input.size() - 2][];
        for (int i = 0; i < input.size() - 2; i++) {
            board[i] = input.get(i).toCharArray();
        }

        int startX = 0;
        while (board[0][startX] == ' ') {
            startX++;
        }
        Position me = new Position(startX, 0, Direction.UP);
        String instructions = 'R' + input.getLast();
        int pointer = 0;
        while (pointer < instructions.length()) {
            if (instructions.charAt(pointer++) == 'R') {
                me.turnRight();
            } else {
                me.turnLeft();
            }
            int l = pointer;
            while (l < instructions.length() && Character.isDigit(instructions.charAt(l))) {
                l++;
            }
            int steps = Integer.parseInt(instructions.substring(pointer, l));
            pointer = l;
            int step = 0;
            boolean moved;
            do {
                moved = part2 ? me.hyperMove() : me.move();
                step++;
            } while (moved && step < steps);
        }
        int password = switch (me.direction) {
            case UP -> 3;
            case DOWN -> 1;
            case LEFT -> 2;
            case RIGHT -> 0;
        };
        return password + 1000 * (me.y + 1) + 4 * (me.x + 1);
    }

    private class Position {
        Face face = Face.TOP;
        int x;
        int y;
        Direction direction;

        Position(int x, int y, Direction direction) {
            this.x = x;
            this.y = y;
            this.direction = direction;
        }

        boolean move() {
            int tempX = x + direction.dx();
            int tempY = y + direction.dy();
            if (tempX < 0 || tempY < 0 || tempY >= board.length || tempX >= board[tempY].length || board[tempY][tempX] == ' ') {
                switch (direction) {
                    case UP -> {
                        tempY = board.length - 1;
                        while (board[tempY].length <= tempX) tempY--;
                    }
                    case DOWN -> {
                        tempY = 0;
                        while (board[tempY].length <= tempX) tempY++;
                    }
                    case LEFT -> tempX = board[tempY].length - 1;
                    case RIGHT -> tempX = 0;
                }
                while (board[tempY][tempX] == ' ') {
                    tempX += direction.dx();
                    tempY += direction.dy();
                }
            }
            if (board[tempY][tempX] == '.') {
                y = tempY;
                x = tempX;
                return true;
            }
            return false;
        }

        boolean hyperMove() {
            int tempX = x + direction.dx();
            int tempY = y + direction.dy();
            Face tempFace = face;
            Direction tempD = direction;
            if (tempX < 0 || tempY < 0 || tempY >= board.length || tempX >= board[tempY].length
                    || board[tempY][tempX] == ' ' || x/50 != tempX/50 || y/50 != tempY/50) {
                switch (face) {
                    case TOP -> {
                        switch (direction) {
                            case DOWN -> tempFace = Face.FRONT;
                            case LEFT -> {
                                tempFace = Face.LEFT;
                                tempD = Direction.RIGHT;
                                tempX = 0;
                                tempY = -y + 149;
                            }
                            case UP -> {
                                tempFace = Face.BACK;
                                tempD = Direction.RIGHT;
                                tempX = 0;
                                tempY = x + 100;
                            }
                            case RIGHT -> tempFace = Face.RIGHT;
                        }
                    }
                    case FRONT -> {
                        switch (direction) {
                            case UP -> tempFace = Face.TOP;
                            case DOWN -> tempFace = Face.BOTTOM;
                            case LEFT -> {
                                tempFace = Face.LEFT;
                                tempD = Direction.DOWN;
                                tempX = y - 50;
                                tempY = 100;
                            }
                            case RIGHT -> {
                                tempFace = Face.RIGHT;
                                tempD = Direction.UP;
                                tempX = y + 50;
                                tempY = 49;
                            }
                        }
                    }
                    case LEFT -> {
                        switch (direction) {
                            case UP -> {
                                tempFace = Face.FRONT;
                                tempD = Direction.RIGHT;
                                tempX = 50;
                                tempY = x + 50;
                            }
                            case DOWN -> tempFace = Face.BACK;
                            case LEFT -> {
                                tempFace = Face.TOP;
                                tempD = Direction.RIGHT;
                                tempX = 50;
                                tempY = -y + 149;
                            }
                            case RIGHT -> tempFace = Face.BOTTOM;
                        }
                    }
                    case RIGHT -> {
                        switch (direction) {
                            case UP -> {
                                tempFace = Face.BACK;
                                tempX = x - 100;
                                tempY = 199;
                            }
                            case DOWN -> {
                                tempFace = Face.FRONT;
                                tempD = Direction.LEFT;
                                tempX = 99;
                                tempY = x - 50;
                            }
                            case LEFT -> tempFace = Face.TOP;
                            case RIGHT -> {
                                tempFace = Face.BOTTOM;
                                tempD = Direction.LEFT;
                                tempX = 99;
                                tempY = -y + 149;
                            }
                        }
                    }
                    case BOTTOM -> {
                        switch (direction) {
                            case UP -> tempFace = Face.FRONT;
                            case DOWN -> {
                                tempFace = Face.BACK;
                                tempD = Direction.LEFT;
                                tempX = 49;
                                tempY = x + 100;
                            }
                            case LEFT -> tempFace = Face.LEFT;
                            case RIGHT -> {
                                tempFace = Face.RIGHT;
                                tempD = Direction.LEFT;
                                tempX = 149;
                                tempY = -y + 149;
                            }
                        }
                    }
                    case BACK -> {
                        switch (direction) {
                            case UP -> tempFace = Face.LEFT;
                            case DOWN -> {
                                tempFace = Face.RIGHT;
                                tempX = x + 100;
                                tempY = 0;
                            }
                            case LEFT -> {
                                tempFace = Face.TOP;
                                tempD = Direction.DOWN;
                                tempX = y - 100;
                                tempY = 0;
                            }
                            case RIGHT -> {
                                tempFace = Face.BOTTOM;
                                tempD = Direction.UP;
                                tempX = y - 100;
                                tempY = 149;
                            }
                        }
                    }
                }
            }
            if (board[tempY][tempX] == '.') {
                y = tempY;
                x = tempX;
                direction = tempD;
                face = tempFace;
                return true;
            }
            return false;
        }

        void turnLeft() {
            direction = direction.turnLeft();
        }

        void turnRight() {
            direction = direction.turnRight();
        }

        @Override
        public String toString() {
            return "Position{" +
                    "face=" + face +
                    ", x=" + x +
                    ", y=" + y +
                    ", direction=" + direction +
                    '}';
        }
    }

    private enum Face {
        TOP,
        FRONT,
        LEFT,
        RIGHT,
        BOTTOM,
        BACK
    }
}
