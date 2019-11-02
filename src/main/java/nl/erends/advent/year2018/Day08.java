package nl.erends.advent.year2018;

import nl.erends.advent.util.Util;

import java.util.*;
import java.util.stream.Collectors;

public class Day08 {

    public static void main(String[] args) {
        List<String> input = Arrays.asList(Util.getFileAsList("2018day08.txt").get(0).split(" "));
        long start = System.currentTimeMillis();
        List<Integer> rootSource = input.stream().map(Integer::parseInt).collect(Collectors.toList());
        Node rootNode = new Node(rootSource);
        System.out.println(rootNode.totalMeta());
        long mid = System.currentTimeMillis();
        System.out.println(rootNode.rootValue());
        long end = System.currentTimeMillis();
        System.out.println("Part 1: " + (mid - start) + " millis.\nPart 2: " + (end - mid) + " millis.");
    }
    
    private static class Node {
        private int childrenCount;
        private int metadataCount;
        private List<Integer> trueSource;
        private List<Node> children;
        private List<Integer> metadata;

        private Node(List<Integer> source) {
            trueSource = new ArrayList<>();
            metadata = new ArrayList<>();
            List<Integer> rawSource = new ArrayList<>(source);
            childrenCount = rawSource.get(0);
            metadataCount = rawSource.get(1);
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
