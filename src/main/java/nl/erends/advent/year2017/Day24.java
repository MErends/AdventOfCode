package nl.erends.advent.year2017;

import nl.erends.advent.util.FileIO;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


public class Day24 {
    
    private static int maxStrength = Integer.MIN_VALUE;
    private static int longestBridge = Integer.MIN_VALUE;
    private static int strengthOfLongest;

    public static void main(String[] args) {
        List<String> input = FileIO.getFileAsList("2017day24.txt");
        List<Connector> connectors = input.stream()
                .map(line -> new Connector(Integer.parseInt(line.split("/")[0]), Integer.parseInt(line.split("/")[1])))
                .collect(Collectors.toList());
        buildBridge(new ArrayList<>(), connectors, 0);
        System.out.println(maxStrength);
        System.out.println(strengthOfLongest);
    }
    
    
    private static void buildBridge(List<Connector> currentBridge, List<Connector> freeConnectors, int endGate) {
        boolean done = true;
        for (Connector connector : freeConnectors) {
            if (connector.getPort1() == endGate) {
                done = false;
                List<Connector> newBridge = new ArrayList<>(currentBridge);
                List<Connector> newFreeConnectors = new ArrayList<>(freeConnectors);
                newBridge.add(connector);
                newFreeConnectors.remove(connector);
                buildBridge(newBridge, newFreeConnectors, connector.getPort2());
            } else if (connector.getPort2() == endGate) {
                done = false;
                List<Connector> newBridge = new ArrayList<>(currentBridge);
                List<Connector> newFreeConnectors = new ArrayList<>(freeConnectors);
                newBridge.add(connector);
                newFreeConnectors.remove(connector);
                buildBridge(newBridge, newFreeConnectors, connector.getPort1());
            }
        }
        if (done) {
//            StringBuilder stringBuilder = new StringBuilder(currentBridge.get(0).toString());
//            for (int index = 1; index < currentBridge.size(); index++) {
//                stringBuilder.append("--" + currentBridge.get(index).toString());
//            }
//            System.out.println(stringBuilder);
            int strength = currentBridge.stream().mapToInt(Connector::getStrength).sum();
            maxStrength = Math.max(strength, maxStrength);
            if (currentBridge.size() > longestBridge || (currentBridge.size() == longestBridge && strength > strengthOfLongest)) {
                longestBridge = currentBridge.size();
                strengthOfLongest = strength;
            }
        }
    }
}

class Connector {
    private int port1;
    private int port2;
    private int strength;

    Connector(int port1, int port2) {
        this.port1 = port1;
        this.port2 = port2;
        strength = port1 + port2;
    }

    public int getPort1() {
        return port1;
    }

    public int getPort2() {
        return port2;
    }

    public int getStrength() {
        return strength;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Connector connector = (Connector) o;
        return port1 == connector.port1 &&
                port2 == connector.port2;
    }

    @Override
    public int hashCode() {
        return Objects.hash(port1, port2);
    }
    
    @Override
    public String toString() {
        return port1 + "/" + port2;
    }
}