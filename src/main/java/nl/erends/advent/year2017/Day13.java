package nl.erends.advent.year2017;

import nl.erends.advent.util.FileIO;

import java.util.List;

public class Day13 {

	private static int[] depthAtLevel;

	public static void main(String[] args) {
		List<String> lines = FileIO.getFileAsList("2017day13.txt");
		depthAtLevel = new int[Integer.parseInt(lines.get(lines.size() - 1).split(":")[0].trim()) + 1];
		for (String line : lines) {
			String[] words = line.split(":");
			depthAtLevel[Integer.parseInt(words[0].trim())] = Integer.parseInt(words[1].trim());
		}
		System.out.println(getPenalty(0));
		int delay = 0;
		while (true) {
			int penalty = getPenalty(delay);
//			System.out.println("Delay: " + delay + ", Penalty: " + penalty);
			if (penalty == 0) break;
			delay++;
		}
		System.out.println(delay);
	}

	private static int getPenalty(int delay) {
		int penalty = 0;
		for (int index = 0; index < depthAtLevel.length; index++) {
			int depth = depthAtLevel[index];
			int scannerPosition = getScannerPosition(index, delay + index);
			if (scannerPosition == 1) {
				penalty += index * depth;
			}
		}
		return penalty;
	}

	private static int getScannerPosition(int level, int atSecond) {
		int depth = depthAtLevel[level];
		if (depth == 0) return -1;
		int position = 1;
		boolean goingDown = true;
		for (int second = 0; second < atSecond; second++) {
			if (goingDown) {
				position++;
				if (position == depth) goingDown = false;
			} else {
				position--;
				if (position == 1) goingDown = true;
			}
		}
		return position;
	}



}