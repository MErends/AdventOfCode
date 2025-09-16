package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * <h1>--- Day 18: Lavaduct Lagoon ---</h1>
 * <p>To catch up with the large backlog of parts requests, the factory will
 * need a large supply of lava for a while; the Elves have already started
 * creating a large lagoon nearby for this purpose. The digger starts in a 1
 * meter cube hole in the ground. They then dig the specified number of meters
 * up (U), down (D), left (L), or right (R), clearing full 1 meter cubes as they
 * go. The Elves are concerned the lagoon won't be large enough; if they follow
 * their dig plan, how many cubic meters of lava could it hold?</p>
 * <p><a href="https://adventofcode.com/2023/day/18">2023 Day 18</a></p>
 */
public class Day18 extends AbstractProblem<List<String>, Number> {

    char[][] grid;

    static void main() {
        new Day18().setAndSolve(Util.readInput(2023, 18));
    }
    @Override
    protected Number solve1() {
        List<Direction> directions = new ArrayList<>();
        List<Integer> amounts = new ArrayList<>();
        for (String line : input) {
            directions.add(Direction.getDirection(line.charAt(0)));
            amounts.add(Integer.parseInt(line.split(" ")[1]));
        }
        int x = 0;
        int y = 0;
        int minX = 0;
        int maxX = 0;
        int minY = 0;
        int maxY = 0;
        for (int i = 0; i < input.size(); i++) {
            Direction d = directions.get(i);
            int amount = amounts.get(i);
            for (int j = 0; j < amount; j++) {
                x += d.dx();
                y += d.dy();
            }
            minX = Math.min(minX, x);
            maxX = Math.max(maxX, x);
            minY = Math.min(minY, y);
            maxY = Math.max(maxY, y);
        }
        x = -minX;
        y = -minY + 1;
        grid = new char[maxY - minY + 3][maxX - minX + 1];
        grid[y][x] = '#';
        for (int i = 0; i < input.size(); i++) {
            Direction d = directions.get(i);
            int amount = amounts.get(i);
            for (int j = 0; j < amount; j++) {
                x += d.dx();
                y += d.dy();
                grid[y][x] = '#';
            }
        }

        for (y = 1; y < grid.length - 1; y++) {
            fillLine(y);
        }
        int lagoonCount = 0;
        for (char[] line : grid) {
            for (char c : line) {
                if (c != '\0') {
                    lagoonCount++;
                }
            }
        }
        return lagoonCount;
    }

    private void fillLine(int y) {
        int x;
        boolean inside = false;
        boolean onTrench = false;
        boolean lineFromAbove = false;
        for (x = 0; x < grid[y].length; x++) {
            if (grid[y][x] != '#') {
                if (inside) {
                    grid[y][x] = '~';
                }
                continue;
            }
            char above = grid[y - 1][x];
            char below = grid[y + 1][x];
            if (above == '#' && below == '#') {
                inside = !inside;
            } else if (above == '#') {
                if (onTrench) {
                    onTrench = false;
                    if (!lineFromAbove) {
                        inside = !inside;
                    }
                } else {
                    onTrench = true;
                    lineFromAbove = true;
                }
            } else if (below == '#') {
                if (onTrench) {
                    onTrench = false;
                    if (lineFromAbove) {
                        inside = !inside;
                    }
                } else {
                    onTrench = true;
                    lineFromAbove = false;
                }
            }
        }
    }

    @Override
    public Number solve2() {
        long area = 1;
        List<Long> xCoords = new ArrayList<>();
        List<Long> yCoords = new ArrayList<>();
        long x = 0;
        long y = 0;
        xCoords.add(0L);
        yCoords.add(0L);
        for (String line : input) {
            String instruction = line.split(" ")[2].substring(2, 8);
            long amount = Long.parseLong(instruction.substring(0, 5), 16);
            Direction d = switch (instruction.charAt(5)) {
                case '0' -> Direction.RIGHT;
                case '1' -> Direction.DOWN;
                case '2' -> Direction.LEFT;
                default -> Direction.UP;
            };
            if (d == Direction.UP || d == Direction.RIGHT) {
                area += amount;
            }
            x += d.dx() * amount;
            y += d.dy() * amount;
            xCoords.add(x);
            yCoords.add(y);
        }
        area *= 2;
        for (int i = 0; i < xCoords.size() - 1; i++) {
            area += (xCoords.get(i) * yCoords.get(i + 1));
            area -= (xCoords.get(i + 1) * yCoords.get(i));
        }
        return area / 2;
    }
}
