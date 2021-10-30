package nl.erends.advent.year2019;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day21Test {

    @Test
    void day20Test1() {
        Day21 problem = new Day21();
        problem.setInput(Util.readLine(2019, 21));
        assertThat(problem.solve1(), is(19354173L));
    }

    @Test
    void day20Test2() {
        Day21 problem = new Day21();
        problem.setInput(Util.readLine(2019, 21));
        assertThat(problem.solve2(), is(1145849660L));
    }
}
