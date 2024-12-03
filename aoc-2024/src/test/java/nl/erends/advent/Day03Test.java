package nl.erends.advent;

import org.junit.jupiter.api.Test;

import java.util.Collections;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day03Test {

    @Test
    void day03Test1() {
        Day03 problem = new Day03();
        problem.setInput(Collections.singletonList("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"));
        assertThat(problem.solve1(), is (161));
    }

    @Test
    void day03Test2() {
        Day03 problem = new Day03();
        problem.setInput(Collections.singletonList("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"));
        assertThat(problem.solve2(), is (48));
    }
}
