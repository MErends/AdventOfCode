package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --- Day 14: Extended Polymerization ---
 * <p>The submarine manual contains instructions for finding the optimal polymer
 * formula. You just need to work out what polymer would result after repeating
 * the pair insertion process a few times.
 * <p><a href="https://adventofcode.com/2021/day/14">2021 Day 14</a>
 */
public class Day14 extends AbstractProblem<List<String>,Long> {
    
    private Map<String, List<String>> pairMapping;
    
    static void main() {
        new Day14().setAndSolve(Util.readInput(2021, 14));
    }

    @Override
    protected Long solve1() {
        pairMapping = new HashMap<>();
        for (int index = 2; index < input.size(); index++) {
            String[] rule = input.get(index).split(" -> ");
            pairMapping.put(rule[0], List.of("" + rule[0].charAt(0) + rule[1].charAt(0), "" + rule[1].charAt(0) + rule[0].charAt(1)));
        }
        Map<String, Long> pairCount = new HashMap<>();
        for (int index = 0; index < input.getFirst().length() - 1; index++) {
            pairCount.compute(input.getFirst().substring(index, index + 2), (_, v) -> v == null ? 1L : v + 1);
        }
        for (int i = 0; i < 10; i++) {
            pairCount = iterate(pairCount);
        }
        long answer1 = commonLeastCommonDifference(pairCount);
        for (int i = 10; i < 40; i++) {
            pairCount = iterate(pairCount);
        }
        answer2 = commonLeastCommonDifference(pairCount);
        return answer1;
    }

    private Map<String, Long> iterate(Map<String, Long> inMap) {
        Map<String, Long> outMap = new HashMap<>();
        inMap.forEach((key, value) -> pairMapping.get(key).forEach(pairMap -> outMap.compute(pairMap, (_, v) -> v == null ? value : v + value)));
        return outMap;
    }
    
    private long commonLeastCommonDifference(Map<String, Long> pairCount) {
        Map<Character, Long> charCount = new HashMap<>();
        pairCount.forEach((k, v) -> {
            charCount.compute(k.charAt(0), (_, v1) -> v1 == null ? v : v1 + v);
            charCount.compute(k.charAt(1), (_, v1) -> v1 == null ? v : v1 + v);
        });
        charCount.entrySet().forEach(e -> e.setValue((e.getValue() + 1) / 2));
        long mostCommonChar = charCount.entrySet().stream().max(Map.Entry.comparingByValue()).orElseThrow().getValue();
        long leastCommonChar = charCount.entrySet().stream().min(Map.Entry.comparingByValue()).orElseThrow().getValue();
        return mostCommonChar - leastCommonChar;
    }
}
