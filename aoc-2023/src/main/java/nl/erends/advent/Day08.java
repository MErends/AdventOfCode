package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <h1>--- Day 8: Haunted Wasteland ---</h1>
 * <p>You're still riding a camel across Desert Island when you spot a sandstorm
 * quickly approaching. It seems like you're meant to use the left/right
 * instructions to navigate the network. Starting at AAA, follow the left/right
 * instructions. How many steps are required to reach ZZZ?</p>
 * <p><a href="https://adventofcode.com/2023/day/8">2023 Day 8</a></p>
 */
public class Day08 extends AbstractProblem<List<String>, Number> {

    private List<String> nodeList;
    private Map<String, String> leftMap;
    private Map<String, String> rightMap;

    public static void main(String[] args) {
        new Day08().setAndSolve(Util.readInput(2023, 8));
    }

    @Override
    protected Number solve1() {
        readData();
        return stepsToExit("AAA");
    }

    @Override
    public Number solve2() {
        readData();
        return nodeList.stream()
                .filter(n -> n.charAt(2) == 'A')
                .mapToLong(this::stepsToExit)
                .reduce(1L, Util::lcm);
    }

    private void readData() {
        leftMap = new HashMap<>();
        rightMap = new HashMap<>();
        nodeList = new ArrayList<>();
        for (int index = 2; index < input.size(); index++) {
            String line = input.get(index);
            String node = line.substring(0, 3);
            nodeList.add(node);
            leftMap.put(node, line.substring(7, 10));
            rightMap.put(node, line.substring(12, 15));
        }
    }

    private int stepsToExit(String node) {
        int steps = 0;
        while (node.charAt(2) != 'Z') {
            node = input.getFirst().charAt(steps % input.getFirst().length()) == 'L' ? leftMap.get(node) : rightMap.get(node);
            steps++;
        }
        return steps;
    }
}