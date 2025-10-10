package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day07 extends AbstractProblem<List<String>, String> {

    private final Map<String, Node> nodes = new HashMap<>();
    
	void main() {
        new Day07().setAndSolve(Util.readInput(2017, 7));
    }
    
    @Override
    public String solve1() {
        readNodes();
        List<String> nodeList = new ArrayList<>(nodes.keySet());
        for(Node node : nodes.values()) {
            nodeList.removeAll(node.supports);
        }
        return nodeList.getFirst();
	}
	
	@Override
    public String solve2() {
	    Node root = nodes.get(solve1());
	    return Integer.toString(root.getCorrectedWeight());
    }

    private void readNodes() {
        for (String line : input) {
            String[] words = line.split("->");
            String[] nodeInfo = words[0].split(" ");
            String name = nodeInfo[0];
            int weight = Integer.parseInt(nodeInfo[1].substring(1, nodeInfo[1].length() - 1));
            List<String> supports = new ArrayList<>();
            if (words.length > 1) {
                String[] supportedNodes = words[1].split(",");
                for (String supportedNode : supportedNodes) {
                    supports.add(supportedNode.trim());
                }
            }
        nodes.put(name, new Node(name, weight, supports));
        }
    }

    private class Node {
        final String name;
        final int weight;
        final List<String> supports;

        Node(String name, int weight, List<String> supports) {
            this.name = name;
            this.weight = weight;
            this.supports = supports;
        }
        
        int getEffectiveWeight() {
            int effectiveWeight = weight;
            for (String support : supports) {
                effectiveWeight += nodes.get(support).getEffectiveWeight();
            }
            return effectiveWeight;
        }
        
        boolean isBalanced() {
            Set<Integer> supportWeight = new HashSet<>();
            for (String support : supports) {
                supportWeight.add(nodes.get(support).getEffectiveWeight());
            }
            return supportWeight.size() <= 1;
        }
        
        int getCorrectedWeight() {
            for (String support : supports) {
                if (!nodes.get(support).isBalanced()) {
                    return nodes.get(support).getCorrectedWeight();
                }
            }
            Map<Integer, List<String>> effectiveWeightMap = new HashMap<>();
            for (String support : supports) {
                Node node = nodes.get(support);
                effectiveWeightMap.computeIfAbsent(node.getEffectiveWeight(), _ -> new ArrayList<>())
                        .add(node.name);
            }
            Node wrongNode = null;
            int correctEffectiveWeight = 0;
            for (List<String> nodeList : effectiveWeightMap.values()) {
                if (nodeList.size() == 1) {
                    wrongNode = nodes.get(nodeList.getFirst());
                } else {
                    correctEffectiveWeight = nodes.get(nodeList.getFirst()).getEffectiveWeight();
                }
            }
            int offset;
            if (wrongNode != null) {
                offset = wrongNode.getEffectiveWeight() - correctEffectiveWeight;
                return wrongNode.weight - offset;
            }
            return 0;
        }
    }
}
