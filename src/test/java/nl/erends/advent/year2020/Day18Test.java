package nl.erends.advent.year2020;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day18Test {

    @Test
    void day18Test() {
        Day18 problem = new Day18();
        problem.setInput(Util.readInput(2020, 18, 1));
        assertThat(problem.solve1(), is(71 + 51 + 26 + 437 + 12240 + 13632L));
        assertThat(problem.solve2(), is(231 + 51 + 46 + 1445 + 669060 + 23340L));
    }
}
