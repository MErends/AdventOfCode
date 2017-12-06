package nl.erends.advent.year2017;

import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;


public class Day6Test {

	@Test
	public void solve1Test() {
		int[] memory = new int[]{0, 2, 7, 0};
		assertEquals(Day6.solve1(memory), 5);

	}

	@Test
	public void solve2Test() {
		int[] memory = new int[]{0, 2, 7, 0};
		assertEquals(Day6.solve2(memory), 4);
	}

	@Test
	public void reallocateTest() {
		int[] memory = new int[]{0, 2, 7, 0};
		Day6.reallocate(memory);
		assertArrayEquals(memory, new int[]{2, 4, 1, 2});
	}
}