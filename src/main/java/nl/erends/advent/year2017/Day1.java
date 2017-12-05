package nl.erends.advent.year2017;

import nl.erends.advent.util.FileIO;

public class Day1 {

	public static void main(String[] args) {
		String input = FileIO.getFileAsString("2017day1.txt");
		System.out.println(solve1(input));
		System.out.println(solve2(input));
	}

	public static int solve1(String input) {
		int length = input.length();
		int sum = 0;
		String b = input.substring(1) + input.substring(0, 1);
		for (int i = 0; i < length; i++) {
			int ia = Integer.parseInt(input.substring(i, i + 1));
			int ib = Integer.parseInt(b.substring(i, i + 1));
			if (ia == ib) {
				sum += ia;
			}
		}
		return sum;
	}

	public static int solve2(String input) {
		int length = input.length();
		int sum = 0;
		String a = input.substring(0, length / 2);
		String b = input.substring(length / 2);
		for (int i = 0; i < length / 2; i++) {
			int ia = Integer.parseInt(a.substring(i, i + 1));
			int ib = Integer.parseInt(b.substring(i, i + 1));
			if (ia == ib) {
				sum += ia + ia;
			}
		}
		return sum;
	}
}
