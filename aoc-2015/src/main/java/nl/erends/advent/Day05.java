package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day05 extends AbstractProblem<List<String>, Integer> {
    
    static void main() {
        new Day05().setAndSolve(Util.readInput(2015, 5));
    }

    @Override
    public Integer solve1() {
        int niceLines = 0;
        for (String line : input) {
           if (has3Vowels(line) && hasDoubles(line) && !hasBadWords(line)) {
                niceLines++;
            }
        }
        return niceLines;
    }

    @Override
    public Integer solve2() {
        int niceLines = 0;
        for (String line : input) {
            if (hasDoubleString(line) && hasSkips(line)) {
                niceLines++;
            }
        }
        return niceLines;
    }
    

    private boolean has3Vowels(String line) {
        String vowels = "aeiou";
        int numVowels = 0;
        for (int i = 0; i < line.length(); i++) {
            if (vowels.contains(line.substring(i, i + 1))) {
                numVowels++;
            }
            if (numVowels == 3) {
                return true;
            }
        }
        return false;
    }

    private boolean hasDoubles(String line) {
        for (int i = 0; i < line.length() - 1; i++) {
            if (line.charAt(i) == line.charAt(i + 1)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasBadWords(String line) {
        List<String> badWords = new ArrayList<>();
        badWords.add("ab");
        badWords.add("cd");
        badWords.add("pq");
        badWords.add("xy");
        for (String badWord : badWords) {
            if (line.contains(badWord)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasDoubleString(String line) {
        for (int i = 0; i < line.length() - 2; i++) {
            String searchterm = line.substring(i, i + 2);
            String subject = line.substring(i + 2);
            if (subject.contains(searchterm)) {
                return true;
            }
        }
        return false;
    }

    private boolean hasSkips(String line) {
        for (int i = 0; i < line.length() - 2; i++) {
            if (line.charAt(i) == line.charAt(i + 2)) { 
                return true;
            }
        }
        return false;
    }
}
