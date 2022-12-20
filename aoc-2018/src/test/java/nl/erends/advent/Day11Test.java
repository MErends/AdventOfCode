package nl.erends.advent;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day11Test {

    @Test
    void day11Test1() {
        Day11 problem = new Day11();
        problem.setInput(18);
        assertThat(problem.solve1(), is("33,45"));
        assertThat(problem.solve2(), is("90,269,16"));
    }
}
