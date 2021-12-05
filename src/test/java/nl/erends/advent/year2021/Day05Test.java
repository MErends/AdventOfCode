package nl.erends.advent.year2021;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day05Test {

    @Test
    void day05Test() {
        Day05 problem = new Day05();
        problem.setInput(Util.readInput(2021, 5, 1));
        assertThat(problem.solve1(), is(5));
        assertThat(problem.solve2(), is(12));
    }
}