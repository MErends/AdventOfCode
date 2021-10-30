package nl.erends.advent.year2020;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day15Test {

    @Test
    void day15Test() {
        Day15 problem = new Day15();
        problem.setInput("0,3,6");
        assertThat(problem.solve1(), is(436));
        assertThat(problem.solve2(), is(175594));
    }
}
