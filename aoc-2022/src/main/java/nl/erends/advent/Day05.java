package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * --- Day 5: Supply Stacks ---
 * <p>The expedition can depart as soon as the final supplies have been unloaded
 * from the ships. Supplies are stored in stacks of marked crates, but because
 * the needed supplies are buried under many other crates, the crates need to be
 * rearranged. After the rearrangement procedure completes, what crate ends up
 * on top of each stack?
 * <p><a href="https://adventofcode.com/2022/day/5">2022 Day 5</a>
 */
public class Day05 extends AbstractProblem<List<String>, String> {

    private final List<Stack<Character>> stacks = new ArrayList<>();

    void main() {
        new Day05().setAndSolve(Util.readInput(2022, 5));
    }

    @Override
    protected String solve1() {
        loadStacks();
        for (String line : input) {
            if (!line.startsWith("move")) {
                continue;
            }
            String[] words = line.split(" ");
            for (int i = 0; i < Integer.parseInt(words[1]); i++) {
                Stack<Character> fromStack = stacks.get(Integer.parseInt(words[3]) - 1);
                Stack<Character> toStack = stacks.get(Integer.parseInt(words[5]) - 1);
                toStack.push(fromStack.pop());
            }
        }
        return stacks.stream()
                .map(s -> s.pop() + "")
                .collect(Collectors.joining());
    }

    @Override
    public String solve2() {
        stacks.clear();
        loadStacks();
        for (String line : input) {
            if (!line.startsWith("move")) {
                continue;
            }
            String[] words = line.split(" ");
            Stack<Character> tempStack = new Stack<>();
            Stack<Character> fromStack = stacks.get(Integer.parseInt(words[3]) - 1);
            Stack<Character> toStack = stacks.get(Integer.parseInt(words[5]) - 1);
            for (int i = 0; i < Integer.parseInt(words[1]); i++) {
                tempStack.push(fromStack.pop());
            }
            while (!tempStack.empty()) {
                toStack.push(tempStack.pop());
            }
        }
        return stacks.stream()
                .map(s -> s.pop() + "")
                .collect(Collectors.joining());
    }

    private void loadStacks() {
        int lineLength = input.getFirst().length();
        int stackCount = lineLength / 4 + 1;
        IntStream.range(0, stackCount)
                .forEach(_ -> stacks.add(new Stack<>()));
        for (String line : input) {
            if (line.isBlank()) {
                return;
            }
            for (int pos = 0; pos < line.length(); pos++) {
                char crate = line.charAt(pos);
                if (Character.isAlphabetic(crate)) {
                    stacks.get(pos / 4).addFirst(crate);
                }
            }
        }
    }
}
