package nl.erends.advent.year2021;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import java.util.Map;

/**
 * --- Day 10: Syntax Scoring ---
 * <p>The navigation subsystem syntax is made of several lines containing
 * chunks. Some lines are incomplete, but others are corrupted. Determine the
 * total syntax error score and autocomplete score for the incomplete lines.
 * <p><a href="https://adventofcode.com/2021/day/10">2021 Day 10</a>
 */
public class Day10 extends AbstractProblem<List<String>, Number> {

    private final Map<Character, Character> brackets = Map.of('<', '>', '{', '}', '[', ']', '(', ')');
    private final Map<Character, Integer> syntaxScore = Map.of(')', 3, ']', 57, '}', 1197, '>', 25137, '\0', 0);
    private final Map<Character, Long> autocompleteScore = Map.of('(', 1L, '[', 2L, '{', 3L, '<', 4L);
    
    public static void main(String[] args) {
        new Day10().setAndSolve(Util.readInput(2021, 10));
    }
    
    @Override
    protected Integer solve1() {
        List<LineParser> lineParsers = input.stream()
                .map(LineParser::new)
                .toList();
        
        List<Long> scores = lineParsers.stream()
                .map(LineParser::stackScore)
                .filter(i -> i != 0)
                .sorted()
                .toList();
        
        answer2 = scores.get(scores.size() / 2);
        return lineParsers.stream()
                .mapToInt(LineParser::syntaxScore)
                .sum();
    }
    
    private class LineParser {
        
        char corruptChar;
        final Deque<Character> stack = new ArrayDeque<>();
        
        private LineParser(String line) {
            for (char found : line.toCharArray()) {
                if (brackets.containsKey(found)) {
                    stack.addFirst(found);
                } else {
                    char expected = brackets.get(stack.removeFirst());
                    if (expected != found) {
                        corruptChar = found;
                        return;
                    }
                }
            }
        }
        
        private long stackScore() {
            if (corruptChar != '\0') {
                return 0;
            }
            return stack.stream()
                    .mapToLong(autocompleteScore::get)
                    .reduce(0, (s1, s2) -> s1 * 5 + s2);
        }
        
        private int syntaxScore() {
            return syntaxScore.get(corruptChar);
        }
    }
}
