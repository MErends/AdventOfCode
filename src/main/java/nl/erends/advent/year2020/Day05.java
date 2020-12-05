package nl.erends.advent.year2020;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;
import java.util.stream.Collectors;

public class Day05 extends AbstractProblem<List<String>, Integer> {

    public static void main(String[] args) {
        new Day05().setAndSolve(Util.readInput(2020, 5));
    }
    
    @Override
    public Integer solve1() {
        List<Integer> seatIDs = input.stream().map(this::getSeatId).sorted().collect(Collectors.toList());
        int answer1 = seatIDs.get(seatIDs.size() - 1);
        int mySeat = seatIDs.remove(0) + 1;
        while (mySeat == seatIDs.remove(0)) {
            mySeat++;
        }
        answer2 = mySeat;
        return answer1;
    }
    
    private int getSeatId(String boardingPass) {
        String binaryPass = boardingPass.replaceAll("F", "0").replaceAll("B", "1").replaceAll("R", "1").replaceAll("L", "0");
        int row = Integer.parseInt(binaryPass.substring(0, 7), 2);
        int column = Integer.parseInt(binaryPass.substring(7), 2);
        return row * 8 + column;
    }
}
