package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day04Test {

    @Test
    void day04Test1() {
        Day04 problem = new Day04();
        problem.setInput(Util.readInput(2025, 4, 1));
        assertThat(problem.solve1(), is(13));
        assertThat(problem.solve2(), is(43));
    }
}
