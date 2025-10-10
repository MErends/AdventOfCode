package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day08 extends AbstractProblem<String, Integer> {

    void main() {
        new Day08().setAndSolve(Util.readLine(2018, 8));
    }
    
    @Override
    public Integer solve1() {
        List<String> chain = Arrays.asList(input.split(" "));
        List<Integer> rootSource = chain.stream().map(Integer::parseInt).toList();
        Node rootNode = new Node(rootSource);
        int answer1 = rootNode.totalMeta();
        answer2 = rootNode.rootValue();
        return answer1;
    }
    
    private static class Node {
        private final int childrenCount;
        private final List<Integer> trueSource;
        private final List<Node> children;
        private final List<Integer> metadata;

        private Node(List<Integer> source) {
            trueSource = new ArrayList<>();
            metadata = new ArrayList<>();
            List<Integer> rawSource = new ArrayList<>(source);
            childrenCount = rawSource.getFirst();
            int metadataCount = rawSource.get(1);
            children = new ArrayList<>();
            if (childrenCount == 0) {
                trueSource.addAll(rawSource.subList(0, 2 + metadataCount));
                metadata.addAll(trueSource.subList(2, trueSource.size()));
            } else {
                trueSource.addAll(rawSource.subList(0, 2));
                rawSource = rawSource.subList(2, rawSource.size());
                for (int childIndex = 0; childIndex < childrenCount; childIndex++) {
                    Node child = new Node(rawSource);
                    children.add(child);
                    trueSource.addAll(child.trueSource);
                    rawSource = rawSource.subList(child.trueSource.size(), rawSource.size());
                }
                metadata.addAll(rawSource.subList(0, metadataCount));
                trueSource.addAll(metadata);
            }
        }

        private int totalMeta() {
            return children.stream().mapToInt(Node::totalMeta).sum() + metadata.stream().mapToInt(i -> i).sum();
        }

        private int rootValue() {
            if (childrenCount == 0) {
                return totalMeta();
            } else {
                int rootValue = 0;
                for (int index : metadata) {
                    if (index <= childrenCount) {
                        rootValue += children.get(index - 1).rootValue();
                    }
                }
                return rootValue;
            }
        }
    }
}
