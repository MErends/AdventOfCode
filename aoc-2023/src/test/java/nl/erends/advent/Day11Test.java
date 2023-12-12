package nl.erends.advent;

import nl.erends.advent.util.Util;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day11Test {

    @ParameterizedTest
    @CsvSource({
            "2, 374",
            "10, 1030",
            "100, 8410"
    })
    void day11Test(int factor, long result) {
        Day11 problem = new Day11();
        problem.setInput(Util.readInput(2023, 11, 1));
        assertThat(problem.getDistanceAfterExpansionFactor(factor), is(result));
    }
}
