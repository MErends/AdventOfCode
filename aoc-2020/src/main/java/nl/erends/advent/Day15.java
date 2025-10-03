package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Day15 extends AbstractProblem<String, Integer> {

    private int targetTurn = 2020;

    void main() {
        new Day15().setAndSolve("6,19,0,5,7,13,1");
    }

    @Override
    public Integer solve1() {
        String[] numberArr = input.split(",");
        Map<Integer, Integer> lastSaidMap = IntStream.rangeClosed(1, numberArr.length - 1)
                .boxed()
                .collect(Collectors.toMap(n -> Integer.parseInt(numberArr[n - 1]), n -> n));

        int turn = numberArr.length + 1;
        int lastSaid = Integer.parseInt(numberArr[turn - 2]);
        while (turn <= targetTurn) {
            if (lastSaidMap.containsKey(lastSaid)) {
                int tempValue = turn - 1 - lastSaidMap.get(lastSaid);
                lastSaidMap.put(lastSaid, turn - 1);
                lastSaid = tempValue;
            } else {
                lastSaidMap.put(lastSaid, turn - 1);
                lastSaid = 0;
            }
            turn++;
        }
        return lastSaid;
    }

    @Override
    public Integer solve2() {
        targetTurn = 30_000_000;
        return solve1();
    }
}
