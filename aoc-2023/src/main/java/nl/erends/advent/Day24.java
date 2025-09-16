package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.OptionalLong;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>--- Day 24: Never Tell Me The Odds ---</h1>
 * <p>It seems like something is going wrong with the snow-making process.
 * Instead of forming snow, the water that's been absorbed into the air seems to
 * be forming hail! Perhaps you won't have to do anything. How likely are the
 * hailstones to collide with each other and smash into tiny ice crystals? You
 * find a rock on the ground nearby. While it seems extremely unlikely, if you
 * throw it just right, you should be able to hit every hailstone in a single
 * throw!</p>
 * <p><a href="https://adventofcode.com/2023/day/24">2023 Day 24</a></p>
 */
public class Day24 extends AbstractProblem<List<String>, Number> {

    private static final Pattern LINE_PAT = Pattern.compile("(\\d+), (\\d+), (\\d+) @ +(-?\\d+), +(-?\\d+), +(-?\\d+)");
    long lowIntersect = 200000000000000L;
    long highIntersect = 400000000000000L;
    private List<Hail> hails;

    static void main() {
        new Day24().setAndSolve(Util.readInput(2023, 24, 1));
    }

    public void setTestParameters() {
        lowIntersect = 7L;
        highIntersect = 27L;
    }

    @Override
    protected Number solve1() {
        hails = input.stream().map(Hail::new).toList();
        int intersects = 0;
        for (int i1 = 0; i1 < input.size(); i1++) {
            Hail one = hails.get(i1);
            for (int i2 = i1 + 1; i2 < input.size(); i2++) {
                Hail two = hails.get(i2);
                if (one.intersectsInRange(two)) {
                    intersects++;
                }
            }
        }
        return intersects;
    }

    @Override
    public Number solve2() {
        long x;
        int vx = 1;
        while (true) {
            OptionalLong xO = solveStoneX(vx);
            if (xO.isPresent()) {
                x = xO.getAsLong();
                break;
            }
            vx = -vx;
            xO = solveStoneX(vx);
            if (xO.isPresent()) {
                x = xO.getAsLong();
                break;
            }
            vx = -vx;
            vx++;
        }
        Hail hail1 = hails.get(1);
        Hail hail2 = hails.get(2);
        long t1 = (x - hail1.x[0]) / (hail1.v[0] - vx);
        long t2 = (x - hail2.x[0]) / (hail2.v[0] - vx);
        long vy = (hail1.v[1]*t1 - hail2.v[1]*t2 - hail2.x[1] + hail1.x[1]) / (t1 - t2);
        long y = hail1.v[1]*t1 - vy*t1 + hail1.x[1];
        long vz = (hail1.v[2]*t1 - hail2.v[2]*t2 - hail2.x[2] + hail1.x[2]) / (t1 - t2);
        long z = hail1.v[2]*t1 - vz*t1 + hail1.x[2];
        return x + y + z;
    }

    OptionalLong solveStoneX(int velocity) {
        List<Integer> noemers = new ArrayList<>();
        long xMin = Long.MIN_VALUE;
        long xMax = Long.MAX_VALUE;
        boolean zeroNoemer = false;
        for (Hail hail : hails) {
            int noemer = hail.v[0] - velocity;
            noemers.add(noemer);
            if (noemer < 0) {
                xMax = Math.min(xMax, hail.x[0]);
            } else if (noemer > 0) {
                xMin = Math.max(xMin, hail.x[0]);
            } else {
                zeroNoemer = true;
            }
        }
        if (xMin >= xMax) return OptionalLong.empty();
        if (zeroNoemer) {
            Hail xHail = hails.get(noemers.indexOf(0));
            long x = xHail.x[0];
            if (x < xMin || x > xMax) {
                return OptionalLong.empty();
            }
            for (Hail hail : hails) {
                if ((hail.v[0] - velocity) != 0 && (x - hail.x[0]) % (hail.v[0] - velocity) != 0) {
                    return OptionalLong.empty();
                }
            }
            return OptionalLong.of(x);
        }
        Set<Integer> noemerSet = new HashSet<>(noemers);
        for (int noemer : noemerSet) {
            List<Integer> indexes = new ArrayList<>();
            for (int index = 0; index < noemers.size(); index++) {
                if (noemers.get(index) == noemer) {
                    indexes.add(index);
                }
            }
            for (int indexA = 0; indexA < indexes.size(); indexA++) {
                Hail hailA = hails.get(indexes.get(indexA));
                for (int indexB = indexA + 1; indexB < indexes.size(); indexB++) {
                    Hail hailB = hails.get(indexes.get(indexB));
                    long xDif = Math.abs(hailB.x[0] - hailA.x[0]);
                    if (xDif % Math.abs(noemer) != 0) {
                        return OptionalLong.empty();
                    }
                }
            }
        }
        // Wat nu?
        int maxStep = 0;
        for (int index = 0; index < hails.size(); index++) {
            int noemer = noemers.get(index);
            if (Math.abs(noemer) > Math.abs(maxStep)) {
                maxStep = noemer;
            }
        }
        long x;
        if (maxStep < 0) {
            x = xMax - 1;
            long xRemain = Math.abs(maxStep) - (x % maxStep);
            x -= xRemain;
        } else {
            x = xMin + 1;
            long xRemain = maxStep - (x % maxStep);
            x += xRemain;
        }
        outer:
        while (true) {
            for (Hail hail : hails) {
                if ((x - hail.x[0]) % (hail.v[0] - velocity) != 0) {
                    x += maxStep;
                    continue outer;
                }
            }
            return OptionalLong.of(x);
        }
    }

    class Hail {
        long[] x;
        int[] v;
        double a;
        double b;

        Hail(String line) {
            Matcher m = LINE_PAT.matcher(line);
            if (!m.find()) throw new IllegalArgumentException();
            x = new long[]{Long.parseLong(m.group(1)), Long.parseLong(m.group(2)), Long.parseLong(m.group(3))};
            v = new int[]{Integer.parseInt(m.group(4)), Integer.parseInt(m.group(5)), Integer.parseInt(m.group(6))};
            a = ((double) v[1]) / v[0];
            b = x[1] - a * x[0];
        }

        boolean intersectsInRange(Hail other) {
            double xInt = (other.b - b) / (a - other.a);
            double yInt = a * xInt + b;
            boolean canIntersect =  lowIntersect <= xInt && xInt <= highIntersect
                    && lowIntersect <= yInt && yInt <= highIntersect;
            if (v[0] > 0 && xInt < x[0]) {
                canIntersect = false;
            }
            if (v[0] < 0 && xInt > x[0]) {
                canIntersect = false;
            }
            if (other.v[0] > 0 && xInt < other.x[0]) {
                canIntersect = false;
            }
            if (other.v[0] < 0 && xInt > other.x[0]) {
                canIntersect = false;
            }
            return canIntersect;
        }
    }
}
