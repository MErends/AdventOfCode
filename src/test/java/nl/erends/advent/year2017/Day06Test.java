package nl.erends.advent.year2017;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day06Test {

	@Test
    void day06Test1() {
	    Day06 problem = new Day06();
	    problem.setInput("0\t2\t7\t0");
	    assertThat(problem.solve1(), is(5));
	    assertThat(problem.solve2(), is(4));
	}
}