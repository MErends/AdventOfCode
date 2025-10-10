package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * --- Day 8: Seven Segment Search ---
 * <p>As your submarine slowly makes its way through the cave system, you notice
 * that the four-digit seven-segment displays in your submarine are
 * malfunctioning. For each entry, determine all of the wire/segment connections
 * and decode the four-digit output values. What do you get if you add up all of
 * the output values?
 * <p><a href="https://adventofcode.com/2021/day/8">2021 Day 8</a>
 */
public class Day08 extends AbstractProblem<List<String>, Integer> {

    void main() {
        new Day08().setAndSolve(Util.readInput(2021, 8));
    }
    
    @Override
    protected Integer solve1() {
        List<Integer> lookingFor = List.of(2, 3, 4, 7);
        return (int) input.stream()
                .map(Display::new)
                .flatMap(d -> d.values.stream())
                .filter(s -> lookingFor.contains(s.length()))
                .count();
    }

    @Override
    public Integer solve2() {
        List<Display> displays = input.stream()
                .map(Display::new)
                .toList();
        displays.forEach(Display::solve);
        return displays.stream().mapToInt(Display::getValue).sum();
    }

    private static class Display {
        private final List<String> patterns;
        private final List<String> values;
        private final List<String> mapping = Arrays.asList(new String[10]);
        
        private Display(String line) {
            String[] split = line.split(" \\| ");
            patterns = Arrays.stream(split[0].split(" "))
                    .map(Day08::orderString)
                    .collect(Collectors.toList());
            values = Arrays.stream(split[1].split(" "))
                    .map(Day08::orderString)
                    .toList();
        }
        
        private void solve() {
            mapping.set(1, patterns.stream()
                    .filter(p -> p.length() == 2)
                    .findFirst().orElseThrow());
            patterns.remove(mapping.get(1));
            mapping.set(7, patterns.stream().
                    filter(p -> p.length() == 3)
                    .findFirst().orElseThrow());
            patterns.remove(mapping.get(7));
            mapping.set(4, patterns.stream()
                    .filter(p -> p.length() == 4)
                    .findFirst().orElseThrow());
            patterns.remove(mapping.get(4));
            mapping.set(8, patterns.stream()
                    .filter(p -> p.length() == 7)
                    .findFirst().orElseThrow());
            patterns.remove(mapping.get(8));
            mapping.set(3, patterns.stream()
                    .filter(p -> p.length() == 5 && containsAllChars(p, mapping.get(1)))
                    .findFirst().orElseThrow());
            patterns.remove(mapping.get(3));
            mapping.set(9, patterns.stream()
                    .filter(p -> p.length() == 6 && containsAllChars(p, mapping.get(4)))
                    .findFirst().orElseThrow());
            patterns.remove(mapping.get(9));
            mapping.set(0, patterns.stream()
                    .filter(p -> p.length() == 6 && containsAllChars(p, mapping.get(1)))
                    .findFirst().orElseThrow());
            patterns.remove(mapping.getFirst());
            mapping.set(6, patterns.stream()
                    .filter(p -> p.length() == 6)
                    .findFirst().orElseThrow());
            patterns.remove(mapping.get(6));
            mapping.set(5, patterns.stream()
                    .filter(p -> containsAllChars(mapping.get(6), p))
                    .findFirst().orElseThrow());
            patterns.remove(mapping.get(5));
            mapping.set(2, patterns.getFirst());
        }
        
        private int getValue() {
            return 1000 * mapping.indexOf(values.getFirst())
                    + 100 * mapping.indexOf(values.get(1))
                    + 10 * mapping.indexOf(values.get(2))
                    + mapping.indexOf(values.get(3));
        }

        private boolean containsAllChars(String subject, String source) {
            for (char ch : source.toCharArray()) {
                if (subject.indexOf(ch) == -1) {
                    return false;
                }
            }
            return true;
        }
    }
    
    private static String orderString(String in) {
        return in.chars()
                .sorted()
                .mapToObj(i -> String.valueOf((char) i))
                .collect(Collectors.joining());
    }
}
