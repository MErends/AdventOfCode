package nl.erends.advent;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day24Test {

    @Test
    void day24Test() {
        Day24 problem = new Day24();
        problem.setInput(Arrays.asList(1, 2, 3, 4, 5, 7, 8, 9, 10, 11));
        assertThat(problem.solve1(), is(99L));
        assertThat(problem.solve2(), is(44L));
    }
}
