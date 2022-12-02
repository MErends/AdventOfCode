package nl.erends.advent.year2020;

import java.util.ArrayList;
import java.util.List;

class Console {

    private int accumulator = 0;
    private final List<String> instructions;
    private int pointer = 0;
    private final List<Integer> seenPointers = new ArrayList<>();

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

    private boolean execute() {
        if (pointer < 0 || pointer >= instructions.size()) {
            return false;
        }
        String instruction = instructions.get(pointer);
        String[] words = instruction.split(" ");
        switch (words[0]) {
            case "nop" -> pointer++;
            case "acc" -> {
                accumulator += Integer.parseInt(words[1]);
                pointer++;
            }
            case "jmp" -> pointer += Integer.parseInt(words[1]);
            default -> throw new IllegalArgumentException(words[0]);
        }
        return true;
    }

    int getAccumulator() {
        return accumulator;
    }
}
