package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day10Test {

    @Test
    void day10Test1() {
        Day10 problem = new Day10();
        problem.setInput(Util.readInput(2024, 10, 1));
        assertThat(problem.solve1(), is (36));
        assertThat(problem.solve2(), is (81));
    }
}
