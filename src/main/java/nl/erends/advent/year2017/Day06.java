package nl.erends.advent.year2017;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day06 extends AbstractProblem<String, Integer> {

	public static void main(String[] args) {
        new Day06().setAndSolve(Util.readLine(2017, 6));
	}

	@Override
	public Integer solve1() {
	    String[] numbers = input.split("\t");
	    int[] memory = new int[numbers.length];
	    for (int i = 0; i < numbers.length; i++) {
	        memory[i] = Integer.parseInt(numbers[i]);
        }
		List<Integer> appearances = new ArrayList<>();
		appearances.add(Arrays.hashCode(memory));
		int steps = 0;
		while (true) {
			reallocate(memory);
			steps++;
			int hash = Arrays.hashCode(memory);
			if (appearances.contains(hash)) {
			    break;
			} else {
				appearances.add(hash);
			}
		}
		return steps;
	}

	@Override
	public Integer solve2() {
        String[] numbers = input.split("\t");
        int[] memory = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            memory[i] = Integer.parseInt(numbers[i]);
        }
        List<Integer> appearances = new ArrayList<>();
        appearances.add(Arrays.hashCode(memory));
        int steps = 0;
        boolean secondLoop = false;
        while (true) {
            reallocate(memory);
            steps++;
            int hash = Arrays.hashCode(memory);
            if (appearances.contains(hash)) {
                if (!secondLoop) {
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

	private void reallocate(int[] memory) {
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
