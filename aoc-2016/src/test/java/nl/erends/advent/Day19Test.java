package nl.erends.advent;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day19Test {

    @Test
    void day19Test1() {
        Day19 problem = new Day19();
        problem.setInput(5);
        assertThat(problem.solve1(), is(3));
        assertThat(problem.solve2(), is(2));
    }
}
