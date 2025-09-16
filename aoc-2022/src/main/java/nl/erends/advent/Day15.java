package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * --- Day 15: Beacon Exclusion Zone ---
 * <p>Your pack contains a set of deployable sensors that you imagine were
 * originally built to locate lost Elves. Once a sensor finds a spot it thinks
 * will give it a good reading, it attaches itself to a hard surface and begins
 * monitoring for the nearest signal source beacon. The distress beacon is not
 * detected by any sensor. Find the only possible position for the distress
 * beacon.
 * <p><a href="https://adventofcode.com/2022/day/15">2022 Day 15</a>
 */
public class Day15 extends AbstractProblem<List<String>, Number> {

    private int max = 4_000_000;

    static void main() {
        new Day15().setAndSolve(Util.readInput(2022, 15));
    }

    @Override
    protected Integer solve1() {
        List<Scanner> scanners = new ArrayList<>();
        for (String line : input) {
            scanners.add(new Scanner(line));
        }
        int row = max / 2;
        Set<Integer> exclusion = new HashSet<>();
        for (Scanner s : scanners) {
            exclusion.addAll(s.exclusionsOnRow(row));
            if (row == s.bY) {
                exclusion.remove(s.bX);
            }
        }
        return exclusion.size();
    }

    @Override
    public Long solve2() {
        List<Scanner> scanners = new ArrayList<>();
        for (String line : input) {
            scanners.add(new Scanner(line));
        }
        for (int y = 0; y <= max; y++) {
            int x = 0;
            while (x <= max) {
                int currentX = x;
                int currentY = y;
                Scanner s = scanners.stream()
                        .filter(sc -> Math.abs(sc.x - currentX) + Math.abs(sc.y - currentY) <= sc.range)
                        .findAny()
                        .orElse(null);
                if (s == null) {
                    return x * 4000000L + y;
                } else {
                    x = s.x + s.range - Math.abs(y - s.y) + 1;
                }
            }
        }
        return null;
    }

    void setTest() {
        max = 20;
    }

    private static class Scanner {
        final int x;
        final int y;
        final int bX;
        final int bY;
        final int range;

        Scanner(String line) {
            String[] words = line.split(" ");
            String sx = words[2];
            String sy = words[3];
            String sbX = words[8];
            String sbY = words[9];
            x = Integer.parseInt(sx.substring(2, sx.length() - 1));
            y = Integer.parseInt(sy.substring(2, sy.length() - 1));
            bX = Integer.parseInt(sbX.substring(2, sbX.length() - 1));
            bY = Integer.parseInt(sbY.substring(2));
            range = Math.abs(x - bX) + Math.abs(y - bY);
        }

        List<Integer> exclusionsOnRow(int row) {
            List<Integer> exclusions = new ArrayList<>();
            int rowDistance = Math.abs(row - y);
            int leeway = range - rowDistance;
            for (int i = -leeway; i <= leeway; i++) {
                exclusions.add(x + i);
            }
            return exclusions;
        }
    }
}
