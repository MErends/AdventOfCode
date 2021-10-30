package nl.erends.advent.year2016;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day13Test {

    @Test
    void day13Test1() {
        Day13 problem = new Day13();
        problem.setInput(1362);
        assertThat(problem.solve1(), is(82));
        problem = new Day13();
        problem.setInput(1362);
        assertThat(problem.solve2(), is(138));
    }
}
