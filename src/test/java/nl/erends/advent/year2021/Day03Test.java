package nl.erends.advent.year2021;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day03Test {

    @Test
    void day03Test() {
        Day03 problem = new Day03();
        problem.setInput(Util.readInput(2021, 3, 1));
        assertThat(problem.solve1(), is(198));
        assertThat(problem.solve2(), is(230));
    }
}