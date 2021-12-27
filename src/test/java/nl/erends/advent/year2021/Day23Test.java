package nl.erends.advent.year2021;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day23Test {

    @Test
    void day23Test() {
        Day23 problem = new Day23();
        problem.setInput(Util.readInput(2021, 23, 1));
        assertThat(problem.solve1(), is(12521));
        assertThat(problem.solve2(), is(44169));
    }
}
