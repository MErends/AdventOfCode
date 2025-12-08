package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day08Test {

    @Test
    void day08Test1() {
        Day08 problem = new Day08();
        problem.setInput(Util.readInput(2025, 8, 1));
        problem.setTestTarget();
        assertThat(problem.solve1(), is(40L));
        assertThat(problem.solve2(), is(25272L));
    }
}
