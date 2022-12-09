package nl.erends.advent.year2017;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day24 extends AbstractProblem<List<String>, Integer> {

    private int maxStrength = Integer.MIN_VALUE;
    private int longestBridge = Integer.MIN_VALUE;
    private int strengthOfLongest;

    public static void main(String[] args) {
        new Day24().setAndSolve(Util.readInput(2017, 24));
    }

    @Override
    public Integer solve1() {
        List<Connector> connectors = input.stream()
                .map(line -> new Connector(Integer.parseInt(line.split("/")[0]), Integer.parseInt(line.split("/")[1])))
                .toList();
        buildBridge(new ArrayList<>(), connectors, 0);
        answer2 = strengthOfLongest;
        return maxStrength;
    }


    private void buildBridge(List<Connector> currentBridge, List<Connector> freeConnectors, int endGate) {
        boolean done = true;
        for (Connector connector : freeConnectors) {
            if (connector.port1 == endGate) {
                done = false;
                List<Connector> newBridge = new ArrayList<>(currentBridge);
                List<Connector> newFreeConnectors = new ArrayList<>(freeConnectors);
                newBridge.add(connector);
                newFreeConnectors.remove(connector);
                buildBridge(newBridge, newFreeConnectors, connector.port2);
            } else if (connector.port2 == endGate) {
                done = false;
                List<Connector> newBridge = new ArrayList<>(currentBridge);
                List<Connector> newFreeConnectors = new ArrayList<>(freeConnectors);
                newBridge.add(connector);
                newFreeConnectors.remove(connector);
                buildBridge(newBridge, newFreeConnectors, connector.port1);
            }
        }
        if (done) {
            int strength = currentBridge.stream().mapToInt(c -> c.strength).sum();
            maxStrength = Math.max(strength, maxStrength);
            if (currentBridge.size() > longestBridge || (currentBridge.size() == longestBridge && strength > strengthOfLongest)) {
                longestBridge = currentBridge.size();
                strengthOfLongest = strength;
            }
        }
    }

    private static class Connector {
        private final int port1;
        private final int port2;
        private final int strength;

        Connector(int port1, int port2) {
            this.port1 = port1;
            this.port2 = port2;
            strength = port1 + port2;
        }
    }
}
