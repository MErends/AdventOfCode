package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day11Test {

    @Test
    void day11Test() {
        Day11 problem = new Day11();
        problem.setInput(Util.readInput(2021, 11, 1));
        assertThat(problem.solve1(), is(1656));
        assertThat(problem.solve2(), is(195));
    }
}
