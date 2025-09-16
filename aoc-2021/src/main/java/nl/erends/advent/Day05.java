package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * --- Day 5: Hydrothermal Venture --- 
 * <p>You come across a field of hydrothermal vents on the ocean floor! They
 * tend to form in lines; the submarine helpfully produces a list of nearby
 * lines of vents (your puzzle input) for you to review. To avoid the most
 * dangerous areas, you need to determine the number of points where at least
 * two lines overlap.
 * <p><a href="https://adventofcode.com/2021/day/5">2021 Day 5</a>
 */
public class Day05 extends AbstractProblem<List<String>, Integer> {
    
    private static final Pattern LINE_PAT = Pattern.compile("(\\d+),(\\d+) -> (\\d+),(\\d+)");
    
    static void main() {
        new Day05().setAndSolve(Util.readInput(2021, 5));
    }

    @Override
    protected Integer solve1() {
        Map<String, Integer> cardinalMap = new HashMap<>();
        Map<String, Integer> diagonalMap = new HashMap<>();
        for (String line : input) {
            Matcher m = LINE_PAT.matcher(line);
            if (!m.find()) throw new IllegalArgumentException();
            int x0 = Integer.parseInt(m.group(1));
            int y0 = Integer.parseInt(m.group(2));
            int x1 = Integer.parseInt(m.group(3));
            int y1 = Integer.parseInt(m.group(4));
            if (x0 == x1) { // Vertical
                for (int y = Math.min(y0, y1); y <= Math.max(y0, y1); y++) {
                    addToMap(cardinalMap, x0, y);
                    addToMap(diagonalMap, x0, y);
                }
            } else if (y0 == y1) { // Horizontal
                for (int x = Math.min(x0, x1); x <= Math.max(x0, x1); x++) {
                    addToMap(cardinalMap, x, y0);
                    addToMap(diagonalMap, x, y0);
                }
            } else if (x1 > x0 && y1 > y0 || x1 < x0 && y1 < y0) { // Positive diagonal
                for (int x = Math.min(x0, x1), y = Math.min(y0, y1); x <= Math.max(x0, x1); x++, y++) {
                    addToMap(diagonalMap, x, y);
                }
            } else { // Negative diagonal
                for (int x = Math.min(x0, x1), y = Math.max(y0, y1); x <= Math.max(x0, x1); x++, y--) {
                    addToMap(diagonalMap, x, y);
                }
            }
        }
        answer2 = (int) diagonalMap.values().stream().filter(v -> v != 1).count();
        return (int) cardinalMap.values().stream().filter(v -> v != 1).count();
    }
    
    private void addToMap(Map<String, Integer> map, int x, int y) {
        map.compute(x + "," + y, (_, v) -> v == null ? 1 : v + 1);
    }
}
