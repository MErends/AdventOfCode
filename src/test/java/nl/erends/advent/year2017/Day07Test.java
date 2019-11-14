package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day07Test {

	@Test
	public void day07Test1() {
	    Day07 problem = new Day07();
	    problem.setInput(Util.readInput(2017, 7, 1));
	    assertEquals("tknk", problem.solve1());
	    assertEquals("60", problem.solve2());
	}
}