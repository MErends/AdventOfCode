package nl.erends.advent.year2018;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Day01 extends AbstractProblem<List<String>, Integer> {

    public static void main(String[] args) {
        new Day01().setAndSolve(Util.readInput(2018, 1));
    }
    
    @Override
    public Integer solve1() {
        return input.stream()
                .mapToInt(Integer::parseInt)
                .sum();
    }
    
    @Override
    public Integer solve2() {
        
        List<Integer> integerInput = input.stream()
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        Set<Integer> freqsSeen = new HashSet<>();
        int freq = 0;
        int index = 0;
        while (freqsSeen.add(freq)) {
            freq += integerInput.get(index++ % integerInput.size());
        }
        return freq;
    }
}
