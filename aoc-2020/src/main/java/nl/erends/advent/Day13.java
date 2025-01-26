package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static nl.erends.advent.util.Util.lcm;

public class Day13 extends AbstractProblem<List<String>, Number> {

    public static void main(String[] args) {
        new Day13().setAndSolve(Util.readInput(2020, 13));
    }

    @Override
    public Integer solve1() {
        int time = Integer.parseInt(input.get(0));
        List<Integer> busIDs = Arrays.stream(input.get(1).split(","))
                .filter(s -> !s.equals("x"))
                .map(Integer::parseInt)
                .toList();
        int minWait = Integer.MAX_VALUE;
        int targetBus = 0;
        for (int busId : busIDs) {
            int wait = (time / busId) * busId + busId - time;
            if (wait < minWait) {
                minWait = wait;
                targetBus = busId;
            }
        }
        return minWait * targetBus;
    }
    
    @Override
    public Long solve2() {
        String[] buses = input.get(1).split(",");
        List<Bus> busList = new ArrayList<>();
        for (int offset = 0; offset < buses.length; offset++) {
            if (!buses[offset].equals("x")) {
                busList.add(new Bus(Integer.parseInt(buses[offset]), offset));
            }
        }
        busList.sort((b1, b2) -> b2.id - b1.id);


        Bus slowestBus = busList.removeFirst();
        long timeStep = slowestBus.id;
        long time = (long) slowestBus.id - slowestBus.offset;
        for (Bus currentBus : busList) {
            while (true) {
                if ((time + currentBus.offset) % currentBus.id == 0) {
                    timeStep = lcm(timeStep, currentBus.id);
                    break;
                }
                time += timeStep;
            }
        }
        return time;
    }

    private record Bus(int id, int offset) {
    }
}
