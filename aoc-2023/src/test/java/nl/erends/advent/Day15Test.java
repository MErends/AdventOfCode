package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day15Test {

    @Test
    void day15Test() {
        Day15 problem = new Day15();
        problem.setInput(Util.readInput(2023, 15, 1));
        assertThat(problem.solve1(), is(1320));
        assertThat(problem.solve2(), is(145));
    }
}
