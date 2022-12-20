package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day07 extends AbstractProblem<List<String>, Integer> {

    public static void main(String[] args) {
        new Day07().setAndSolve(Util.readInput(2016, 7));
    }
    
    @Override
    public Integer solve1() {
        int tlsLines = 0;
        int sslLines = 0;
        for (String line : input) {
            List<String> outsides = new ArrayList<>();
            List<String> insides = new ArrayList<>();
            while (line.contains("[")) {
                int split = line.indexOf('[');
                outsides.add(line.substring(0, split));
                line = line.substring(split + 1);
                split = line.indexOf(']');
                insides.add(line.substring(0, split));
                line = line.substring(split + 1);
            }
            outsides.add(line);
            if (supportsTLS(insides, outsides)) {
                tlsLines++;
            }
            if (supportsSSL(insides, outsides)) {
                sslLines++;
            }
        }
        answer2 = sslLines;
        return tlsLines;
    }


    private boolean containsAbba(String input) {
        char[] letters = input.toCharArray();
        for (int index = 0; index < letters.length - 3; index++) {
            char a = letters[index];
            char b = letters[index + 1];
            if (a != b && letters[index + 2] == b && letters[index + 3] == a) {
                return true;
            }
        }
        return false;
    }

    private boolean supportsTLS(List<String> insides, List<String> outsides) {
        for (String inside : insides) {
            if (containsAbba(inside)) {
                return false;
            }
        }
        for (String outside : outsides) {
            if (containsAbba(outside)) {
                return true;
            }
        }
        return false;
    }

    private boolean supportsSSL(List<String> insides, List<String> outsides) {
        for (String inside : insides) {
            char[] letters = inside.toCharArray();
            for (int index = 0; index < letters.length - 2; index++) {
                char a = letters[index];
                char b = letters[index + 1];
                if (a != b && letters[index + 2] == a) {
                    String search = "" + b + a + b;
                    if (outsides.stream().anyMatch(outside -> outside.contains(search))) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
