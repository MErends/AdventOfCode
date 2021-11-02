package nl.erends.advent.year2020;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day19 extends AbstractProblem<List<String>, Long> {
   
    private final Map<String, Rule> rules = new HashMap<>();

    public static void main(String[] args) {
        new Day19().setAndSolve(Util.readInput(2020, 19));
    }

    @Override
    public Long solve1() {
        int inputSplit = input.indexOf("");
        for (int index = 0; index < inputSplit; index++) {
            String[] line = input.get(index).split(":");
            rules.put(line[0], new Rule(line[1], line[0]));
        }
        Rule zero = rules.get("0");
        zero.resolve();
         return input.subList(inputSplit + 1, input.size()).stream()
                 .filter(zero.matches::contains).count();
    }

    private class Rule {
        final String ruleString;
        final Set<String> matches = new HashSet<>();
        boolean resolved = false;
        final String number;
        
        private Rule(String ruleString, String number) {
            this.ruleString = ruleString;
            this.number = number;
        }
        
        private void resolve() {
            String[] options = ruleString.split("\\|");
            for (String option : options) {
                String[] numbers = option.trim().split(" ");
                if (!rules.containsKey(numbers[0])) {
                    matches.add(option.replace("\"", "").trim());
                    continue;
                }
                addCombined(numbers);
            }
            resolved = true;
        }
        
        private void addCombined(String[] numbers) {
            Set<String> matches1 = rules.get(numbers[0]).getMatches();
            Set<String> matches2 = new HashSet<>(Collections.singletonList(""));
            Set<String> matches3 = new HashSet<>(Collections.singletonList(""));
            if (numbers.length > 1) {
                matches2 = rules.get(numbers[1]).getMatches();
            }
            if (numbers.length > 2) {
                matches3 = rules.get(numbers[2]).getMatches();
            }
            for (String match1 : matches1) {
                for (String match2 : matches2) {
                    for (String match3 : matches3) {
                        matches.add(match1 + match2 + match3);
                    }
                }
            }
        }

        Set<String> getMatches() {
            if (!resolved) {
                resolve();
            }
            return matches;
        }
    }

    @Override
    // always 0: 8 11
    // Now  8: 42 8 so any amount of 42
    // Now 11: 42 11 31 so any amount of 42 followed by the same amount of 31.
    // So: 0: (42)x + (42)y + (31)y
    public Long solve2() {
        if (rules.isEmpty()) {
            solve1();
        }
        int inputSplit = input.indexOf("");
        int byteSize = rules.get("42").matches.stream().findFirst().orElseThrow(IllegalStateException::new).length();
        return input.subList(inputSplit + 1, input.size()).stream()
                .filter(s -> extendedMatch(s, byteSize)).count();
    }
    
    private boolean extendedMatch(String line, int bytesize) {
        Rule rule42 = rules.get("42");
        Rule rule31 = rules.get("31");
        int count31 = 0;
        int count42 = 0;
        while (line.length() > 2 * bytesize) {
            String beginByte = line.substring(0, bytesize);
            String endByte = line.substring(line.length() - bytesize);
            if (rule42.matches.contains(beginByte) && rule31.matches.contains(endByte)) {
                line = line.substring(bytesize, line.length() - bytesize);
                count31++;
            } else {
                break;
            }
        }
        while (line.length() >= bytesize) {
            String beginByte = line.substring(0, bytesize);
            if (rule42.matches.contains(beginByte)) {
                line = line.substring(bytesize);
                count42++;
            } else {
                break;
            }
        }
        return line.isEmpty() && count31 > 0 && count42 > 0;
    }
}
