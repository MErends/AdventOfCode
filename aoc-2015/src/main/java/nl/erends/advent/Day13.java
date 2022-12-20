package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * --- Day 13: Knights of the Dinner Table ---
 * <p> Find the seating arrangement where the total happiness is at a maximum.
 * <p><a href="https://adventofcode.com/2015/day/13">2015 Day 13</a>
 */
public class Day13 extends AbstractProblem<List<String>, Number> {

    private Map<String, Integer> happinessMap;
    private Set<String> personSet;
    private int maxHappiness;

    public static void main(String[] args) {
        new Day13().setAndSolve(Util.readInput(2015, 13));
    }

    @Override
    protected Integer solve1() {
        readInput();
        List<String> table = new ArrayList<>();
        List<String> persons = new ArrayList<>(personSet);
        table.add(persons.remove(0));
        fillTable(table, persons);
        return maxHappiness;
    }

    @Override
    public Integer solve2() {
        readInput();
        List<String> table = new ArrayList<>();
        List<String> persons = new ArrayList<>(personSet);
        table.add("Me");
        fillTable(table, persons);
        return maxHappiness;
    }

    private void readInput() {
        maxHappiness = Integer.MIN_VALUE;
        happinessMap = new HashMap<>();
        personSet = new HashSet<>();
        for (String line : input) {
            String[] words = line.split(" ");
            String pair = words[0] + words[10];
            pair = pair.substring(0, pair.length() - 1);
            int happiness = Integer.parseInt(words[3]);
            if ("lose".equals(words[2])) {
                happiness *= -1;
            }
            happinessMap.put(pair, happiness);
            personSet.add(words[0]);
        }
    }

    private void fillTable(List<String> table, List<String> persons) {
        if (persons.isEmpty()) {
            calculateHappiness(table);
            return;
        }
        for (String person : persons) {
            List<String> newTable = new ArrayList<>(table);
            List<String> newPersons = new ArrayList<>(persons);
            newTable.add(person);
            newPersons.remove(person);
            fillTable(newTable, newPersons);
        }
    }

    private void calculateHappiness(List<String> table) {
        int happiness = 0;
        for (int index = 0; index < table.size(); index++) {
            String personA = table.get(index);
            String personB = table.get((index + 1) % table.size());
            if ("Me".equals(personA) || "Me".equals(personB)) {
                continue;
            }
            happiness += happinessMap.get(personA + personB);
            happiness += happinessMap.get(personB + personA);
        }
        maxHappiness = Math.max(maxHappiness, happiness);
    }
}
