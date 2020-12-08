package nl.erends.advent.year2020;

import java.util.ArrayList;
import java.util.List;

public class Console {

    private int accumulator = 0;
    private List<String> instructions;
    private int pointer = 0;
    private List<Integer> seenPointers = new ArrayList<>();

    Console(List<String> instructions) {
        this.instructions = new ArrayList<>(instructions);
    }

    boolean isInfiniteLoop() {
        seenPointers.add(pointer);
        while (execute()) {
            if (seenPointers.contains(pointer)) {
                return true;
            } else {
                seenPointers.add(pointer);
            }
        }
        return false;
    }

    public boolean execute() {
        if (pointer < 0 || pointer >= instructions.size()) {
            return false;
        }
        String instruction = instructions.get(pointer);
        String[] words = instruction.split(" ");
        switch (words[0]) {
            default:
            case "nop":
                pointer++;
                break;
            case "acc":
                accumulator += Integer.parseInt(words[1]);
                pointer++;
                break;
            case "jmp":
                pointer += Integer.parseInt(words[1]);
                break;
        }
        return true;
    }

    int getAccumulator() {
        return accumulator;
    }
}
