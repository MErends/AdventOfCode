package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Day05 extends AbstractProblem<List<String>, Integer> {

    static void main() {
        new Day05().setAndSolve(Util.readInput(2020, 5));
    }
    
    @Override
    public Integer solve1() {
        List<Integer> seatIDs = input.stream().map(this::getSeatId).sorted()
                .collect(Collectors.toCollection(ArrayList::new));
        int answer1 = seatIDs.getLast();
        int mySeat = seatIDs.removeFirst() + 1;
        while (mySeat == seatIDs.removeFirst()) {
            mySeat++;
        }
        answer2 = mySeat;
        return answer1;
    }
    
    private int getSeatId(String boardingPass) {
        String binaryPass = boardingPass.replace("F", "0").replace("B", "1").replace("R", "1").replace("L", "0");
        int row = Integer.parseInt(binaryPass.substring(0, 7), 2);
        int column = Integer.parseInt(binaryPass.substring(7), 2);
        return row * 8 + column;
    }
}
