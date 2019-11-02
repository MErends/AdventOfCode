package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;

import java.util.List;

public class Day5 {

	private static boolean PART_2 = false;

	public static void main(String[] args) {
		List<String> input = Util.getFileAsList("2017day5.txt");
		int[] memory = new int[input.size()];
		for (int i = 0; i < input.size(); i++) {
			memory[i] = Integer.parseInt(input.get(i));
		}
		System.out.println(solve1(memory));
		for (int i = 0; i < input.size(); i++) {
			memory[i] = Integer.parseInt(input.get(i));
		}
		System.out.println(solve2(memory));
	}

	static int solve1(int[] memory) {
		int position = 0;
		int steps = 0;
		while (true) {
			if (position < 0 || position >= memory.length) {
				break;
			}
			int increment = memory[position];
			if (Day5.PART_2 && increment >= 3) {
				memory[position]--;
			} else {
				memory[position]++;
			}
			position += increment;
			steps++;
		}
		return steps;
	}

	static int solve2(int[] memory) {
		Day5.PART_2 = true;
		return solve1(memory);
	}
}
