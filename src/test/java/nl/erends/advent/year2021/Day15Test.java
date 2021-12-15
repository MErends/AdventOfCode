package nl.erends.advent.year2021;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day15Test {

    @Test
    void day15Test() {
        Day15 problem = new Day15();
        problem.setInput(Util.readInput(2021, 15, 1));
        assertThat(problem.solve1(), is(40));
        assertThat(problem.solve2(), is(315));
    }
}
