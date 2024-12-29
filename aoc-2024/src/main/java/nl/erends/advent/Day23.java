package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <h1>--- Day 23: LAN Party ---</h1>
 * <p>The network map provides a list of every connection between two computers.
 * LAN parties typically involve multiplayer games, so maybe you can locate it
 * by finding groups of connected computers. Start by looking for sets of three
 * computers where each computer in the set is connected to the other two
 * computers. How many contain at least one computer with a name that starts
 * with t?</p>
 * <p><a href="https://adventofcode.com/2024/day/23">2024 Day 23</a></p>
 */
public class Day23 extends AbstractProblem<List<String>, String> {

    private String biggestGroup;
    private int biggestSize;
    private Map<String, Node> nodeMap;

    public static void main(String[] args) {
        new Day23().setAndSolve(Util.readInput(2024, 23));
    }

    @Override
    protected String solve1() {
        int result = 0;
        List<Connection> connections = input.stream().map(Connection::new).toList();
        for (int ia = 0; ia < connections.size(); ia++) {
            Connection a = connections.get(ia);
            List<Connection> connectors = connections.stream()
                    .skip(ia + 1L)
                    .filter(c -> c.sharesNode(a))
                    .toList();
            for (int ib = 0; ib < connectors.size(); ib++) {
                Connection b = connectors.get(ib);
                String sharedNode = a.getSharedNode(b);
                String cNode1 = a.getOtherNode(sharedNode);
                String cNode2 = b.getOtherNode(sharedNode);
                Optional<Connection> oc = connectors.stream()
                        .skip(ib + 1L)
                        .filter(c -> c.hasNode(cNode1) && c.hasNode(cNode2))
                        .findFirst();
                if (oc.isPresent() && (cNode1.startsWith("t") || cNode2.startsWith("t") || sharedNode.startsWith("t"))) {
                        result++;
                    }

            }
        }
        return Integer.toString(result);
    }

    @Override
    public String solve2() {
        nodeMap = new HashMap<>();
        for (String line : input) {
            String[] sA = line.split("-");
            String sNode1 = sA[0];
            String sNode2 = sA[1];
            Node node1 = nodeMap.computeIfAbsent(sNode1, Node::new);
            Node node2 = nodeMap.computeIfAbsent(sNode2, Node::new);
            node1.connectors.add(node2);
            node2.connectors.add(node1);
        }
        biggestGroup = "";
        biggestSize = Integer.MIN_VALUE;
        for (String line : input) {
            String[] sA = line.split("-");
            Set<Node> nodeSet = new HashSet<>();
            nodeSet.add(nodeMap.get(sA[0]));
            nodeSet.add(nodeMap.get(sA[1]));
            getNextNode(nodeSet);
        }
        return biggestGroup;
    }

    void getNextNode(Set<Node> currentSet) {
        List<Node> nextNodes = nodeMap.values().stream()
                .filter(n -> !currentSet.contains(n) && n.connectors.containsAll(currentSet))
                .toList();
        if (!nextNodes.isEmpty()) {
            Set<Node> nextSet = new HashSet<>(currentSet);
            nextSet.add(nextNodes.get(0));
            getNextNode(nextSet);
        }
        if (nextNodes.isEmpty() && currentSet.size() > biggestSize) {
            biggestSize = currentSet.size();
            biggestGroup = currentSet.stream()
                    .map(n -> n.name)
                    .sorted()
                    .collect(Collectors.joining(","));
        }
    }

    static class Connection {
        String node1;
        String node2;

        public Connection(String line) {
            String[] sA = line.split("-");
            this.node1 = sA[0];
            this.node2 = sA[1];
        }

        boolean hasNode(String node) {
            return node1.equals(node) || node2.equals(node);
        }

        boolean sharesNode(Connection other) {
            return hasNode(other.node1) || hasNode(other.node2);
        }

        String getSharedNode(Connection other) {
            if (other.hasNode(node1)) {
                return node1;
            }
            return node2;
        }

        String getOtherNode(String node) {
            if (node1.equals(node)) {
                return node2;
            }
            return node1;
        }
    }

    static class Node {
        String name;
        Set<Node> connectors = new HashSet<>();

        Node(String name) {
            this.name = name;
            connectors.add(this);
        }
    }
}
