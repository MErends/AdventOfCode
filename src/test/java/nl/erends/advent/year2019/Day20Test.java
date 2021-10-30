package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day20Test {

    @Test
    void day20Test1() {
        Day20 problem = new Day20();
        problem.setInput(Util.readInput(2019, 20 , 1));
        assertThat(problem.solve1(), is(23));
    }

    @Test
    void day20Test2() {
        Day20 problem = new Day20();
        problem.setInput(Util.readInput(2019, 20 , 2));
        assertThat(problem.solve2(), is(396));
    }

}