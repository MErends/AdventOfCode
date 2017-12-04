package nl.erends.advent.year2017;

import nl.erends.advent.util.FileIO;

import java.util.*;

public class Day4 {
    public static void main(String[] args) throws Exception {
        int valids = 0;

        List<String> lines = FileIO.getFileAsList("C:\\Users\\marke\\IdeaProjects\\Adventofcode\\resource\\2017day4.txt");
        for (String line : lines) {
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

        System.out.println(valids);

    }
}


