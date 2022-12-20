package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Direction;
import nl.erends.advent.util.Util;

import java.util.List;

import static nl.erends.advent.util.Util.ASCII_OFFSET;

/**
 * --- Day 8: Treetop Tree House ---
 * <p>The expedition comes across a peculiar patch of tall trees all planted
 * carefully in a grid. First, determine whether there is enough tree cover here
 * to keep a tree house hidden. To do this, you need to count the number of
 * trees that are visible from outside the grid when looking directly along a
 * row or column. What is the highest scenic score possible for any tree?
 * <p><a href="https://adventofcode.com/2022/day/8">2022 Day 8</a>
 */
public class Day08 extends AbstractProblem<List<String>, Integer> {

    private int[][] tree;
    private int size;

    public static void main(String[] args) {
        new Day08().setAndSolve(Util.readInput(2022, 8));
    }

    @Override
    protected Integer solve1() {
        size = input.size();
        tree = new int[size][size];
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                tree[y][x] = input.get(y).charAt(x) - ASCII_OFFSET;
            }
        }
        int visibleCount = 0;
        answer2 = Integer.MIN_VALUE;
        for (int y = 0; y < size; y++) {
            for (int x = 0; x < size; x++) {
                answer2 = Math.max(answer2, getScenicScore(x, y));
                if (isVisible(Direction.UP, x, y) || isVisible(Direction.DOWN, x, y)
                        || isVisible(Direction.LEFT, x, y) || isVisible(Direction.RIGHT, x, y)) {
                    visibleCount++;
                }
            }
        }
        return visibleCount;
    }

    private boolean isVisible(Direction d, int x, int y) {
        int currentSize = tree[y][x];
        x += d.dx();
        y += d.dy();
        while (x >= 0 && x < size && y >= 0 && y < size) {
            if (tree[y][x] >= currentSize) {
                return false;
            }
            x += d.dx();
            y += d.dy();
        }
        return true;
    }

    private int getScenicScore(int x, int y) {
        int currentTree = tree[y][x];
        int currentScene = 1;
        for (Direction d : Direction.values()) {
            int cx = x + d.dx();
            int cy = y + d.dy();
            int treesSeen = 0;
            while(cx >= 0 && cx < size && cy >= 0 && cy < size) {
                treesSeen++;
                if (tree[cy][cx] >= currentTree) {
                    break;
                }
                cx += d.dx();
                cy += d.dy();
            }
            currentScene *= treesSeen;
        }
        return currentScene;
    }
}
