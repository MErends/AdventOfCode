package nl.erends.advent.year2015;

import nl.erends.advent.util.Problem;
import nl.erends.advent.util.Timer;
import nl.erends.advent.util.Util;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class Day05 implements Problem<List<String>, Integer> {
    
    private static final Logger LOG = Logger.getLogger(Day05.class);
    public static void main(String[] args) {
        List<String> input = Util.readInput(2015, 5);
        Day05 problem = new Day05();
        LOG.info(problem.solve1(input));
        LOG.info(problem.solve2(input));
        Timer.printStats();
    }
    
    public Integer solve1(List<String> input) {
        Timer.start1();
        int niceLines = 0;
        for (String line : input) {
           if (has3Vowels(line) && hasDoubles(line) && !hasBadWords(line)) {
                niceLines++;
            }
        }
        Timer.end1();
        return niceLines;
    }
    
    public Integer solve2(List<String> input) {
        Timer.start2();
        int niceLines = 0;
        for (String line : input) {
            if (hasDoubleString(line) && hasSkips(line)) {
                niceLines++;
            }
        }
        Timer.end2();
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
