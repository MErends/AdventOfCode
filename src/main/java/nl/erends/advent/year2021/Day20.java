package nl.erends.advent.year2021;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.Arrays;
import java.util.List;

/**
 * --- Day 20: Trench Map ---
 * <p>With the scanners fully deployed, you turn their attention to mapping the
 * floor of the ocean trench. When you get back the image from the scanners, it
 * seems to just be random noise. Perhaps you can combine an image enhancement
 * algorithm and the input image (your puzzle input) to clean it up a little.
 * <p><a href="https://adventofcode.com/2021/day/20">2021 Day 20</a>
 */
public class Day20 extends AbstractProblem<List<String>,Integer> {
    
    private char[][] grid;
    private String lookup;
    private int iteration = 0;
    private char infinityPixel = '.';
    
    public static void main(String[] args) {
        new Day20().setAndSolve(Util.readInput(2021, 20));
    }

    @Override
    protected Integer solve1() {
        lookup = input.remove(0);
        input.remove(0);
        int iterations = 50;
        int length = input.size();
        grid = new char[length + 4 * iterations][length + 4 * iterations];
        Arrays.stream(grid).forEach(l -> Arrays.fill(l, '.'));
        for (int y = 0; y < length; y++) {
            for (int x = 0; x < length; x++) {
                grid[y + 2 * iterations][x + 2 * iterations] = input.get(y).charAt(x);
            }
        }
        int answer1 = 0;
        for (int i = 0; i< iterations; i++) {
            if (iteration == 2) {
                answer1 = count();
            }
            iterate();
        }
        answer2 = count();
        return answer1;
    }
    
    private void iterate() {
        char[][] newGrid = new char[grid.length][grid.length];
        Arrays.stream(newGrid).forEach(l -> Arrays.fill(l, '.'));
        for (int y = 0; y < grid.length; y++) {
            for (int x = 0; x < grid.length; x++) {
                newGrid[y][x] = enhance(x, y);
            }
        }
        grid = newGrid;
        infinityPixel = infinityPixel == '.' ? lookup.charAt(0) : lookup.charAt(lookup.length() - 1);
        iteration++;
    }
    
    private char enhance(int x, int y) {
        String s = "" + getChar(x-1, y-1) + getChar(x, y-1) + getChar(x+1, y-1) +
                getChar(x-1, y) + getChar(x, y) + getChar(x+1, y) +
                getChar(x-1, y+1) + getChar(x, y+1) + getChar(x+1, y+1);
        s = s.replace('#', '1');
        s = s.replace('.', '0');
        return lookup.charAt(Integer.parseInt(s, 2));
    }
    
    private char getChar(int x, int y) {
        if (y < 0 || x< 0 || y >= grid.length || x >= grid.length) {
            return infinityPixel;
        }
        return grid[y][x];
    }
    
    private int count() {
        int count = 0;
        for (char[] line : grid) {
            for (char c : line) {
                count += c == '#' ? 1 : 0;
            }
        }
        return count;
    }
}
