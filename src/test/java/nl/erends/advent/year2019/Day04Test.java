package nl.erends.advent.year2019;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day04Test {

    @Test
    void day04Test1() {
        Day04 problem = new Day04();
        problem.setInput("357253-892942");
        assertThat(problem.solve1(), is(530));
        assertThat(problem.solve2(), is(324));
    }
}