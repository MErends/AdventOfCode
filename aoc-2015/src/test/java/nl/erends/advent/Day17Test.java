package nl.erends.advent;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day17Test {

    @Test
    void day17Test1() {
        Day17 problem = new Day17();
        problem.setInput(Arrays.asList(20, 15, 10, 5, 5));
        problem.setTestTarget();
        assertThat(problem.solve1(), is(4));
        assertThat(problem.solve2(), is(3));
    }
}
