package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day11Test {

    @Test
    void deay11Test() {
        Day11 problem = new Day11();
        problem.setInput(Util.readInput(2020, 11, 1));
        assertThat(problem.solve1(), is(37));
        assertThat(problem.solve2(), is(26));
    }
}
