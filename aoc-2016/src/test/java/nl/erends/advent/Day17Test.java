package nl.erends.advent;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day17Test {

    @ParameterizedTest
    @CsvSource({
            "ihgpwlah, DDRRRD, 370",
            "kglvqrro, DDUDRLRRUDRD, 492",
            "ulqzkmiv, DRURDRUDDLLDLUURRDULRLDUUDDDRR, 830"
    })
    void day17Test(String input, String result1, String result2) {
        Day17 problem = new Day17();
        problem.setInput(input);
        assertThat(problem.solve1(), is(result1));
        assertThat(problem.solve2(), is(result2));
    }
}
