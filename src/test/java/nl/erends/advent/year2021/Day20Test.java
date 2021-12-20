package nl.erends.advent.year2021;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day20Test {

    @Test
    void day20Test() {
        Day20 problem = new Day20();
        problem.setInput(Util.readInput(2021, 20, 1));
        assertThat(problem.solve1(), is(35));
        assertThat(problem.solve2(), is(3351));
    }
}
