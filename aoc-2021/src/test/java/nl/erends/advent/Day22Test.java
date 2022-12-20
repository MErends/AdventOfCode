package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day22Test {

    @Test
    void day22Test() {
        Day22 problem = new Day22();
        problem.setInput(Util.readInput(2021, 22, 1));
        assertThat(problem.solve1(), is(474140L));
        assertThat(problem.solve2(), is(2758514936282235L));
    }
}


