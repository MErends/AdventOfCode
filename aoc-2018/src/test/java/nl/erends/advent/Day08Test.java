package nl.erends.advent;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day08Test {

    @Test
    void day08Test1() {
        Day08 problem = new Day08();
        problem.setInput("2 3 0 3 10 11 12 1 1 0 1 99 2 1 1 2");
        assertThat(problem.solve1(), is(138));
        assertThat(problem.solve2(), is(66));
    }
}
