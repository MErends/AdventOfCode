package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day08Test {

	@Test
    void day08Test1() {
	    Day08 problem = new Day08();
	    problem.setInput(Util.readInput(2017, 8, 1));
	    assertEquals(1, problem.solve1().intValue());
	    assertEquals(10, problem.solve2().intValue());
	}
	
    @Test
    void day08Test2() {
        Day08 problem = new Day08();
        problem.setInput(Util.readInput(2017, 8, 2));
        assertEquals(5849, problem.solve1().intValue());
        assertEquals(6702, problem.solve2().intValue());
    }
}