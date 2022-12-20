package nl.erends.advent;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day06Test {

    @ParameterizedTest
    @CsvSource({
            "mjqjpqmgbljsphdztnvjfqwrcgsmlb,  7, 19",
            "bvwbjplbgvbhsrlpgdmjqwftvncz, 5, 23",
            "nppdvjthqldpwncqszvftbrmjlhg, 6, 23",
            "nznrnfrfntjfmvfwmzdfjlvtqnbhcprsg, 10, 29",
            "zcfzfwzzqfrljwzlrfnpqdbhtmscgvjw, 11, 26"})
    void day06Test(String input, int answer1, int answer2) {
        Day06 problem = new Day06();
        problem.setInput(input);
        assertThat(problem.solve1(), is (answer1));
        assertThat(problem.solve2(), is (answer2));
    }
}
