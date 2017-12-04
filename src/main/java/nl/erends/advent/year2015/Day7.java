package nl.erends.advent.year2015;

import nl.erends.advent.util.FileIO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day7 {
    public static void main(String[] args) throws Exception {
        List<String> lines = FileIO.getFileAsList("C:\\Users\\marke\\IdeaProjects\\Adventofcode\\resource\\2015day7.txt");
        Map<String, Integer> kabels = new HashMap<>();
        for (int i = 1; i < 16; i++) {
            kabels.put(Integer.toString(i), i);
        }
        kabels.put("b", 3176);
        while (!kabels.containsKey("a")) {
            for (String line : lines) {
                if (line.contains("AND")) {
                    String[] words = line.split(" ");
                    String input1 = words[0];
                    String input2 = words[2];
                    String output = words[4];
                    if (kabels.containsKey(input1) && kabels.containsKey(input2)) {
                        kabels.put(output, kabels.get(input1) & kabels.get(input2));
                    }
                } else if (line.contains("OR")) {
                    String[] words = line.split(" ");
                    String input1 = words[0];
                    String input2 = words[2];
                    String output = words[4];
                    if (kabels.containsKey(input1) && kabels.containsKey(input2)) {
                        kabels.put(output, kabels.get(input1) | kabels.get(input2));
                    }
                } else if (line.contains("LSHIFT")) {
                    String[] words = line.split(" ");
                    String input1 = words[0];
                    String input2 = words[2];
                    String output = words[4];
                    if (kabels.containsKey(input1) && kabels.containsKey(input2)) {
                        kabels.put(output, kabels.get(input1) << kabels.get(input2));
                    }
                } else if (line.contains("RSHIFT")) {
                    String[] words = line.split(" ");
                    String input1 = words[0];
                    String input2 = words[2];
                    String output = words[4];
                    if (kabels.containsKey(input1) && kabels.containsKey(input2)) {
                        kabels.put(output, kabels.get(input1) >> kabels.get(input2));
                    }
                } else if (line.contains("NOT")) {
                    String[] words = line.split(" ");
                    String input = words[1];
                    String output = words[3];
                    if (kabels.containsKey(input)) {
                        kabels.put(output, ~kabels.get(input) & 0xfff);
                    }
                } else {
                    String[] words = line.split(" ");
                    String input = words[0];
                    String output = words[2];
                    try {
                        kabels.put(output, Integer.parseInt(input));
                    } catch (NumberFormatException ne) {
                        if (kabels.containsKey(input)) {
                            kabels.put(output, kabels.get(input));
                        }
                    }
                }
            }
        }
        System.out.println(kabels.get("a"));
    }
}
