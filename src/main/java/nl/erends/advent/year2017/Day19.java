package nl.erends.advent.year2017;

import nl.erends.advent.util.FileIO;

import java.util.List;

import static nl.erends.advent.year2017.Day19.Direction.*;
//             |
//             |  +--+
//             A  |  C
//         F---|----E|--+
//             |  |  |  D
//             +B-+  +--+

public class Day19 {


    public static void main(String[] args) {
        List<String> input = FileIO.getFileAsList("2017day19.txt");
        char[][] maze = new char[input.size()][];
        for (int y = 0; y < maze.length; y++) {
            maze[y] = new char[input.get(y).length()];
            for (int x = 0; x< maze[y].length; x++) {
                maze[y][x] = input.get(y).charAt(x);
            }
        }
        int y = 0;
        int x = 0;
        Direction direction = DOWN;
        for (; x < maze[y].length; x++) {
            if (maze[y][x] == '|') {
                break;
            }
        }
        StringBuilder word = new StringBuilder();
        int stepsTaken = 0;
        while (true) {
            stepsTaken++;
            switch (direction) {
                case UP:
                    y--;
                    break;
                case DOWN:
                    y++;
                    break;
                case LEFT:
                    x--;
                    break;
                case RIGHT:
                    x++;
                    break;
            }
            if (maze[y][x] == ' ') {
                break;
            }
            if (!"|-+".contains("" + maze[y][x])) {
                word.append(maze[y][x]);
            }
            switch (direction) {
                case UP:
                    if (maze[y - 1][x] == ' ') {
                        if (maze[y][x - 1] != ' ') {
                            direction = LEFT;
                        } else if (maze[y][x + 1] != ' ') {
                            direction = RIGHT;
                        }
                    }
                    break;
                case DOWN:
                    if (maze[y + 1][x] == ' ') {
                        if (maze[y][x - 1] != ' ') {
                            direction = LEFT;
                        } else if (maze[y][x + 1] != ' ') {
                            direction = RIGHT;
                        }
                    }
                    break;
                case LEFT:
                    if (maze[y][x - 1] == ' ') {
                        if (maze[y - 1][x] != ' ') {
                            direction = UP;
                        } else if (maze[y + 1][x] != ' ') {
                            direction = DOWN;
                        }
                    }
                    break;
                case RIGHT:
                    if (maze[y][x + 1] == ' ') {
                        if (maze[y - 1][x] != ' ') {
                            direction = UP;
                        } else if (maze[y + 1][x] != ' ') {
                            direction = DOWN;
                        }
                    }
                    break;
            }
        }
        System.out.println(word);
        System.out.println(stepsTaken);
    }


    public enum Direction {
        UP, DOWN, LEFT, RIGHT
    }
}