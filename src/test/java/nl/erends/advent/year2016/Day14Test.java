package nl.erends.advent.year2016;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day14Test {

    @Test
    void day14Test1() {
        Day14 problem = new Day14();
        problem.setInput("abc");
        assertThat(problem.solve1(), is(22728));
    }
}
