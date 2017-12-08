package nl.erends.advent.year2017;

import nl.erends.advent.util.FileIO;

import java.util.*;

public class Day8 {


	public static void main(String[] args) {
		List<String> lines = FileIO.getFileAsList("2017day8.txt");
		Map<String, Integer> memory = new HashMap<>();
		int maxValue = Integer.MIN_VALUE;
		for (String line : lines) {
			String[] words = line.split(" ");
			String pointer = words[0];
			int multipy =  words[1].equals("inc") ? 1 : -1;
			int alteration = Integer.parseInt(words[2]);
			String comparePointer = words[4];
			String comparator = words[5];
			int compareValue = Integer.parseInt(words[6]);

			if (!memory.containsKey(pointer)) {
				memory.put(pointer, 0);
			}
			if (!memory.containsKey(comparePointer)) {
				memory.put(comparePointer, 0);
			}
			int pointerValue = memory.get(pointer);
			int comparePointerValue = memory.get(comparePointer);
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
					if (comparePointerValue <= compareValue) {
						pointerValue += multipy * alteration;
					}
					break;
			}
			memory.put(pointer, pointerValue);
			for (int value : memory.values()) {
				maxValue = Math.max(value, maxValue);
			}
		}
		System.out.println(maxValue);
	}

}