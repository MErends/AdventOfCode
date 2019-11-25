package nl.erends.advent.year2018;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Day02 extends AbstractProblem<List<String>, String> {

    public static void main(String[] args) {
        new Day02().setAndSolve(Util.readInput(2018, 2));
    }
    
    @Override
    public String solve1() {
        int hasDoubleChar = 0;
        int hasTripleChar = 0;
        for (String line : input) {
            Map<String, Long> lettermap = Arrays.stream(line.split(""))
                    .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
            if (lettermap.values().stream().anyMatch(l -> l == 2)) {
                hasDoubleChar++;
            }
            if (lettermap.values().stream().anyMatch(l -> l == 3)) {
                hasTripleChar++;
            }
        }
        return Integer.toString(hasDoubleChar * hasTripleChar);
    }
    
    @Override
    public String solve2() {
        for (int indexA = 0; indexA < input.size(); indexA++) {
            for (int indexB = indexA + 1; indexB < input.size(); indexB++) {
                String difference = difference(input.get(indexA), input.get(indexB));
                if (!difference.equals("")) {
                    return difference;
                }
            }
        }
        return "";
    }
    
    
    private String difference(String a, String b) {
        int differentIndex = -1;
        for (int index = 0; index < a.length(); index++) {
            if (a.charAt(index) != b.charAt(index)) {
                if (differentIndex == -1) {
                    differentIndex = index;
                } else {
                    return "";
                }
            }
        }
        return a.substring(0, differentIndex) + a.substring(differentIndex + 1);
    }
}
