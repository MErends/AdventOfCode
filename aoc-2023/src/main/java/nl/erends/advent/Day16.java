package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <h1>--- Day 16: The Floor Will Be Lava ---</h1>
 * <p>Finally, as you approach what must be the heart of the mountain, you see a
 * bright light in a cavern up ahead. There, you discover that the beam of light
 * you so carefully focused is emerging from the cavern wall closest to the
 * facility and pouring all of its energy into a contraption on the opposite
 * side. Upon closer inspection, the contraption appears to be a flat,
 * two-dimensional square grid containing empty space (.), mirrors (/ and \),
 * and splitters (| and -).Find the initial beam configuration that energizes
 * the largest number of tiles; how many tiles are energized in that
 * configuration?</p>
 * <p><a href="https://adventofcode.com/2023/day/16">2023 Day 16</a></p>
 */
public class Day16 extends AbstractProblem<List<String>, Number> {

    List<Beam> beams;
    char[][] grid;
    char[][] energizedTiles;
    Set<String> splittersDone;


    void main() {
        new Day16().setAndSolve(Util.readInput(2023, 16));
    }

    @Override
    protected Number solve1() {
        return solve(new Beam(-1, 0, Direction.RIGHT));
    }

    @Override
    public Number solve2() {
        int highLava = Integer.MIN_VALUE;
        for (int startY = 0; startY < input.size(); startY++) {
            highLava = Math.max(highLava, solve(new Beam(-1, startY, Direction.RIGHT)));
            highLava = Math.max(highLava, solve(new Beam(input.get(startY).length(), startY, Direction.LEFT)));
        }
        for (int startX = 0; startX < input.getFirst().length(); startX++) {
            highLava = Math.max(highLava, solve(new Beam(startX, -1, Direction.DOWN)));
            highLava = Math.max(highLava, solve(new Beam(startX, input.size(), Direction.UP)));
        }
        return highLava;
    }

    private int solve(Beam startBeam) {
        grid = new char[input.size()][];
        beams = new ArrayList<>();
        for (int line = 0; line < input.size(); line++) {
            grid[line] = input.get(line).toCharArray();
        }
        energizedTiles = new char[grid.length][grid[0].length];
        splittersDone = new HashSet<>();
        beams.add(startBeam);
        while (!beams.isEmpty()) {
            beams.removeFirst().resolve();
        }
        int sum = 0;
        for (char[] line : energizedTiles) {
            for (char c : line) {
                if (c == '#') {
                    sum++;
                }
            }
        }
        return sum;
    }

    private class Beam {
        int x;
        int y;
        Direction d;

        public Beam(int x, int y, Direction d) {
            this.x = x;
            this.y = y;
            this.d = d;
        }

        private void resolve() {
            while (x + d.dx() >= 0 && y + d.dy() >= 0 && y + d.dy() < grid.length && x + d.dx() < grid[y + d.dy()].length) {
                x += d.dx();
                y += d.dy();
                energizedTiles[y][x] = '#';
                if (beamResolved()) return;
            }
        }

        private boolean beamResolved() {
            switch (grid[y][x]) {
                case '/' -> d = d.isHorizontal() ? d.turnLeft() : d.turnRight();
                case '\\' -> d = d.isHorizontal() ? d.turnRight() : d.turnLeft();
                case '|' -> {
                    if (d.isHorizontal()) {
                        if (splittersDone.contains(x + "," + y)) return true;
                        beams.add(new Beam(x, y, d.turnLeft()));
                        d = d.turnRight();
                        splittersDone.add(x + "," + y);
                    }
                }
                case '-' -> {
                    if (d.isVertical()) {
                        if (splittersDone.contains(x + "," + y)) return true;
                        beams.add(new Beam(x, y, d.turnLeft()));
                        d = d.turnRight();
                        splittersDone.add(x + "," + y);
                    }
                }
                default -> {
                    //No action
                }
            }
            return false;
        }
    }
}
