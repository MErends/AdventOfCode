package nl.erends.advent.year2017;

import nl.erends.advent.util.FileIO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day6 {

	private static boolean PART_2 = false;

	public static void main(String[] args) {
		String[] input = FileIO.getFileAsString("2017day6.txt").split("\t");
		int[] memory = new int[input.length];
		for (int i = 0; i < input.length; i++) {
			memory[i] = Integer.parseInt(input[i]);
		}
		System.out.println(solve1(memory));
		for (int i = 0; i < input.length; i++) {
			memory[i] = Integer.parseInt(input[i]);
		}
		System.out.println(solve2(memory));
	}

	static int solve1(int[] memory) {
		List<Integer> appearances = new ArrayList<>();
		appearances.add(Arrays.hashCode(memory));
		int steps = 0;
		boolean secondLoop = false;
		while (true) {
			reallocate(memory);
			steps++;
			int hash = Arrays.hashCode(memory);
			if (appearances.contains(hash)) {
				if (Day6.PART_2 && !secondLoop) {
					appearances.clear();
					appearances.add(hash);
					steps = 0;
					secondLoop = true;
				} else {
					break;
				}
			} else {
				appearances.add(hash);
			}
		}
		return steps;
	}

	static int solve2(int[] memory) {
		Day6.PART_2 = true;
		return solve1(memory);
	}

	static void reallocate(int[] memory) {
		int highestIndex = 0;
		int highestValue = memory[highestIndex];
		for (int index = 0; index < memory.length; index++) {
			if (memory[index] > highestValue) {
				highestIndex = index;
				highestValue = memory[highestIndex];
			}
		}
		memory[highestIndex] = 0;
		while (highestValue != 0) {
			highestIndex++;
			highestIndex %= memory.length;
			memory[highestIndex]++;
			highestValue--;
		}
	}
}
