package nl.erends.advent.year2015;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day09 extends AbstractProblem<List<String>, Integer> {
    
    private int maxDistance = Integer.MIN_VALUE;
    private int minDistance = Integer.MAX_VALUE;
    
    public static void main(String[] args) {
        new Day09().setAndSolve(Util.readInput(2015, 9));
    }

    @Override
    public Integer solve1() {
        Set<String> cities = new HashSet<>();
        Map<String, Integer> distances = new HashMap<>();
        for (String line : input) {
            String[] words = line.split(" ");
            cities.add(words[0]);
            cities.add(words[2]);
            distances.put(words[0] + "-" + words[2], Integer.valueOf(words[4]));
            distances.put(words[2] + "-" + words[0], Integer.valueOf(words[4]));
        }
        for (String fromCity : cities) {
            Set<String> citiesLeft = new HashSet<>(cities);
            citiesLeft.remove(fromCity);
            calculate(fromCity, citiesLeft, 0, distances);
        }
        return minDistance;
    }

    @Override
    public Integer solve2() {
        if (minDistance == Integer.MAX_VALUE) {
            solve1();
        }
        return maxDistance;
    }
    
    private void calculate(String fromCity, Set<String> citiesLeft, int currentDistance, Map<String, Integer> distances) {
        if (citiesLeft.isEmpty()) {
            maxDistance = Math.max(maxDistance, currentDistance);
            minDistance = Math.min(minDistance, currentDistance);
        } else {
            for (String nextCity : citiesLeft) {
                Set<String> newCitiesLeft = new HashSet<>(citiesLeft);
                newCitiesLeft.remove(nextCity);
                calculate(nextCity, newCitiesLeft, currentDistance + getDistance(fromCity, nextCity, distances), distances);
            }
        }
    }
    
    private int getDistance(String city1, String city2, Map<String, Integer> distances) {
        String key = city1 + "-" + city2;
        if (distances.containsKey(key)) {
            return distances.get(key);
        } else {
            return distances.get(city2 + "-" + city1);
        }
    }
    
}
