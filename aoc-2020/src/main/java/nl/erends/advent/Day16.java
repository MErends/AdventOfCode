package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

public class Day16 extends AbstractProblem<List<String>, Number> {
    
    private List<Criteria> criteria = new ArrayList<>();
    private int ticketStart;
    private final Set<Integer> invalidIndex = new HashSet<>();

    static void main() {
        new Day16().setAndSolve(Util.readInput(2020, 16));
    }
    
    @Override
    public Integer solve1() {
        readCriteria();
        int index = 0;
        while (!input.get(index).startsWith("nearby")) {
            index++;
        }
        ticketStart = ++index;
        List<Integer> errors = new ArrayList<>();
        Set<Integer> validNumber = new HashSet<>();
        Set<Integer> invalidNumber = new HashSet<>();
        while (index < input.size()) {
            String[] numbers = input.get(index).split(",");
            for (String numberString : numbers) {
                int number = Integer.parseInt(numberString);
                if (invalidNumber.contains(number)) {
                    invalidIndex.add(index);
                    errors.add(number);
                } else if (!validNumber.contains(number)){
                    boolean valid = criteria.stream().anyMatch(c -> c.matchesCriteria(number));
                    if (valid) {
                        validNumber.add(number);
                    } else {
                        invalidIndex.add(index);
                        invalidNumber.add(number);
                        errors.add(number);
                    }
                }
            }
            index++;
        }
        return errors.stream().reduce(0, Integer::sum);
    }
    
    @Override
    public Long solve2() {
        if (criteria.isEmpty()) {
            solve1();
        }
        int index = 0;
        while (!input.get(index).startsWith("your ticket:")) {
            index++;
        }
        String[] myticket = input.get(index + 1).split(",");
        invalidIndex.stream().sorted((i1, i2) -> i2 - i1).mapToInt(i -> i).forEach(i -> input.remove(i));
        List<Integer> indices = IntStream.range(0, myticket.length).boxed().toList();
        criteria.forEach(c -> c.indices = new ArrayList<>(indices));
        for (index = ticketStart; index < input.size(); index++) {
            String[] line = input.get(index).split(",");
            for (int subIndex = 0; subIndex < line.length; subIndex++) {
                int finalSubIndex = subIndex;
                criteria.stream()
                        .filter(c -> !c.matchesCriteria(Integer.parseInt(line[finalSubIndex])))
                        .forEach(c -> c.indices.remove(Integer.valueOf(finalSubIndex)));
            }
        }
        List<Criteria> doneCriteria = new ArrayList<>();
        while (!criteria.isEmpty()) {
            Criteria done = criteria.stream().filter(c -> c.indices.size() == 1).findFirst().orElseThrow(IllegalStateException::new);
            doneCriteria.add(done);
            criteria.remove(done);
            criteria.forEach(c -> c.indices.remove(done.indices.getFirst()));
        }
        return doneCriteria.stream().
                filter(c -> c.name.startsWith("departure"))
                .map(c -> c.indices.getFirst())
                .map(i -> Long.parseLong(myticket[i]))
                .reduce(1L, (i1, i2) -> i1 * i2);
    }
    
    private void readCriteria() {
        criteria = new ArrayList<>();
        for (String line : input) {
            if (line.isEmpty()) {
                return;
            }
            criteria.add(new Criteria(line));
        }
    }
    
    private static class Criteria {
        final String name;
        final List<Range> ranges = new ArrayList<>();
        List<Integer> indices;
        
        Criteria(String line) {
            String[] words = line.split(":");
            name = words[0];
            String[] rangeArr = words[1].split(" or ");
            for (String rangeString : rangeArr) {
                ranges.add(new Range(rangeString));
            }
        }
        
        boolean matchesCriteria(int number) {
            for (Range range : ranges) {
                if (number >= range.lower && number <= range.upper) {
                    return true;
                }
            }
            return false;
        }
    }
    
    private static class Range {
        final int lower;
        final int upper;

        Range(String rangeString) {
            String[] bounds = rangeString.trim().split("-");
            this.lower = Integer.parseInt(bounds[0]);
            this.upper = Integer.parseInt(bounds[1]);
        }
    }
}
