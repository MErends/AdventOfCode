package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day06Test {

    @Test
    void day06Test1() {
        Day06 problem = new Day06();
        problem.setInput(Util.readInput(2025, 6, 1));
        assertThat(problem.solve1(), is(4277556L));
        assertThat(problem.solve2(), is(3263827L));
    }
}
