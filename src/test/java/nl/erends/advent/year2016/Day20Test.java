package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day20Test {

    @Test
    void day20Test1() {
        Day20 problem = new Day20();
        problem.setInput(Util.readInput(2016, 20, 1));
        problem.setMaxValue(9);
        assertThat(problem.solve1(), is(3));
        assertThat(problem.solve2(), is(2));
    }
}
