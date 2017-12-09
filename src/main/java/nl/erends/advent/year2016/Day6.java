package nl.erends.advent.year2016;


import nl.erends.advent.util.FileIO;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Day6 {

    public static void main(String[] args) {
        List<String> lines = FileIO.getFileAsList("2016day6.txt");
        StringBuilder mostOften = new StringBuilder();
        StringBuilder leastOften = new StringBuilder();
        for (int index = 0; index < lines.get(0).length(); index++) {
            Map<Character, Integer> counts = new HashMap<>();

            for (String line : lines) {
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
        System.out.println(mostOften);
        System.out.println(leastOften);
    }
}
