package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day20Test {

    @Test
    void day20Test1() {
        Day20 problem = new Day20();
        problem.setInput(Util.readInput(2023, 20, 1));
        assertThat(problem.solve1(), is(32000000L));
    }

    @Test
    void day20Test2() {
        Day20 problem = new Day20();
        problem.setInput(Util.readInput(2023, 20, 2));
        assertThat(problem.solve1(), is(11687500L));
    }
}
