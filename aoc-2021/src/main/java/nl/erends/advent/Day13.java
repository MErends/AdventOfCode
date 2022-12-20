package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * --- Day 13: Transparent Origami ---
 * <p>The transparent paper is marked with random dots and includes instructions
 * on how to fold it up. Finish folding the transparent paper according to the
 * instructions. The manual says the code is always eight capital letters. What
 * code do you use to activate the infrared thermal imaging camera system?
 * <p><a href="https://adventofcode.com/2021/day/13">2021 Day 13</a>
 */
public class Day13 extends AbstractProblem<List<String>,String> {
    
    private List<Point> points;
    private int lineIndex = 0;
    private int maxX = Integer.MAX_VALUE;
    private int maxY = Integer.MAX_VALUE;

    public static void main(String[] args) {
        new Day13().setAndSolve(Util.readInput(2021, 13));
    }
    
    @Override
    protected String solve1() {
        points = new ArrayList<>();
        while (!input.get(lineIndex).isEmpty()) {
            points.add(new Point(input.get(lineIndex++)));
        }
        lineIndex++;
        doFold(input.get(lineIndex));
        return "" + points.stream().map(p -> p.x + "," + p.y).collect(Collectors.toSet()).size();
    }

    @Override
    public String solve2() {
        while (++lineIndex < input.size()) {
            doFold(input.get(lineIndex));
        }
        boolean[][] paper = new boolean[maxY][maxX];
        points.forEach(p -> paper[p.y][p.x] = true);
        StringBuilder sb = new StringBuilder();
        for (boolean[] row : paper) {
            for (boolean point : row) {
                sb.append(point ? '#' : '.');
            }
            sb.append('\n');
        }
        return sb.toString();
    }
    
    private void doFold(String line) {
        char direction = line.charAt(11);
        for (Point p : points) {
            if (direction == 'y') {
                p.foldY(Integer.parseInt(input.get(lineIndex).substring(13)));
                maxY = Math.min(maxY, Integer.parseInt(input.get(lineIndex).substring(13)));
            } else {
                p.foldX(Integer.parseInt(input.get(lineIndex).substring(13)));
                maxX = Math.min(maxX, Integer.parseInt(input.get(lineIndex).substring(13)));

            }
        }
        
    }
    
    private static class Point {
        int x;
        int y;

        Point(String line) {
            String[] splitLine = line.split(",");
            this.x = Integer.parseInt(splitLine[0]);
            this.y = Integer.parseInt(splitLine[1]);
        }
        
        private void foldX(int fx) {
            x = x < fx ? x : fx - (x - fx);
        }

        private void foldY(int fy) {
            y = y < fy ? y : fy - (y - fy);
        }
    }
}
