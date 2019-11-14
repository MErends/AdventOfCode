package nl.erends.advent.year2017;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day06Test {

	@Test
	public void day06Test1() {
	    Day06 problem = new Day06();
	    problem.setInput("0\t2\t7\t0");
	    assertEquals(5, problem.solve1().intValue());
	    assertEquals(4, problem.solve2().intValue());
	}
}