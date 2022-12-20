package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

public class Day01 extends AbstractProblem<String, Integer> {

	public static void main(String[] args) {
	    new Day01().setAndSolve(Util.readLine(2017, 1));
	}

	@Override
	public Integer solve1() {
		int length = input.length();
		int sum = 0;
		String b = input.substring(1) + input.charAt(0);
		for (int i = 0; i < length; i++) {
			int ia = Integer.parseInt(input.substring(i, i + 1));
			int ib = Integer.parseInt(b.substring(i, i + 1));
			if (ia == ib) {
				sum += ia;
			}
		}
		return sum;
	}

	@Override
	public Integer solve2() {
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
