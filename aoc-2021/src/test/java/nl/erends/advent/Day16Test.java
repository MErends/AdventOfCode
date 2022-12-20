package nl.erends.advent;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class Day16Test {

    @ParameterizedTest
    @CsvSource({
            "8A004A801A8002F478, 16",
            "620080001611562C8802118E34, 12",
            "C0015000016115A2E0802F182340, 23",
            "A0016C880162017C3686B18A3D4780, 31"
    })
    void day16Test1(String input, int result) {
        Day16 problem = new Day16();
        problem.setInput(input);
        assertThat(problem.solve1(), is(result));
    }

    @ParameterizedTest
    @CsvSource({
            "C200B40A82, 3",
            "04005AC33890, 54",
            "880086C3E88112, 7",
            "CE00C43D881120, 9",
            "D8005AC2A8F0, 1",
            "F600BC2D8F, 0",
            "9C005AC2F8F0, 0",
            "9C0141080250320F1802104A08, 1"
    })
    void day16Test2(String input, long result) {
        Day16 problem = new Day16();
        problem.setInput(input);
        assertThat(problem.solve2(), is(result));
    }
}
