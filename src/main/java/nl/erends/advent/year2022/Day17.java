package nl.erends.advent.year2022;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --- Day 17
 * <p>
 * <p><a href="https://adventofcode.com/2022/day/17">2022 Day 17</a>
 */
public class Day17 extends AbstractProblem<String, Number> {

    private char[][] field;
    private List<char[][]> rocks;
    private int rockHeight;
    private long offset;
    private long movementId;

    public static void main(String[] args) {
        new Day17().setAndSolve(Util.readLine(2022, 17));
    }

    @Override
    protected Long solve1() {
        return getRockheight(2022);
    }

    @Override
    public Long solve2() {
        return getRockheight(1_000_000_000_000L);
    }

    private long getRockheight(long rockCount) {
        loadRocks();
        rockHeight = 0;
        offset = 0;
        field = new char[200][7];
        boolean skipped = false;
        movementId = 0;
        Map<Integer, Long> moveHeightMap = new HashMap<>();
        Map<Integer, Long> moveRockIdMap = new HashMap<>();
        List<String> repeatPattern = new ArrayList<>();
        long rockID = 0;
        while (rockID < rockCount) {
            int normalRockId = (int) (rockID % rocks.size());
            int normalMoveId = (int) (movementId % input.length());
            if (!skipped && normalRockId == 0) {
                if (moveHeightMap.containsKey(normalMoveId)) {
                    long dHeight = rockHeight + offset - moveHeightMap.get(normalMoveId);
                    long dRockID = rockID - moveRockIdMap.get(normalMoveId);
                    String repeat = dRockID + "," + dHeight;
                    if (repeatPattern.contains(repeat)) {
                        long remaining = rockCount - rockID;
                        long skipCount = remaining / dRockID;
                        rockID += dRockID * skipCount;
                        offset += dHeight * skipCount;
                        skipped = true;
                    } else {
                        repeatPattern.add(repeat);
                    }
                } else {
                    moveHeightMap.put(normalMoveId, rockHeight + offset);
                    moveRockIdMap.put(normalMoveId, rockID);
                }
            }
            placeRock(normalRockId);
            rockID++;
        }
        return rockHeight + offset;
    }

    private void placeRock(int normalRockId) {
        char[][] rock = rocks.get(normalRockId);
        int x = 2;
        int y = rockHeight + 3;
        while (true) {
            char move = input.charAt((int) (movementId++ % input.length()));
            if (move == '<' && placePossible(rock, x - 1, y)) {
                x--;
            } else if (move == '>' && placePossible(rock, x + 1, y)) {
                x++;
            }
            if (placePossible(rock, x, y - 1)) {
                y--;
            } else {
                writeRock(rock, x, y);
                break;
            }
        }
    }

    private void writeRock(char[][] rock, int x, int y) {
        for (int dy = 0; dy < rock.length; dy++) {
            for (int dx = 0; dx < rock[dy].length; dx++) {
                if (rock[dy][dx] == '#') {
                    field[y+dy][x+dx] = '#';
                }
            }
        }
        updateRockHeight();
    }

    private void updateRockHeight() {
        while (true) {
            char[] line = field[rockHeight];
            boolean lineClear = true;
            for (char c : line) {
                if (c == '#') {
                    lineClear = false;
                    break;
                }
            }
            if (lineClear) {
                break;
            } else {
                rockHeight++;
            }
        }
        if (rockHeight > 150) {
            for (int row = 0; row < 100; row++) {
                field[row] = field[row + 100];
                field[row + 100] = new char[7];
            }
            rockHeight -= 100;
            offset += 100;
        }
    }

    private boolean placePossible(char[][] rock, int x, int y) {
        if (x < 0 || y < 0 || x + rock[0].length > field[0].length) {
            return false;
        }
        for (int dy = 0; dy < rock.length; dy++) {
            for (int dx = 0; dx < rock[dy].length; dx++) {
                if (field[y+dy][x+dx] == '#' && rock[dy][dx] == '#') {
                    return false;
                }
            }
        }
        return true;
    }

    private void loadRocks() {
        rocks = new ArrayList<>();
        rocks.add(new char[][]{{'#', '#', '#', '#'}});
        rocks.add(new char[][]{{'.', '#', '.'}, {'#', '#', '#'}, {'.', '#', '.'}});
        rocks.add(new char[][]{{'#', '#', '#'}, {'.', '.', '#'}, {'.', '.', '#'}}); // flipped
        rocks.add(new char[][]{{'#'}, { '#'}, { '#'}, { '#'}});
        rocks.add(new char[][]{{'#', '#'}, { '#', '#'}});
    }
}
