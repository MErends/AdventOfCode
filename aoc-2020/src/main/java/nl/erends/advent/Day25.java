package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;

import java.util.Arrays;
import java.util.List;

public class Day25 extends AbstractProblem<List<Integer>, Number> {

    public static void main(String[] args) {
        new Day25().setAndSolve(Arrays.asList(18499292 ,8790390));
    }

    @Override
    public Long solve1() {
        int cardLoop = 0;
        int subject = 7;
        long value = 1;
        int cardPubKey = input.getFirst();
        while (value != cardPubKey) {
            value *= subject;
            value = value % 20201227;
            cardLoop++;
        }
        value = 1;
        subject = input.get(1);
        for (int loop = 0; loop < cardLoop; loop++) {
            value *= subject;
            value = value % 20201227;
        }
        return value;
    }
}
