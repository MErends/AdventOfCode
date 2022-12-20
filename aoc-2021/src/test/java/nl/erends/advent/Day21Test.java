package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day21Test {

    @Test
    void day21Test() {
        Day21 problem = new Day21();
        problem.setInput(Util.readInput(2021, 21, 1));
        assertThat(problem.solve1(), is(739785));
        assertThat(problem.solve2(), is(444_356_092_776_315L));
    }
}
