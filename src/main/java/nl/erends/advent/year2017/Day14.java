package nl.erends.advent.year2017;

import java.util.ArrayList;
import java.util.List;

public class Day14 {

	static Square[][] squares = new Square[128][128];

	public static void main(String[] args) {

		String input = "hwlqcszp";
		int usedSpaces = 0;
		for (int nonce = 0; nonce < 128; nonce++) {
			String knotHash = knotHash(input + "-" + nonce);
			String binaryRep = toBinaryRep(knotHash);
			int x = 0;
			for (char c : binaryRep.toCharArray()) {
				Square square = new Square(x, nonce);
				if (c == '1') {
					usedSpaces++;
					square.setData();
				}
				squares[nonce][x] = square;
				x++;
			}
		}
		System.out.println(usedSpaces);
		int group = 0;
		for (Square[] row : squares) {
			for (Square square : row) {
				if (square.hasData() && !square.isGrouped()) {
					square.setGroup(++group);
				}
			}
		}
		System.out.println(group);
	}


	private static String toBinaryRep(String input) {
		StringBuilder output = new StringBuilder();
		for (int index = 0; index < input.length(); index++) {
			String letter = input.substring(index, index + 1);
			int numberRep = Integer.parseInt(letter, 16);
			StringBuilder letterBuilder = new StringBuilder(Integer.toBinaryString(numberRep));
			while (letterBuilder.length() < 4) {
				letterBuilder.insert(0, '0');
			}
//			System.out.println(letter + " " + letterBuilder);
			output.append(letterBuilder);
		}
		return output.toString();
	}

	private static String knotHash(String input) {
		List<Integer> chain = new ArrayList<>();
		for (int i = 0; i < 256; i++) {
			chain.add(i);
		}
		int chainlength = chain.size();
		List<Integer> inputs = new ArrayList<>();
		for (char c : input.toCharArray()) {
			inputs.add((int) c);
		}
		inputs.add(17);
		inputs.add(31);
		inputs.add(73);
		inputs.add(47);
		inputs.add(23);
		int currentPosition = 0;
		int skipSize = 0;
		for (int round = 0; round < 64; round++) {
			for (int inputNum : inputs) {
				for (int offset = 0; offset < inputNum / 2; offset++) {
					int temp = chain.get((currentPosition + offset) % chainlength);
					chain.set((currentPosition + offset) % chainlength, chain.get((currentPosition + inputNum - offset - 1) % chainlength));
					chain.set((currentPosition + inputNum - offset - 1) % chainlength, temp);
				}
				currentPosition = (currentPosition + inputNum + skipSize) % chainlength;
				skipSize++;
			}
		}
		List<Integer> output = new ArrayList<>();
		for (int index = 0; index < 16; index++) {
			int letter = 0;
			List<Integer> subChain = chain.subList(index * 16, index * 16 + 16);
			for (int value : subChain) {
				letter ^= value;
			}
			output.add(letter);
		}
		StringBuilder outputString = new StringBuilder();
		for (int value : output) {
			if (value < 16) {
				outputString.append("0");
			}
			outputString.append(Integer.toHexString(value));
		}
		return outputString.toString();
	}
}

class Square {
	private int x;
	private int y;
	private boolean data;
	private int group;

	Square(int x, int y) {
		this.x = x;
		this.y = y;
		this.data = false;
		this.group = 0;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	boolean hasData() {
		return data;
	}

	void setData() {
		this.data = true;
	}

	void setGroup(int group) {
		this.group = group;
		if (x != 0) {
			Square square = Day14.squares[y][x - 1];
			if (square.hasData() && !square.isGrouped()) square.setGroup(group);
		}
		if (x != 127) {
			Square square = Day14.squares[y][x + 1];
			if (square.hasData() && !square.isGrouped()) square.setGroup(group);
		}
		if (y != 0) {
			Square square = Day14.squares[y - 1][x];
			if (square.hasData() && !square.isGrouped()) square.setGroup(group);
		}
		if (y != 127) {
			Square square = Day14.squares[y + 1][x];
			if (square.hasData() && !square.isGrouped()) square.setGroup(group);
		}
	}

	boolean isGrouped() {
		return group != 0;
	}
}