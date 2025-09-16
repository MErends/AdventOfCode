package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Day04 extends AbstractProblem<List<String>, Integer> {
    
    static void main() {
        new Day04().setAndSolve(Util.readInput(2017, 4));
    }

    @Override
    public Integer solve1() {
        int valids = 0;
        for (String line : input) {
            List<String> wordList = Arrays.asList(line.split(" "));
            Set<String> wordSet = new HashSet<>(wordList);
            if (wordList.size() == wordSet.size()) {
                valids++;
            }
        }
        return valids;
    }

    @Override
    public Integer solve2() {
        int valids = 0;
        for (String line : input) {
            String[] words = line.split(" ");
            Set<String> uniek = new HashSet<>();
            for (String word : words) {
                char[] letters = word.toCharArray();
                Arrays.sort(letters);
                uniek.add(new String(letters));
            }
            if (words.length == uniek.size()) {
                valids++;
            }
        }
        return valids;
    }
}


