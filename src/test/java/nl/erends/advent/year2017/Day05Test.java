package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class Day05Test {
    
	@Test
	public void day05Test1() {
		Day05 problem = new Day05();
		problem.setInput(Util.readInput(2017, 5, 1));
		assertEquals(5, problem.solve1().intValue());
        assertEquals(10, problem.solve2().intValue());
	}
}