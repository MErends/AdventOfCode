package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day12Test {

    @ParameterizedTest
    @CsvSource({"1, 140, 80",
                "2, 772, 436",
                "3, 1930, 1206",
                "4, 692, 236",
                "5, 1184, 368"})
    void day12Test1(int testcase, int expected1, int expected2) {
        Day12 problem = new Day12();
        problem.setInput(Util.readInput(2024, 12, testcase));
        assertThat(problem.solve1(), is (expected1));
        assertThat(problem.solve2(), is (expected2));
    }
}
