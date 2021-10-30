package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day13Test {

    @Test
    void day13Test1() {
        Day13 problem = new Day13();
        problem.setInput(Util.readLine(2019, 13, 1));
        assertThat(problem.solve1(), is(376));
        assertThat(problem.solve2(), is(18509));
    }
}
