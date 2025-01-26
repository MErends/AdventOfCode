package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day23 extends AbstractProblem<String, Long> {

    private int lastSender;
    private long natX;
    private long natY;
    private long answer1;
    private List<Intcode> nicList;

    public static void main(String[] args) {
        new Day23().setAndSolve(Util.readLine(2019, 23));
    }

    @Override
    public Long solve1() {
        answer1 = 0;
        natX = 0;
        natY = 0;
        lastSender = 0;
        List<Long> natList = new ArrayList<>();
        nicList = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Intcode intcode = new Intcode(input);
            intcode.addInput(i);
            nicList.add(intcode);
        }
        int index = 0;
        while (answer2 == null) {
            Intcode computer = nicList.get(index);
            computer.execute();
            if (computer.hasOutput()) {
                processOutput(index, computer);
            } else {
                computer.addInput(-1);
                if (index == lastSender) {
                    nicList.getFirst().addInput(natX);
                    nicList.getFirst().addInput(natY);
                    natList.addFirst(natY);
                    if (natList.size() > 1 && natList.get(0).equals(natList.get(1))) {
                        answer2 = natList.getFirst();
                    }
                }
            }
            index = ++index % nicList.size();
        }
        return answer1;
    }

    private void processOutput(int index, Intcode computer) {
        lastSender = index;
        int target = (int) computer.getOutput().longValue();
        computer.execute();
        long x = computer.getOutput();
        computer.execute();
        long y = computer.getOutput();
        if (target == 255) {
            if (answer1 == 0) {
                answer1 = y;
            }
            natX = x;
            natY = y;
        } else {
            nicList.get(target).addInput(x);
            nicList.get(target).addInput(y);
        }
    }
}
