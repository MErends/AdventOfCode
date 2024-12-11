package nl.erends.advent;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day11Test {

    @Test
    void day11Test1() {
        Day11 problem = new Day11();
        problem.setInput("125 17");
        assertThat(problem.solve1(), is (55312L));
        assertThat(problem.solve2(), is (65601038650482L));
    }
}
