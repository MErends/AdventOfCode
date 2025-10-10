package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day08 extends AbstractProblem<List<String>, Integer> {
    
	void main() {
        new Day08().setAndSolve(Util.readInput(2017, 8));
    }
    
    @Override
    public Integer solve1() {
	    answer2 = 0;
		Map<String, Integer> memory = new HashMap<>();
		for (String line : input) {
            processLine(line, memory);
			for (int value : memory.values()) {
				answer2 = Math.max(value, answer2);
			}
		}
		int maxMemory = Integer.MIN_VALUE;
		for (int value : memory.values()) {
		    maxMemory = Math.max(maxMemory, value);
        }
		return maxMemory;
	}

    private void processLine(String line, Map<String, Integer> memory) {
        String[] words = line.split(" ");
        String pointer = words[0];
        int multipy =  words[1].equals("inc") ? 1 : -1;
        int alteration = Integer.parseInt(words[2]);
        String comparePointer = words[4];
        String comparator = words[5];
        int compareValue = Integer.parseInt(words[6]);
        int pointerValue = memory.computeIfAbsent(pointer, _ -> 0);
        int comparePointerValue = memory.computeIfAbsent(comparePointer, _ -> 0);
        switch (comparator) {
            case ">=":
                if (comparePointerValue >= compareValue) {
                    pointerValue += multipy * alteration;
                }
                break;
            case "<":
                if (comparePointerValue < compareValue) {
                    pointerValue += multipy * alteration;
                }
                break;
            case ">":
                if (comparePointerValue > compareValue) {
                    pointerValue += multipy * alteration;
                }
                break;
            case "!=":
                if (comparePointerValue != compareValue) {
                    pointerValue += multipy * alteration;
                }
                break;
            case "==":
                if (comparePointerValue == compareValue) {
                    pointerValue += multipy * alteration;
                }
                break;
            case "<=":
            default:
                if (comparePointerValue <= compareValue) {
                    pointerValue += multipy * alteration;
                }
                break;
        }
        memory.put(pointer, pointerValue);
    }

}
