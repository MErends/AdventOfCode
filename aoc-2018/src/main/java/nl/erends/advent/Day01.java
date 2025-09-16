package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day01 extends AbstractProblem<List<Integer>, Integer> {

    static void main() {
        new Day01().setAndSolve(Util.readIntegers(2018, 1));
    }
    
    @Override
    public Integer solve1() {
        return input.stream()
                .mapToInt(i -> i)
                .sum();
    }
    
    @Override
    public Integer solve2() {
        Set<Integer> freqsSeen = new HashSet<>();
        int freq = 0;
        int index = 0;
        while (freqsSeen.add(freq)) {
            freq += input.get(index++ % input.size());
        }
        return freq;
    }
}
