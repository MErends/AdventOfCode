package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day13Test {

    @Test
    void day13Test() {
        Day13 problem = new Day13();
        problem.setInput(Util.readInput(2023, 13, 1));
        assertThat(problem.solve1(), is(405));
        assertThat(problem.solve2(), is(400));
    }
}
