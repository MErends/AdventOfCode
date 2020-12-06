package nl.erends.advent.year2020;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day06 extends AbstractProblem<List<String>, Integer> {

    public static void main(String[] args) {
        new Day06().setAndSolve(Util.readInput(2020, 6));
    }
    
    @Override
    public Integer solve1() {
        int solution = 0;
        answer2 = 0;
        String longString = String.join("\n", input);
        String[] groups = longString.split("\n\n");
        for (String group : groups) {
            Set<Integer> answers = new HashSet<>();
            long groupSize = group.chars().filter(a -> a == '\n').count() + 1;
            group = group.replaceAll("\n", "");
            group.chars().forEach(answers::add);
            Map<Character, Integer> answerMap = new HashMap<>();
            for (char answer : group.toCharArray()) {
                int answerCount = answerMap.computeIfAbsent(answer, a -> 0);
                answerCount++;
                answerMap.put(answer, answerCount);
            }
            solution += answers.size();
            answer2 += (int) answerMap.values().stream().filter(ac -> ac == groupSize).count();
        }
        return solution;
    }
}
