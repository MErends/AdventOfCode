package nl.erends.advent.year2022;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day07Test {

    @Test
    void day07Test() {
        Day07 problem = new Day07();
        problem.setInput(Util.readInput(2022, 7, 1));
        assertThat(problem.solve1(), is (95437));
        assertThat(problem.solve2(), is (24933642));
    }
}
