package nl.erends.advent.year2019;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day10 extends AbstractProblem<List<String>, Integer> {

    private char[][] field;
    private int xStationBest = 0;
    private int yStationBest = 0;
    private int asteroidsDestroyed = 0;
    private Asteroid asteroid200;

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
                int asteroidSeen = getAsteroidsSeen(xStation, yStation);
                if (asteroidSeen > mostAsteroids) {
                    mostAsteroids = asteroidSeen;
                    xStationBest = xStation;
                    yStationBest = yStation;
                }
            }
        }
        return mostAsteroids;
    }

    @Override
    public Integer solve2() {
        if (field == null) {
            solve1();
        }
        Map<Double, List<Asteroid>> rightSideMap = new HashMap<>();
        Map<Double, List<Asteroid>> leftSideMap = new HashMap<>();
        for (int yAsteroid = 0; yAsteroid < field.length; yAsteroid++) {
            for (int xAsteroid = 0; xAsteroid < field[yAsteroid].length; xAsteroid++) {
                addAsteroidToMap(rightSideMap, leftSideMap, yAsteroid, xAsteroid);
            }
        }
        while (asteroidsDestroyed < 200) {
            rightSideMap.keySet().stream().sorted().map(rightSideMap::get).forEach(this::zapAsteroidFromList);
            leftSideMap.keySet().stream().sorted().map(leftSideMap::get).forEach(this::zapAsteroidFromList);
            rightSideMap.entrySet().removeIf(e -> e.getValue().isEmpty());
            leftSideMap.entrySet().removeIf(e -> e.getValue().isEmpty());
        }
        return asteroid200.x * 100 + asteroid200.y;
    }

    private void addAsteroidToMap(Map<Double, List<Asteroid>> rightSideMap, Map<Double, List<Asteroid>> leftSideMap, int yAsteroid, int xAsteroid) {
        if (field[yAsteroid][xAsteroid] != '#' || (xAsteroid == xStationBest && yAsteroid == yStationBest)) {
            return;
        }
        int dx = xAsteroid - xStationBest;
        int dy = yAsteroid - yStationBest;
        boolean toTheRight = (dx > 0) || (dx == 0 && dy < 0);
        double gradient;
        if (dx == 0) {
            gradient = Double.NEGATIVE_INFINITY;
        } else {
            gradient = (double) dy / dx;
        }
        Asteroid asteroid = new Asteroid(xAsteroid, yAsteroid);
        if (toTheRight) {
            List<Asteroid> asteroidList = rightSideMap.computeIfAbsent(gradient, k -> new ArrayList<>());
            asteroidList.add(asteroid);
        } else {
            List<Asteroid> asteroidList = leftSideMap.computeIfAbsent(gradient, k -> new ArrayList<>());
            asteroidList.add(asteroid);
        }
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
    
    private void zapAsteroidFromList(List<Asteroid> asteroidList) {
        int closestDistance = Integer.MAX_VALUE;
        Asteroid closestAsteroid = null;
        for (Asteroid asteroid : asteroidList) {
            int distance = Math.abs(xStationBest - asteroid.x) + Math.abs(yStationBest - asteroid.y);
            if (distance < closestDistance) {
                closestDistance = distance;
                closestAsteroid = asteroid;
            }
        }
        asteroidsDestroyed++;
        asteroidList.remove(closestAsteroid);
        if (asteroidsDestroyed == 200) {
            asteroid200 = closestAsteroid;
        }
    }
    
    private static class Asteroid {
        private final int x;
        private final int y;

        Asteroid(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
