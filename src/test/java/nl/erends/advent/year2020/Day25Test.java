package nl.erends.advent.year2020;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day25Test {

    @Test
    void day25Test() {
        Day25 problem = new Day25();
        problem.setInput(Arrays.asList("5764801", "17807724"));
        assertThat(problem.solve1(), is(14897079L));
    }
}
