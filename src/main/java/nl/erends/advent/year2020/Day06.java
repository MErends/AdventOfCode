package nl.erends.advent.year2020;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;
import java.util.stream.Collectors;

public class Day06 extends AbstractProblem<List<String>, Integer> {

    public static void main(String[] args) {
        new Day06().setAndSolve(Util.readInput(2020, 6));
    }
    
    @Override
    public Integer solve1() {
        int answer1 = 0;
        answer2 = 0;
        String longString = String.join("\n", input);
        String[] groups = longString.split("\n\n");
        for (String group : groups) {
            long groupSize = group.chars().filter(a -> a == '\n').count() + 1;
            answer1 += group.chars().filter(a -> a != '\n').boxed().collect(Collectors.toSet()).size();
            answer2 += (int) group.chars().boxed().collect(Collectors.toMap(v -> v, v -> 1, (v1, v2) -> v1 + 1)).values().stream().filter(v -> v == groupSize).count();
        }
        return answer1;
    }
}
