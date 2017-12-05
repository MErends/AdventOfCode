package nl.erends.advent.year2017;

import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class Day5Test {
	@Test
	public void solve1() throws Exception {
		int[] memory = new int[]{0, 3, 0, 1, -3};
		assertEquals(Day5.solve1(memory), 5);

	}

	@Test
	public void solve2() throws Exception {
		int[] memory = new int[]{0, 3, 0, 1, -3};
		assertEquals(Day5.solve2(memory), 10);
	}

}