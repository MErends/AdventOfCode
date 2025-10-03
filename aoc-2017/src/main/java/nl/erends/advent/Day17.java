package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;

import java.util.ArrayList;
import java.util.List;

public class Day17 extends AbstractProblem<Integer, Integer> {

    void main() {
        new Day17().setAndSolve(343);
    }
    
    @Override
    public Integer solve1() {
        List<Integer> memory = new ArrayList<>();
        memory.add(0);
        int answer1 = 0;
        int position = 0;
        int insert = 1;
        for ( ; insert < 2018; insert++) {
            position = (position + input) % memory.size();
            memory.add(position + 1, insert);
            position++;
        }
        for (int index = 0; index < memory.size(); index++) {
            if (memory.get(index) == 2017) {
                answer1 = memory.get(index + 1);
                break;
            }
        }
        int valueAfterZero = memory.get(1);
        for ( ; insert < 50_000_001; insert++) {
            position = (position + input) % insert;
            if (position == 0) {
                valueAfterZero = insert;
            }
            position++;
        }
        answer2 = valueAfterZero;
        return answer1;
    }
}
