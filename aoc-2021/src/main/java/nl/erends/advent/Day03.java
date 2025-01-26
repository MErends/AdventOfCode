package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * --- Day 3: Binary Diagnostic ---
 * <p>The diagnostic report of your submarine consists of a list of binary
 * numbers which, when decoded properly, can tell you many useful things about
 * the conditions of the submarine.
 * <p><a href="https://adventofcode.com/2021/day/3">2021 Day 3</a>
 */
public class Day03 extends AbstractProblem<List<String>, Integer> {
    
    public static void main(String[] args) {
        new Day03().setAndSolve(Util.readInput(2021, 3));
    }

    @Override
    protected Integer solve1() {
        String gammaString = IntStream.range(0, input.getFirst().length())
                .mapToObj(index -> longString(input, index))
                .map(s -> countOnes(s) > s.length() / 2 ? "1" : "0")
                .collect(Collectors.joining());

        String epsilonString = gammaString.chars()
                .mapToObj(c -> c == '1' ? "0" : "1")
                .collect(Collectors.joining());
        
        return Integer.parseInt(gammaString, 2) * Integer.parseInt(epsilonString, 2);
    }

    @Override
    public Integer solve2() {
        List<String> oxygenList = new ArrayList<>(input);
        List<String> co2List = new ArrayList<>(input);
        IntStream.range(0, input.getFirst().length())
                .forEach(index -> {
                    reduceList(oxygenList, index, '1');
                    reduceList(co2List, index, '0');
                });
        return Integer.parseInt(oxygenList.getFirst(), 2) * Integer.parseInt(co2List.getFirst(), 2);
    }
    
    private String longString(List<String> input, int index) {
        return input.stream()
                .map(s -> String.valueOf(s.charAt(index)))
                .collect(Collectors.joining());
    }
    
    private int countOnes(String line) {
        return (int) line.chars()
                .filter(c -> c == '1')
                .count();
    }
    
    private void reduceList(List<String> inList, int index, char preferredBit) {
        if (inList.size() == 1) {
            return;
        }
        long countOne = inList.stream()
                .filter(s -> s.charAt(index) == '1')
                .count();
        long countZero = inList.size() - countOne;
        char remove = '1';
        if (preferredBit == '1' && countZero <= countOne ||
            preferredBit == '0' && countOne< countZero) {
            remove = '0';
        }
        char finalRemove = remove;
        inList.removeIf(s -> s.charAt(index) == finalRemove);
    }
}
