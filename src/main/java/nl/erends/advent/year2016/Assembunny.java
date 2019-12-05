package nl.erends.advent.year2016;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Assembunny {
    
    private Map<String, Integer> memoryBank = new HashMap<>();
    private List<String> instructions;
    private int outputLimit;
    private String output = "";
    
    Assembunny(List<String> instructions, int outputLimit) {
        this.instructions = new ArrayList<>(instructions);
        this.outputLimit = outputLimit;
    }
    
    Assembunny(List<String> instructions) {
        this(instructions, Integer.MAX_VALUE);
    }
    
    public void execute() {
        int pointer = 0;
        while (pointer >= 0 && pointer < instructions.size() && output.length() < outputLimit) {
            String instruction = instructions.get(pointer);
            String[] words = instruction.split(" ");
            switch (words[0]) {
                default:
                case "inc":
                    doIncrease(words[1]);
                    pointer++;
                    break;
                case "dec":
                    doDecrease(words[1]);
                    pointer++;
                    break;
                case "cpy":
                    doCopy(words[1], words[2]);
                    pointer++;
                    break;
                case "jnz": 
                    pointer += doJumpWhenNotZero(words[1], words[2]);
                    break;
                case "tgl":
                    doToggle(words[1], pointer);
                    pointer++;
                    break;
                case "out":
                    doOut(words[1]);
                    pointer++;
                    break;
            }
        }
    }
    
    private int getMemory(String key) {
        try {
            return Integer.parseInt(key);
        } catch (NumberFormatException e) {
            return memoryBank.computeIfAbsent(key, k -> 0);
        }
    }
    
    private void doIncrease(String memory) {
        int value = getMemory(memory);
        value++;
        memoryBank.put(memory, value);
    }

    private void doDecrease(String memory) {
        int value = getMemory(memory);
        value--;
        memoryBank.put(memory, value);
    }
    
    private void doCopy(String from, String to) {
        int value = getMemory(from);
        String target = Integer.toString(getMemory(to));
        if (!target.equals(to)) {
            memoryBank.put(to, value);
        }
    }
    
    private int doJumpWhenNotZero(String memory, String offset) {
        int value = getMemory(memory);
        if (value != 0) {
            return getMemory(offset);
        } else {
            return 1;
        }
    }
    
    private void doToggle(String memory, int pointer) {
        int value = getMemory(memory);
        pointer += value;
        if (pointer < 0 || pointer >= instructions.size()) {
            return;
        }
        String instruction = instructions.get(pointer);
        switch (instruction.split(" ")[0]) {
            case "inc":
                instructions.set(pointer, "dec" + instruction.substring(3));
                break;
            case "jnz":
                instructions.set(pointer, "cpy" + instruction.substring(3));
                break;
            case "cpy":
                instructions.set(pointer, "jnz" + instruction.substring(3));
                break;
            default:
                instructions.set(pointer, "inc" + instruction.substring(3));
                break;
        }
    }
    
    private void doOut(String memory) {
        output += getMemory(memory);
    }

    Map<String, Integer> getMemoryBank() {
        return memoryBank;
    }

    public String getOutput() {
        return output;
    }
}
