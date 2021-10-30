package nl.erends.advent.year2015;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day05Test {

    @Test
    void day05Test1() {
        Day05 problem = new Day05();
        problem.setInput(Util.readInput(2015, 5, 1));
        assertThat(problem.solve1(), is(2));
    }
    
    @Test
    void day05Test2() {
        Day05 problem = new Day05();
        problem.setInput(Util.readInput(2015, 5, 2));
        assertThat(problem.solve2(), is(2));
    }
}
