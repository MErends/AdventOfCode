package nl.erends.advent.year2016;

import nl.erends.advent.util.Util;

import java.util.List;

public class Day15 {

    private static int[] sizeOfDisc;
    private static int[] holePositionOfDisc;
//
//Disc #6 has 19 positions; at time=0, it is at position 17.
    public static void main(String[] args) {
        List<String> lines = Util.getFileAsList("year2016/2016day15_2.txt");
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
        System.out.println(delay);
    }

    private static void updateDiscs() {
        for (int discNo = 0; discNo < sizeOfDisc.length; discNo++) {
            int sizeOfDisc = Day15.sizeOfDisc[discNo];
            holePositionOfDisc[discNo] = (holePositionOfDisc[discNo] + 1) % sizeOfDisc;
        }
    }

    private static void initializeDiscPosition() {
        for (int discNo = 0; discNo < sizeOfDisc.length; discNo++) {
            int sizeofDisc = sizeOfDisc[discNo];
            holePositionOfDisc[discNo] = (holePositionOfDisc[discNo] + discNo + 1) % sizeofDisc;
        }
    }

    private static boolean freefall() {
        for (int holePosition : holePositionOfDisc) {
            if (holePosition != 0) {
                return false;
            }
        }
        return true;
    }
}
