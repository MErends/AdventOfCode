package nl.erends.advent.year2016;


import nl.erends.advent.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day23 {

    public static void main(String[] args) {
        List<String> lines = Util.getFileAsList("2016day23_test.txt");
        Map<String, Integer> memoryBank = new HashMap<>();
        memoryBank.put("a", 7);
        memoryBank.put("b", 0);
        memoryBank.put("c", 0);
        memoryBank.put("d", 0);
        int index = 0;
        while (index >= 0 && index < lines.size()) {
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
                    if (!memoryBank.containsKey(words[2])) {
                        break;
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
                case "tgl": {
                    String memory = words[1];
                    int memoryValue;
                    if (memoryBank.containsKey(memory)) {
                        memoryValue = memoryBank.get(memory);
                    } else {
                        memoryValue = Integer.parseInt(words[1]);
                    }
                    int targetIndex = index + memoryValue;
                    if (targetIndex < 0 || targetIndex >= lines.size()) break;
                    String targetCommand = lines.get(targetIndex);
                    switch (targetCommand.split(" ")[0]) {
                        case "inc": {
                            lines.set(targetIndex, "dec" + targetCommand.substring(3));
                            break;
                        }
                        case "dec":
                        case "tgl": {
                            lines.set(targetIndex, "inc" + targetCommand.substring(3));
                            break;
                        }
                        case "jnz": {
                            lines.set(targetIndex, "cpy" + targetCommand.substring(3));
                            break;
                        }
                        case "cpy": {
                            lines.set(targetIndex, "jnz" + targetCommand.substring(3));
                            break;
                        }
                    }
                    break;
                }
            }
            index++;
        }
        System.out.println(memoryBank.get("a"));
    }
}
