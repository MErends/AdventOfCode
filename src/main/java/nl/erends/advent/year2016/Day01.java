package nl.erends.advent.year2016;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day01 extends AbstractProblem<String, Integer> {

    public static void main(String[] args) {
        new Day01().setAndSolve(Util.readLine(2016, 1));
    }

    @Override
    public Integer solve1() {
        List<String> visited = new ArrayList<>();
        visited.add("0_0");
        int x = 0;
        int y = 0;
        Direction orientation = Direction.PLUSY;
        List<String> directions = Arrays.asList(input.split(", "));
        for (String direction : directions) {
            orientation = updateDirection(orientation, direction.substring(0, 1));
            int steps = Integer.parseInt(direction.substring(1));
            switch (orientation) {
                default:
                case PLUSX:
                    while (steps != 0) {
                        x++;
                        steps--;
                        checkVisited(visited, x, y);
                    }
                    break;
                case PLUSY:
                    while (steps != 0) {
                        y++;
                        steps--;
                        checkVisited(visited, x, y);
                    }
                    break;
                case MINX:
                    while (steps != 0) {
                        x--;
                        steps--;
                        checkVisited(visited, x, y);
                    }
                    break;
                case MINY:
                    while (steps != 0) {
                        y--;
                        steps--;
                        checkVisited(visited, x, y);
                    }
                    break;
            }
        }
        return Math.abs(x) + Math.abs(y);
    }

    private void checkVisited(List<String> visited, int x, int y) {
        String position = x + "_" + y;
        if (visited.contains(position) && answer2 == null) {
            answer2 = Math.abs(x) + Math.abs(y);
        } else {
            visited.add(position);
        }
    }

    private Direction updateDirection(Direction direction, String command) {
        if (command.equals("L")) {
            switch (direction) {
                case PLUSX:
                    return Direction.PLUSY;
                case PLUSY:
                    return Direction.MINX;
                case MINX:
                    return Direction.MINY;
                case MINY:
                    return Direction.PLUSX;
            }
        } else {
            switch (direction) {
                case PLUSX:
                    return Direction.MINY;
                case PLUSY:
                    return Direction.PLUSX;
                case MINX:
                    return Direction.PLUSY;
                case MINY:
                    return Direction.MINX;
            }
        }
        return Direction.PLUSX;
    }


    private enum Direction {
        PLUSX,
        PLUSY,
        MINX,
        MINY
    }
}