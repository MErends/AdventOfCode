package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day13 extends AbstractProblem<List<String>, Integer> {

    private int[] depthAtLevel;
    private int[] scannerposition;
    private boolean[] scannerMovingDown;

    static void main() {
        new Day13().setAndSolve(Util.readInput(2017, 13));
    }
    
    @Override
    public Integer solve1() {
        depthAtLevel = new int[Integer.parseInt(input.getLast().split(":")[0].trim()) + 1];
        scannerposition = new int[Integer.parseInt(input.getLast().split(":")[0].trim()) + 1];
        scannerMovingDown = new boolean[Integer.parseInt(input.getLast().split(":")[0].trim()) + 1];
        for (String line : input) {
            String[] words = line.split(":");
            depthAtLevel[Integer.parseInt(words[0].trim())] = Integer.parseInt(words[1].trim());
            scannerposition[Integer.parseInt(words[0].trim())] = Integer.parseInt(words[1].trim()) != 0 ? 1 : -1;
            scannerMovingDown[Integer.parseInt(words[0].trim())] = true;
        }
        initializeScannerPositions();
        int answer1 = getPenalty();
        int delay = 0;
        while (hasPenalty()) {
            delay++;
            updateScanners();
        }
        answer2 = delay;
        return answer1;
    }

    private int getPenalty() {
        int penalty = 0;
        for (int level = 0; level < depthAtLevel.length; level++) {
            int depth = depthAtLevel[level];
            if (scannerposition[level] == 1) {
                penalty += level * depth;
            }
        }
        return penalty;
    }

    private boolean hasPenalty() {
        for (int level = 0; level < depthAtLevel.length; level++) {
            if (scannerposition[level] == 1) {
                return true;
            }
        }
        return false;
    }

    private void updateScanners() {
        for (int level = 0; level < depthAtLevel.length; level++) {
            int depth = depthAtLevel[level];
            if (depth == 0)
                continue;
            int position = scannerposition[level];
            if (scannerMovingDown[level]) {
                position++;
                if (position == depth)
                    scannerMovingDown[level] = false;
            } else {
                position--;
                if (position == 1)
                    scannerMovingDown[level] = true;
            }
            scannerposition[level] = position;
        }
    }

    private void initializeScannerPositions() {
        for (int level = 0; level < depthAtLevel.length; level++) {
            int depth = depthAtLevel[level];
            if (depth == 0) {
                continue;
            }
            int position = getPosition(level, depth);
            scannerposition[level] = position;
        }
    }

    private int getPosition(int level, int depth) {
        int position = 1;
        for (int i = 0; i < level; i++) {
            if (scannerMovingDown[level]) {
                position++;
                if (position == depth) scannerMovingDown[level] = false;
            } else {
                position--;
                if (position == 1) scannerMovingDown[level] = true;
            }
        }
        return position;
    }
}
