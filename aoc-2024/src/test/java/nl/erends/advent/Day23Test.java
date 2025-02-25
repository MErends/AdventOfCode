package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day23Test {

    @Test
    void day23Test1() {
        Day23 problem = new Day23();
        problem.setInput(Util.readInput(2024, 23, 1));
        assertThat(problem.solve1(), is ("7"));
        assertThat(problem.solve2(), is ("co,de,ka,ta"));
    }
}
