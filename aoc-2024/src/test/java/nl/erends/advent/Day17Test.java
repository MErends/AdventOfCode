package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day17Test {

    @Test
    void day17Test1() {
        Day17 problem = new Day17();
        problem.setInput(Util.readInput(2024, 17, 1));
        assertThat(problem.solve1(), is ("4,6,3,5,6,3,5,2,1,0"));
    }
}
