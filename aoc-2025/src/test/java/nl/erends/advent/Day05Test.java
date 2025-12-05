package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day05Test {

    @Test
    void day05Test1() {
        Day05 problem = new Day05();
        problem.setInput(Util.readInput(2025, 5, 1));
        assertThat(problem.solve1(), is(3L));
        assertThat(problem.solve2(), is(14L));
    }
}
