package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day22Test {
    
    @Test
    void day22Test1() {
        Day22 problem = new Day22();
        problem.setInput(Util.readInput(2016, 22, 1));
        assertThat(problem.solve1(), is(1020));
        problem = new Day22();
        problem.setInput(Util.readInput(2016, 22, 1));
        assertThat(problem.solve2(), is(198));
    }
}
