package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day20Test {

    @Test
    void day20Test1() {
        Day20 problem = new Day20();
        problem.setInput(Util.readInput(2024, 20, 1));
        problem.setTestValue(2);
        assertThat(problem.solve1(), is (44));
        problem.setTestValue(50);
        assertThat(problem.solve2(), is(285));
    }
}
