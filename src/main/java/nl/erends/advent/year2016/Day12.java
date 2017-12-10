package nl.erends.advent.year2016;


import nl.erends.advent.util.FileIO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day12 {

    public static void main(String[] args) {
        List<String> lines = FileIO.getFileAsList("2016day12.txt");
        Map<String, Integer> memoryBank = new HashMap<>();
        memoryBank.put("a", 0);
        memoryBank.put("b", 0);
        memoryBank.put("c", 1);
        memoryBank.put("d", 0);
        for (int index = 0; index < lines.size(); index++) {
            String command = lines.get(index);
            String[] words = command.split(" ");
            switch (words[0]) {
                case "inc": {
                    String memory = words[1];
                    int memoryValue = memoryBank.get(memory);
                    memoryValue++;
                    memoryBank.put(memory, memoryValue);
                    break;
                }
                case "dec": {
                    String memory = words[1];
                    int memoryValue = memoryBank.get(memory);
                    memoryValue--;
                    memoryBank.put(memory, memoryValue);
                    break;
                }
                case "cpy": {
                    String memory = words[1];
                    int memoryValue;
                    if (memoryBank.containsKey(memory)) {
                        memoryValue = memoryBank.get(memory);
                    } else {
                        memoryValue = Integer.parseInt(words[1]);
                    }
                    memoryBank.put(words[2], memoryValue);
                    break;
                }
                case "jnz": {
                    String memory = words[1];
                    int memoryValue;
                    if (memoryBank.containsKey(memory)) {
                        memoryValue = memoryBank.get(memory);
                    } else {
                        memoryValue = Integer.parseInt(words[1]);
                    }
                    if (memoryValue != 0) {
                        index += Integer.parseInt(words[2]) - 1;
                    }
                    break;
                }
            }
        }
        System.out.println(memoryBank.get("a"));
    }
}
