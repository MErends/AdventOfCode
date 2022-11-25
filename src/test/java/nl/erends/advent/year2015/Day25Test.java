package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day25Test {

    @Test
    void day25Test() {
        Day25 problem = new Day25();
        problem.setInput(Util.readLine(2015, 25));
        assertThat(problem.solve1(), is(19980801L));
    }
}
