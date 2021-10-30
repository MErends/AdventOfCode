package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day18Test {

    @Test
    void day18Test1() {
        Day18 problem = new Day18();
        problem.setInput(Util.readInput(2017, 18, 1));
        assertThat(problem.solve1().intValue(), is(4));
    }

    @Test
    void day18Test2() {
        Day18 problem = new Day18();
        problem.setInput(Util.readInput(2017, 18, 2));
        assertThat(problem.solve2().intValue(), is(3));
    }
}