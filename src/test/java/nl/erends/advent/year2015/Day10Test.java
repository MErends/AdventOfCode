package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day10Test {

    @Test
    void day10Test1() {
        Day10 problem = new Day10();
        problem.setInput(Util.readLine(2015, 10, 1));
        problem.setTarget1(4);
        problem.setTarget2(6);
        assertThat(problem.solve1(), is(6));
        assertThat(problem.solve2(), is(8));
    }
}
