package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day20Test {

    @Test
    void day20Test() {
        Day20 problem = new Day20();
        problem.setInput(Util.readInput(2022, 20, 1));
        assertThat(problem.solve1(), is (3L));
        assertThat(problem.solve2(), is (1623178306L));
    }
}
