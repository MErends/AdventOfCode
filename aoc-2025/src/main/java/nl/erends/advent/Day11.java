package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Timer;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day11 extends AbstractProblem<List<String>, Integer> {
    
    static Map<String, Node> nodeMap = new HashMap<>();

    void main() {
        new Day11().setAndSolve(Util.readInput(2025, 11));
    }

    @Override
    protected Integer solve1() {
        nodeMap.put("out", new Node("out:  "));
        nodeMap.get("out").possibleNodes = new HashSet<>();
        for (String line : input) {
            Node node = new Node(line);
            nodeMap.put(node.name, node);
        }
        
        Set<String> nodesRemaining = new HashSet<>(nodeMap.keySet());
        Node svr = nodeMap.get("svr");
        svr.getPossibleNodes();
        nodesRemaining.remove("svr");
        answer2 = svr.countTightPathsToOut(new HashSet<>());
        return nodeMap.get("you").countPathsToOut(new ArrayList<>());
    }
    
    static Map<Set<String>, Integer> cacheMap = new HashMap<>();
    
    private static class Node {
        
        String name;
        Set<String> outputs;
        Set<String> possibleNodes;
        
        private Node(String line) {
            String[] split = line.split(": ");
            name = split[0];
            outputs = new HashSet<>(Arrays.asList(split[1].split(" ")));
        }
        
        private int countPathsToOut(List<String> currentPath) {
            if (name.equals("out")) return 1;
            int paths = 0;
            for (String output : outputs) {
                if (currentPath.contains(output)) {
                    LOG.info("LOOPPPP");
                    continue;
                }
                List<String> nextPath = new ArrayList<>(currentPath);
                nextPath.add(output);
                paths += nodeMap.get(output).countPathsToOut(nextPath);
            }
            return paths;
        }

        private int countTightPathsToOut(Set<String> nodesSeen) {
            if (cacheMap.containsKey(nodesSeen)) {
                LOG.info("cache hit");
                return cacheMap.get(nodesSeen);
            }
            if (name.equals("out") && nodesSeen.contains("dac") && nodesSeen.contains("fft")){
                cacheMap.put(nodesSeen, 1);
                return 1;
            }
            Set<String> potential = new HashSet<>(nodesSeen);
            potential.addAll(possibleNodes);
            if (!potential.contains("dac") || !potential.contains("fft") || !potential.contains("out")) {
                cacheMap.put(nodesSeen, 0);
                return 0;
            }
            int paths = 0;
            for (String output : outputs) {
                Timer.tick(cacheMap.size());
                Set<String> nextNodesSeen = new HashSet<>(nodesSeen);
                nextNodesSeen.add(output);
                paths += nodeMap.get(output).countTightPathsToOut(nextNodesSeen);
            }
            cacheMap.put(nodesSeen, paths);
            return paths;
        }
        
        private Set<String> getPossibleNodes() {
            if (possibleNodes == null) {
                possibleNodes = new HashSet<>();
                possibleNodes.addAll(outputs);
                for (String node : outputs) {
                    possibleNodes.addAll(nodeMap.get(node).getPossibleNodes());
                }
            }
            return possibleNodes;
        }
    }
}
