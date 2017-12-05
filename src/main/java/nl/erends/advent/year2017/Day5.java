package nl.erends.advent.year2017;

import nl.erends.advent.util.FileIO;

import java.util.Arrays;
import java.util.List;

public class Day5 {

	public static void main(String[] args) {
		List<String> input = FileIO.getFileAsList("2017day5.txt");
		int[] memory = new int[input.size()];
		for (int i = 0; i < input.size(); i++) {
			memory[i] = Integer.parseInt(input.get(i));
		}
		System.out.println(solve1(memory));
	}

	public static int solve1(int[] memory) {
		int position = 0;
		int steps = 0;
		try {
			for( ; ; steps++) {
				System.out.println("Steps:" + steps + "Position: " + position);
				int increment = memory[position];
				System.out.println("Increment: " + increment);
				memory[position]++;
				position += increment;
			}
		} catch (Exception e) {

		}
		return steps;
	}
}
