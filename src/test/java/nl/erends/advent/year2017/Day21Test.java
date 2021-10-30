package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day21Test {

    @Test
    void day21Test1() {
        Day21 problem = new Day21();
        problem.setInput(Util.readInput(2017, 21, 1));
        assertThat(problem.solve1(), is(142));
        assertThat(problem.solve2(), is(1879071));
    }
}