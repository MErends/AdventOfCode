package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Coord;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

import static nl.erends.advent.util.Direction.UP;

/**
 * <h1>--- Day 6: Guard Gallivant ---</h1>
 * <p>A single guard is patrolling this part of the lab. Lab guards in 1518
 * follow a very strict patrol protocol which involves repeatedly following
 * these steps. If there is something directly in front of you, turn right 90
 * degrees. Otherwise, take a step forward. Predict the path of the guard. How
 * many distinct positions will the guard visit before leaving the mapped area?
 * </p>
 * <p><a href="https://adventofcode.com/2024/day/6">2024 Day 6</a></p>
 */
public class Day06 extends AbstractProblem<List<String>, Integer> {

    char[][] grid;
    Coord start;

    public static void main(String[] args) {
        new Day06().setAndSolve(Util.readInput(2024, 6));
    }

    @Override
    protected Integer solve1() {
        grid = readInput();
        start = getStart(grid);
        Coord guard = new Coord(start);
        Direction d = UP;
        boolean busy = true;
        while (busy) {
            grid[guard.y][guard.x] = 'X';
            switch (getSafeGrid(grid, guard.x + d.dx(), guard.y + d.dy())) {
                case '\0' -> busy = false;
                case '#' -> d = d.turnRight();
                default -> guard.addDirection(d);
            }
        }
        int score = 0;
        for (char[] line : grid) {
            for (char c : line) {
                if (c == 'X') {
                    score++;
                }
            }
        }
        return score;
    }

    @Override
    public Integer solve2() {
        int obstructions = 0;
        for (Coord wall : getPossibleWalls(grid)) {
            char[][] newGrid = readInput();
            grid[wall.y][wall.x] = '#';
            Coord guard = new Coord(start);
            Direction d = UP;
            int[][] passes = new int[newGrid.length][newGrid[0].length];
            boolean busy = true;
            while (busy) {
                if (passes[guard.y][guard.x] == 4) { // Went by in all directions?!
                    obstructions++;
                    break;
                }
                passes[guard.y][guard.x]++;
                switch (getSafeGrid(newGrid, guard.x + d.dx(), guard.y + d.dy())) {
                    case '\0' -> busy = false;
                    case '#' -> d = d.turnRight();
                    default -> guard.addDirection(d);
                }
            }
        }
        return obstructions;
    }

    private char getSafeGrid(char[][] grid, int x, int y) {
        if (y < 0 || y >= grid.length || x < 0 || x >= grid[y].length) {
            return '\0';
        }
        return grid[y][x];
    }

    private char[][] readInput() {
        grid = new char[input.size()][];
        for (int y = 0; y < input.size(); y++) {
            grid[y] = input.get(y).toCharArray();
        }
        return grid;
    }

    private Coord getStart(char[][] grid) {
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == '^'){
                    return new Coord(x, y);
                }
            }
        }
        throw new IllegalStateException("No start found!");
    }

    private List<Coord> getPossibleWalls(char[][] grid) {
        List<Coord> possibleWalls = new ArrayList<>();
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid[y].length; x++) {
                if (grid[y][x] == 'X') {
                    possibleWalls.add(new Coord(x, y));
                }
            }
        }
        return possibleWalls;
    }
}
