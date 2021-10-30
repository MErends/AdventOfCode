package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day10Test {
    
    @Test
    void day10Test1() {
        Day10 problem = new Day10();
        problem.setInput(Util.readInput(2016, 10, 1));
        problem.setLowCheck(2);
        problem.setHighCheck(5);
        assertThat(problem.solve1(), is(2));
        assertThat(problem.solve2(), is(30));
    }

}
