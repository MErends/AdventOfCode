package nl.erends.advent;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


class Day25Test {

    @Test
    void day25Test1() {
        assertThat(new Day25().solve1(), is(3099));
    }
}
