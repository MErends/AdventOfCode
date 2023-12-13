package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day12Test {

    @Test
    void day12Test() {
        Day12 problem = new Day12();
        problem.setInput(Util.readInput(2023, 12, 1));
        assertThat(problem.solve1(), is(21L));
        assertThat(problem.solve2(), is(525152L));
    }
}
