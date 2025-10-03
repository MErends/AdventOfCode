package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * --- Day 17: Trick Shot ---
 * <p>The probe launcher on your submarine can fire the probe with any integer
 * velocity in the x and y directions. For the probe to successfully make it
 * into the trench, the probe must be on some trajectory that causes it to be
 * within a target area after any step.
 * <p><a href="https://adventofcode.com/2021/day/17">2021 Day 17</a>
 */
public class Day17 extends AbstractProblem<String,Integer> {
    
    void main() {
        new Day17().setAndSolve(Util.readLine(2021, 17));
    }

    @Override
    protected Integer solve1() {
        Matcher m = Pattern.compile("x=(-?\\d+)..(-?\\d+), y=(-?\\d+)..(-?\\d+)")
                .matcher(input);
        if (!m.find()) throw new IllegalArgumentException();
        int tMinX = Integer.parseInt(m.group(1));
        int tMaxX = Integer.parseInt(m.group(2));
        int tMaxY = Integer.parseInt(m.group(3));
        int tMinY = Integer.parseInt(m.group(4));
        answer2 = 0;
        int yRecord = Integer.MIN_VALUE;
        for (int ivx = 0; ivx <= tMaxX; ivx++) {
            for (int ivy = tMaxY; ivy <= -1 * tMaxY; ivy++) {
                List<Coord> coords = new ArrayList<>();
                int x = 0;
                int y = 0;
                int vx = ivx;
                int vy = ivy;
                while (x <= tMaxX && y >= tMaxY) {
                    coords.add(new Coord(x, y));
                    x += vx;
                    y += vy;
                    vx -= vx > 0 ? 1 : 0;
                    vy--;
                }
                if (coords.stream().anyMatch(p -> tMinX <= p.x && p.x <= tMaxX && tMaxY <= p.y && p.y <= tMinY)) {
                    int yMax = coords.stream()
                            .mapToInt(p -> p.y)
                            .max().orElseThrow();
                    yRecord = Math.max(yMax, yRecord);
                    answer2++;
                }
            }
        }
        return yRecord;
    }

    private record Coord(int x, int y) {
    }
}
