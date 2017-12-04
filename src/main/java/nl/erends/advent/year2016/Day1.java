package nl.erends.advent.year2016;

import nl.erends.advent.util.FileIO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day1 {

    public static void main(String[] args) {
        List<String> visited = new ArrayList<>();
        visited.add("0_0");
        int x = 0;
        int y = 0;
        Direction orientation = Direction.PLUSY;
        String line = FileIO.getFileAsList("C:/Users/marke/IdeaProjects/Adventofcode/resource/2016day1.txt").get(0);
        List<String> directions = Arrays.asList(line.split(", "));
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
        System.out.println(Math.abs(x) + Math.abs(y));
    }

    private static void checkVisited(List<String> visited, int x, int y) {
        String position = x + "_" + y;
        if (visited.contains(position)) {
            System.out.println(Math.abs(x) + Math.abs(y));
            System.exit(0);
        } else {
            visited.add(position);
        }
    }

    private static Direction updateDirection(Direction direction, String command) {
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
}


enum Direction {
    PLUSX,
    PLUSY,
    MINX,
    MINY
}