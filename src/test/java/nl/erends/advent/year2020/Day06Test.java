package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day06Test {
    
    @Test
    void day06Test() {
        Day06 problem = new Day06();
        problem.setInput(Util.readInput(2020, 6, 1));
        assertThat(problem.solve1(), is(11));
        assertThat(problem.solve2(), is(6));
    }
}
