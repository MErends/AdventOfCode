package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day05Test {

    @Test
    void day05Test1() {
        Day05 problem = new Day05();
        problem.setInput(Util.readLine(2019, 5, 2));
        assertThat(problem.solve1(), is(5044655L));
        assertThat(problem.solve2(), is(7408802L));
    }
}