package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day04Test {

    @Test
    void day04Test1() {
        Day04 problem = new Day04();
        problem.setInput(Util.readLine(2015, 4, 1));
        assertThat(problem.solve1(), is(609043));
    }

    @Test
    void day04Test2() {
        Day04 problem = new Day04();
        problem.setInput(Util.readLine(2015, 4, 2));
        assertThat(problem.solve1(), is(1048970));
    }
}