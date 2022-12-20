package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day12Test {
    
    @ParameterizedTest
    @CsvSource({
            "1, 10, 36",
            "2, 19, 103",
            "3, 226, 3509"
    })
    void day12Test(int testcase, int result1, int result2) {
        Day12 problem = new Day12();
        problem.setInput(Util.readInput(2021, 12, testcase));
        assertThat(problem.solve1(), is(result1));
        assertThat(problem.solve2(), is(result2));
    }
}
