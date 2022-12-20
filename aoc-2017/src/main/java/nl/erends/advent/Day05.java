package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day05 extends AbstractProblem<List<Integer>, Integer> {

	public static void main(String[] args) {
		new Day05().setAndSolve(Util.readIntegers(2017, 5));
	}

	@Override
	public Integer solve1() {
        int[] memory = input.stream()
                .mapToInt(i -> i)
                .toArray();
		int position = 0;
		int steps = 0;
        while (position >= 0 && position < memory.length) {
            int increment = memory[position];
            memory[position]++;
            position += increment;
            steps++;
        }
		return steps;
	}

	@Override
	public Integer solve2() {
        int[] memory = input.stream()
                .mapToInt(i -> i)
                .toArray();
        int position = 0;
        int steps = 0;
        while (position >= 0 && position < memory.length) {
            int increment = memory[position];
            if (increment >= 3) {
                memory[position]--;
            } else {
                memory[position]++;
            }
            position += increment;
            steps++;
        }
        return steps;
	}
}
