package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day20 extends AbstractProblem<List<String>, Number> {
    
    private long maxValue = 4294967295L;

    public static void main(String[] args) {
        new Day20().setAndSolve(Util.readInput(2016, 20));
    }

    @Override
    public Long solve1() {
        return nextFree(0);
    }

    @Override
    public Integer solve2() {
    int numFree = 0;
        long pointer = 0;
        while (true) {
            long freeOne = nextFree(pointer);
            if (freeOne > maxValue) break;
            long takenOne = nextBlocked(freeOne);
            numFree += (takenOne - freeOne);
            pointer = takenOne;
        }
        return numFree;
    }
    
    private long nextFree(long blocked) {
        for (String blackRange : input) {
            long lower = Long.parseLong(blackRange.split("-")[0]);
            long upper = Long.parseLong(blackRange.split("-")[1]);
            if (lower <= blocked && upper >= blocked) {
                return nextFree(upper + 1);
            }
        }
        return blocked;
    }
    
    private long nextBlocked(long free) {
        long nextBlocked = maxValue + 1;
        for (String blackRange : input) {
            long lower = Long.parseLong(blackRange.split("-")[0]);
            if (lower > free) {
                nextBlocked = Math.min(nextBlocked, lower);
            }
        }
        return nextBlocked;
    }

    public void setMaxValue(long maxValue) {
        this.maxValue = maxValue;
    }
}
