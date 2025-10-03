package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day15 extends AbstractProblem<List<String>, Integer> {

    private int[] sizeOfDisc;
    private int[] holePositionOfDisc;
    private boolean part1 = true;

    void main() {
        new Day15().setAndSolve(Util.readInput(2016, 15));
    }

    @Override
    public Integer solve1() {
        List<String> lines = new ArrayList<>(input);
        if (part1) {
            lines.removeLast();
        }
        sizeOfDisc = new int[lines.size()];
        holePositionOfDisc = new int[lines.size()];
        int discNo = 0;
        for (String line : lines) {
            String[] words = line.split(" ");
            sizeOfDisc[discNo] = Integer.parseInt(words[3]);
            holePositionOfDisc[discNo] = Integer.parseInt(words[11].substring(0, words[11].length() - 1));
            discNo++;
        }
        initializeDiscPosition();
        int delay = 0;
        while (!freefall()) {
            delay++;
            updateDiscs();
        }
        return delay;
    }
    
    @Override
    public Integer solve2() {
        part1 = false;
        return solve1();
    }

    private void updateDiscs() {
        for (int discNo = 0; discNo < sizeOfDisc.length; discNo++) {
            int sizeofDisc = sizeOfDisc[discNo];
            holePositionOfDisc[discNo] = (holePositionOfDisc[discNo] + 1) % sizeofDisc;
        }
    }

    private void initializeDiscPosition() {
        for (int discNo = 0; discNo < sizeOfDisc.length; discNo++) {
            int sizeofDisc = sizeOfDisc[discNo];
            holePositionOfDisc[discNo] = (holePositionOfDisc[discNo] + discNo + 1) % sizeofDisc;
        }
    }

    private boolean freefall() {
        for (int holePosition : holePositionOfDisc) {
            if (holePosition != 0) {
                return false;
            }
        }
        return true;
    }
}
