package nl.erends.advent.year2017;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day15Test {

    @Test
    void day15Test1() {
        Day15 problem = new Day15();
        problem.setInput("65,8921");
        assertThat(problem.solve1(), is(588));
        assertThat(problem.solve2(), is(309));
    }
}