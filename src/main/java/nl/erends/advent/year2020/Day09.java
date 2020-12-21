package nl.erends.advent.year2020;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day09 extends AbstractProblem<List<String>, Long> {
    
    private int preambleSize = 25;
    private List<Long> longInput;
    private long answer1;

    public static void main(String[] args) {
        new Day09().setAndSolve(Util.readInput(2020, 9));
    }

    @Override
    public Long solve1() {
        longInput = input.stream().map(Long::parseLong).collect(Collectors.toList());
        List<Long> preamble = new ArrayList<>(longInput.subList(0, preambleSize));
        List<Long> candidates = new ArrayList<>(longInput.subList(preambleSize, input.size()));
        for (long candidate : candidates) {
            if (sumExists(candidate, preamble)) {
                preamble.remove(0);
                preamble.add(candidate);
            } else {
                answer1 = candidate;
                break;
            }
        }
        return answer1;
    }
    
    @Override
    public Long solve2() {
        if (longInput == null) solve1();
        for (int startIndex = 0; startIndex < longInput.size(); startIndex++) {
            long sum = longInput.get(startIndex);
            for (int endIndex = startIndex + 1; endIndex < longInput.size(); endIndex++) {
                sum += longInput.get(endIndex);
                if (sum > answer1) {
                    break;
                } else if (sum == answer1){
                    long largest = Long.MIN_VALUE;
                    long smallest = Long.MAX_VALUE;
                    for (int index = startIndex; index <= endIndex; index++) {
                        long number = longInput.get(index);
                        largest = Long.max(largest, number);
                        smallest = Long.min(smallest, number);
                    }
                    return smallest + largest;
                }
            }
        }
        throw new IllegalArgumentException("No sum found");
    }
    
    private boolean sumExists(long candidate, List<Long> preamble) {
        for (int indexA = 0; indexA < preamble.size(); indexA++) {
            for (int indexB = indexA + 1; indexB < preamble.size(); indexB++) {
                if (candidate == preamble.get(indexA) + preamble.get(indexB)) {
                    return true;
                }
            }
        }
        return false;
    }
    
    void setTestPreamble() {
        this.preambleSize = 5;
    }
}
