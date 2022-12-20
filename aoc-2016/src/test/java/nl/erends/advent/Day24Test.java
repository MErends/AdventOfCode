package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day24Test {

    @Test
    void day24Test1() {
        Day24 problem = new Day24();
        problem.setInput(Util.readInput(2016, 24, 1));
        assertThat(problem.solve1(), is(14));
        assertThat(problem.solve2(), is(20));
    }
}
