package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day14Test {

    @Test
    void day14Test1() {
        Day14 problem = new Day14();
        problem.setInput(Util.readInput(2015, 14, 1));
        problem.setMaxSeconds(1000);
        assertThat(problem.solve1(), is(1120));
        assertThat(problem.solve2(), is(689));
    }
}
