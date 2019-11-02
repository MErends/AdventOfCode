package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;

import java.util.List;

public class Day13 {

	private static int[] depthAtLevel;
	private static int[] scannerposition;
	private static boolean[] scannerMovingDown;

	public static void main(String[] args) {
		List<String> lines = Util.getFileAsList("2017day13.txt");
		depthAtLevel = new int[Integer.parseInt(lines.get(lines.size() - 1).split(":")[0].trim()) + 1];
		scannerposition = new int[Integer.parseInt(lines.get(lines.size() - 1).split(":")[0].trim()) + 1];
		scannerMovingDown = new boolean[Integer.parseInt(lines.get(lines.size() - 1).split(":")[0].trim()) + 1];
		for (String line : lines) {
			String[] words = line.split(":");
			depthAtLevel[Integer.parseInt(words[0].trim())] = Integer.parseInt(words[1].trim());
			scannerposition[Integer.parseInt(words[0].trim())] = Integer.parseInt(words[1].trim()) != 0 ? 1 : -1;
			scannerMovingDown[Integer.parseInt(words[0].trim())] = true;
		}
		initializeScannerPosition();
		System.out.println(getPenalty());
		int delay = 0;
		while (hasPenalty()) {
			delay++;
			updateScanners();
		}
		System.out.println(delay);
	}

	private static int getPenalty() {
		int penalty = 0;
		for (int level = 0; level < depthAtLevel.length; level++) {
			int depth = depthAtLevel[level];
			if (scannerposition[level] == 1) {
				penalty += level * depth;
			}
		}
		return penalty;
	}

	private static boolean hasPenalty() {
		for (int level = 0; level < depthAtLevel.length; level++) {
			if (scannerposition[level] == 1) {
				return true;
			}
		}
		return false;
	}

	private static void updateScanners() {
		for (int level = 0; level < depthAtLevel.length; level++) {
			int depth = depthAtLevel[level];
			if (depth == 0)
				continue;
			int position = scannerposition[level];
			if (scannerMovingDown[level]) {
				position++;
				if (position == depth)
					scannerMovingDown[level] = false;
			} else {
				position--;
				if (position == 1)
					scannerMovingDown[level] = true;
			}
			scannerposition[level] = position;
		}
	}

	private static void initializeScannerPosition() {
		for (int level = 0; level < depthAtLevel.length; level++) {
			int depth = depthAtLevel[level];
			if (depth == 0) continue;
			int position = 1;
			for (int i = 0; i < level; i++) {
				if (scannerMovingDown[level]) {
					position++;
					if (position == depth) scannerMovingDown[level] = false;
				} else {
					position--;
					if (position == 1) scannerMovingDown[level] = true;
				}
			}
			scannerposition[level] = position;
		}
	}
}
