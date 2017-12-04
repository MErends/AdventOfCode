package nl.erends.advent.year2015;

import nl.erends.advent.util.FileIO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day8 {
    public static void main(String[] args) throws Exception {
        List<String> lines = FileIO.getFileAsList("C:\\Users\\marke\\IdeaProjects\\Adventofcode\\resource\\2015day8.txt");
        int totalDiff = 0;
        for (String line : lines) {
            System.out.println(line);
            int difference = 2;
            line = line.substring(1, line.length() - 1);
            while (line.contains("\\\\\\\\")) {
                line = line.replaceFirst("\\\\\\\\", "");
                System.out.println("dubbele slash gekilld");
                difference++;
            }
            System.out.println(line);
            String[] words = line.split("\\\\");
            for (String word : words) {
                System.out.print(word + " + ");
                if (word.startsWith("\"")) {
                    difference++;
                } else if (word.startsWith("x")) {
                    difference += 3;
                }
            }
            System.out.println(difference);
            totalDiff += difference;
        }

        System.out.println("Total: " + totalDiff);

    }
}
