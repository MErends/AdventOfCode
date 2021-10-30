package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day03Test {

    @Test
    void day03Test1() {
        Day03 problem = new Day03();
        problem.setInput(Util.readInput(2019, 3, 1));
        assertThat(problem.solve1(), is(159));
        assertThat(problem.solve2(), is(610));
    }
}