package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day03Test {

    @Test
    void day03Test1() {
        Day03 problem = new Day03();
        problem.setInput(Util.readInput(2025, 3, 1));
        assertThat(problem.solve1(), is(357L));
        assertThat(problem.solve2(), is(3121910778619L));
    }
}
