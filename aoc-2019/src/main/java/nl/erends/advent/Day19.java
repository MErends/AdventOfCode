package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

public class Day19 extends AbstractProblem<String, Integer> {

    static void main() {
        new Day19().setAndSolve(Util.readLine(2019, 19));
    }

    @Override
    public Integer solve1() {
        int countPulled = 0;
        for (int y = 0; y < 50; y++) {
            for (int x = 0; x < 50; x++) {
                if (isPulled(x, y)) {
                    countPulled++;
                }
            }
        }
        return countPulled;
    }


    @Override
    public Integer solve2() {
        int y = 0;
        int x = 0;
        while (true) {
            if (isPulled(x + 99, y)) {
                if (isPulled(x, y + 99)) {
                    return x * 10000 + y;
                } else {
                    x++;
                }
            } else {
                y++;
            }
        }
    }

    private boolean isPulled(int x, int y) {
        Intcode droid = new Intcode(input);
        droid.addInput(x);
        droid.addInput(y);
        droid.execute();
        return droid.getOutput() == 1;
    }
}
