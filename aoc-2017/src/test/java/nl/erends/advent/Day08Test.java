package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day08Test {

	@Test
    void day08Test1() {
	    Day08 problem = new Day08();
	    problem.setInput(Util.readInput(2017, 8, 1));
	    assertThat(problem.solve1(), is(1));
	    assertThat(problem.solve2(), is(10));
	}
	
    @Test
    void day08Test2() {
        Day08 problem = new Day08();
        problem.setInput(Util.readInput(2017, 8, 2));
        assertThat(problem.solve1(), is(5849));
        assertThat(problem.solve2(), is(6702));
    }
}
