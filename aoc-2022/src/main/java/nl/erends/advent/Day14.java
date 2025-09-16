package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

/**
 * --- Day 14: Regolith Reservoir ---
 * <p>The distress signal leads you behind a giant waterfall! There seems to be
 * a large cave system here, and the signal definitely leads further inside.
 * Sand begins pouring into the cave! If you don't quickly figure out where the
 * sand is going, you could quickly become trapped! Using your scan, simulate
 * the falling sand. How many units of sand come to rest before sand starts
 * flowing into the abyss below?
 * <p><a href="https://adventofcode.com/2022/day/14">2022 Day 14</a>
 */
public class Day14 extends AbstractProblem<List<String>, Integer> {

    private char[][] cave;
    private int xOffset = 0;
    private List<Line> horizontals;
    private List<Line> verticals;

    static void main() {
        new Day14().setAndSolve(Util.readInput(2022, 14));
    }

    @Override
    protected Integer solve1() {
        readLines();
        createCave();
        return getTotalSand();
    }

    @Override
    public Integer solve2() {
        readLines();
        int yMax = verticals.stream()
                .mapToInt(v -> v.end)
                .max().orElseThrow();
        horizontals.add(new Line(497 - yMax, 503 + yMax, yMax + 2));
        createCave();
        return getTotalSand();
    }

    private void readLines() {
        horizontals = new ArrayList<>();
        verticals = new ArrayList<>();
        for (String line : input) {
            String[] coords = line.split(" -> ");
            for (int i = 0; i < coords.length - 1; i++) {
                int startX = Integer.parseInt(coords[i].split(",")[0]);
                int startY = Integer.parseInt(coords[i].split(",")[1]);
                int endX = Integer.parseInt(coords[i + 1].split(",")[0]);
                int endY = Integer.parseInt(coords[i + 1].split(",")[1]);
                if (startX == endX) {
                    verticals.add(new Line(Math.min(startY, endY), Math.max(startY, endY), startX));
                } else {
                    horizontals.add(new Line(Math.min(startX, endX), Math.max(startX, endX), startY));
                }
            }
        }
    }

    private void createCave() {
        int yMax = horizontals.stream()
                .mapToInt(v -> v.fixed)
                .max().orElseThrow();
        int xMin = horizontals.stream()
                .mapToInt(h -> h.start)
                .min().orElseThrow();
        int xMax = horizontals.stream()
                .mapToInt(h -> h.end)
                .max().orElseThrow();
        cave = new char[yMax + 1][xMax - xMin + 3];
        xOffset = xMin - 1;
        for (Line horizontal : horizontals) {
            for (int x = horizontal.start; x <= horizontal.end; x++) {
                cave[horizontal.fixed][x - xOffset] = '#';
            }
        }
        for (Line vertical : verticals) {
            for (int y = vertical.start; y <= vertical.end; y++) {
                cave[y][vertical.fixed - xOffset] = '#';
            }
        }
    }

    private int getTotalSand() {
        int sandDropped = 0;
        while (true) {
            boolean dropped = dropSand();
            if (dropped) {
                sandDropped++;
            } else {
                break;
            }
        }
        return sandDropped;
    }

    private boolean dropSand(){
        int x = 500 - xOffset;
        int y = 0;
        if (cave[y][x] == 'o') {
            return false;
        }
        while (y < cave.length - 1) {
            if (cave[y + 1][x] == '\0') {
                y++;
            } else if (cave[y + 1][x - 1] == '\0') {
                y++;
                x--;
            } else if (cave[y + 1][x + 1] == '\0') {
                y++;
                x++;
            } else {
                cave[y][x] = 'o';
                return true;
            }
        }
        return false;
    }

    private record Line(int start, int end, int fixed) {}
}
