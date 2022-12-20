package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day16Test {
    
    @Test
    void day16Test1() {
        Day16 problem = new Day16();
        problem.setInput(Util.readInput(2020, 16, 1));
        assertThat(problem.solve1(), is(71));
    }
    
    @Test
    void day16Test2() {
        Day16 problem = new Day16();
        problem.setInput(Util.readInput(2020, 16, 2));
        assertThat(problem.solve2(), is(12 * 13L));

    }
}
