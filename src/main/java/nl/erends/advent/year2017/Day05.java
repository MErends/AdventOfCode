package nl.erends.advent.year2017;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day05 extends AbstractProblem<List<String>, Integer> {

	public static void main(String[] args) {
		new Day05().setAndSolve(Util.readInput(2017, 5));
	}

	@Override
	public Integer solve1() {
        int[] memory = new int[input.size()];
        for (int i = 0; i < input.size(); i++) {
            memory[i] = Integer.parseInt(input.get(i));
        }
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
        int[] memory = new int[input.size()];
        for (int i = 0; i < input.size(); i++) {
            memory[i] = Integer.parseInt(input.get(i));
        }
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
