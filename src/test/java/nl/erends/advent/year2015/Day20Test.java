package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day20Test {

    @Test
    void day20Test1() {
        Day20 problem = new Day20();
        problem.setInput(Integer.parseInt(Util.readLine(2015, 20, 1)));
        assertThat(problem.solve1(), is(96));
        assertThat(problem.solve2(), is(84));
    }
}
