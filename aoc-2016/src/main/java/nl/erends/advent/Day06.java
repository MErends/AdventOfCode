package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day06 extends AbstractProblem<List<String>, String> {

    static void main() {
        new Day06().setAndSolve(Util.readInput(2016, 6));
    }

    @Override
    public String solve1() {
        StringBuilder mostOften = new StringBuilder();
        StringBuilder leastOften = new StringBuilder();
        for (int index = 0; index < input.getFirst().length(); index++) {
            Map<Character, Integer> counts = new HashMap<>();

            for (String line : input) {
                char letter = line.charAt(index);
                if (counts.containsKey(letter)) {
                    counts.put(letter, counts.get(letter) + 1);
                } else {
                    counts.put(letter, 1);
                }
            }
            char oftenLetter = '\0';
            int highestCount = 0;
            char leastLetter = '\0';
            int leastCount = Integer.MAX_VALUE;
            for (Map.Entry<Character, Integer> entry : counts.entrySet()) {
                if (entry.getValue() > highestCount) {
                    highestCount = entry.getValue();
                    oftenLetter = entry.getKey();
                }
                if (entry.getValue() < leastCount) {
                    leastCount = entry.getValue();
                    leastLetter = entry.getKey();
                }
            }
            mostOften.append(oftenLetter);
            leastOften.append(leastLetter);
        }
        answer2 = leastOften.toString();
        return mostOften.toString();
    }
}
