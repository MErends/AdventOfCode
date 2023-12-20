package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day18Test {

    @Test
    void day18Test() {
        Day18 problem = new Day18();
        problem.setInput(Util.readInput(2023, 18, 1));
        assertThat(problem.solve1(), is(62));
        assertThat(problem.solve2(), is(952408144115L));
    }
}
