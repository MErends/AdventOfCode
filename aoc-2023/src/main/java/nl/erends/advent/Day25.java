package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;
import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.SingleGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <h1>--- Day 22: Sand Slabs ---</h1>
 * <p>Enough sand has fallen; it can finally filter water for Snow Island.The
 * sand has been falling as large compacted bricks of sand, piling up to form an
 * impressive stack here near the edge of Island Island. In order to make use of
 * the sand to filter water, some of the bricks will need to be disintegrated
 * back into freely flowing sand. Figure how the blocks will settle based on the
 * snapshot. Once they've settled, consider disintegrating a single brick; how
 * many bricks could be safely chosen as the one to get disintegrated?</p>
 * <p><a href="https://adventofcode.com/2023/day/22">2023 Day 22</a></p>
 */
public class Day25 extends AbstractProblem<List<String>, Number> {

    Map<String, Node> nodeMap = new HashMap<>();

    public static void main(String[] args) {
        new Day25().setAndSolve(Util.readInput(2023, 25));
    }

    @Override
    protected Number solve1() {

        System.setProperty("org.graphstream.ui", "swing");

        Graph graph = new SingleGraph("tutorial 1");

        graph.setStrict(false);
        graph.setAutoCreate(true);
        graph.setAttribute("ui.stylesheet", "node { size: 1px; }");

        for (String line : input) {
            String[] ls = line.split(": ");
            String a = ls[0];
            for (String otherName : ls[1].split(" ")) {
                String b = otherName;
                graph.addEdge(a + b, a, b);
            }
        }


        for (org.graphstream.graph. Node node : graph) {

            node.setAttribute("ui.label", node.getId());
        }

        graph.display();

        for (String line : input) {
            String[] ls = line.split(": ");
            Node a = nodeMap.computeIfAbsent(ls[0], Node::new);
            for (String otherName : ls[1].split(" ")) {
                Node b = nodeMap.computeIfAbsent(otherName, Node::new);
                a.connections.add(b);
                b.connections.add(a);
            }
        }

        Node a = nodeMap.get("bcf");
        Node b = nodeMap.get("fxk");
        a.connections.remove(b);
        b.connections.remove(a);
        a = nodeMap.get("xhl");
        b = nodeMap.get("shj");
        a.connections.remove(b);
        b.connections.remove(a);
        a = nodeMap.get("cgt");
        b = nodeMap.get("zgp");
        a.connections.remove(b);
        b.connections.remove(a);

        Set<Node> groupA = new HashSet<>();
        a = nodeMap.values().stream().findFirst().orElseThrow();
        Set<Node> toExplore = new HashSet<>();
        toExplore.add(a);
        while (!toExplore.isEmpty()) {
            Set<Node> nextExplore = new HashSet<>();
            for (Node explore : toExplore) {
                groupA.add(explore);
                for (Node neighbor : explore.connections) {
                    if (!groupA.contains(neighbor)) {
                        nextExplore.add(neighbor);
                    }
                }
            }
            toExplore = nextExplore;
        }
        return groupA.size() * (nodeMap.size() - groupA.size());
    }

    private class Node {

        String name;
        Set<Node> connections = new HashSet<>();

        Node(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    '}';
        }
    }
}


