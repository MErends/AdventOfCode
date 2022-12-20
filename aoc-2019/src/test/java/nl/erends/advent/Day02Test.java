package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day02Test {

    @Test
    void day02Test1() {
        Day02 problem = new Day02();
        problem.setInput(Util.readLine(2019, 2, 1));
        assertThat(problem.solve1().intValue(), is(6730673));
        assertThat(problem.solve2().intValue(), is(3749));
    }
}
