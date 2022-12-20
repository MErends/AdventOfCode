package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day08Test {

    @Test
    void day08Test() {
        Day08 problem = new Day08();
        problem.setInput(Util.readInput(2021, 8, 1));
        assertThat(problem.solve1(), is(26));
        assertThat(problem.solve2(), is(61229));
    }
}
