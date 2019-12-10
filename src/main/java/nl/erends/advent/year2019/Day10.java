package nl.erends.advent.year2019;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day10 extends AbstractProblem<List<String>, Integer> {

    private char[][] field;

    public static void main(String[] args) {
        new Day10().setAndSolve(Util.readInput(2019, 10));
    }

    @Override
    public Integer solve1() {
        loadField(input);
        int mostAsteroids = Integer.MIN_VALUE;
        for (int yStation = 0; yStation < field.length; yStation++) {
            for (int xStation = 0; xStation < field[yStation].length; xStation++) {
                if (field[yStation][xStation] != '#') {
                    continue;
                }
                mostAsteroids = Math.max(mostAsteroids, getAsteroidsSeen(xStation, yStation));
            }
        }
        return mostAsteroids;
    }

    private void loadField(List<String> input) {
        field = new char[input.size()][];
        for (int i = 0; i < input.size(); i++) {
            field[i] = input.get(i).toCharArray();
        }
    }

    private int getAsteroidsSeen(int xStation, int yStation) {
        Set<String> fractions = new HashSet<>();
        for (int yAsteroid = 0; yAsteroid < field.length; yAsteroid++) {
            for (int xAsteroid = 0; xAsteroid < field[yAsteroid].length; xAsteroid++) {
                if (field[yAsteroid][xAsteroid] != '#' || (yAsteroid == yStation && xAsteroid == xStation)) {
                    continue;
                }
                fractions.add(getFractionString(xStation, yStation, xAsteroid, yAsteroid));
            }
        }
        return fractions.size();
    }

    private String getFractionString(int xStation, int yStation, int xAsteroid, int yAsteroid) {
        int dx = xAsteroid - xStation;
        int dy = yAsteroid - yStation;
        if (dx == 0) {
            if (dy > 0) {
                return "+INF";
            } else if (dy == 0) {
                return "SELF";
            } else {
                return "-INF";
            }
        } else if (dy == 0) {
            if (dx > 0) {
                return "+0";
            } else {
                return "-0";
            }
        } else {
            return getFractionString(dx, dy);
        }
    }

    private String getFractionString(int dx, int dy) {
        while (true) {
            int d = gcd(dx, dy);
            if (d != 1) {
                dx /= d;
                dy /= d;
            } else {
                return "" + dx + "/" + dy;
            }
        }
    }

    private int gcd(int a, int b) {
        a = Math.abs(a);
        b = Math.abs(b);
        for (int d = Math.min(a, b); d >= 1; d--) {
            if (a % d == 0 && b % d == 0) {
                return d;
            }
        }
        return 1;
    }
}
