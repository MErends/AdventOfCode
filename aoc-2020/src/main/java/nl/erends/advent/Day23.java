package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;

import java.util.HashMap;
import java.util.Map;

public class Day23 extends AbstractProblem<String, Number> {

    static void main() {
        new Day23().setAndSolve("562893147");
    }
    
    public Integer solve1() {
        Circle circle = new Circle(input);
        circle.iterate(100);
        return circle.solution1();
    }
    
    @Override
    public Long solve2() {
        Circle circle = new Circle(input);
        for (int i = circle.maxValue + 1; i <= 1_000_000; i++) {
            circle.addNode(i);
        }
        circle.iterate(10_000_000);
        return circle.solution2();
        
    }
    
    private static class Circle {
        int current;
        int end;
        int maxValue = Integer.MIN_VALUE;
        final Map<Integer, Integer> links = new HashMap<>();
        
        private Circle(String input) {
            for (char c : input.toCharArray()) {
                addNode(Integer.parseInt("" + c));
            }
        }
        
        private void addNode(int value) {
            maxValue = Integer.max(maxValue, value);
            if (links.isEmpty()) {
                current = value;
                end = current;
                links.put(current, value);
            } else {
                links.put(end, value);
            }
            end = value;
            links.put(end, current);
        }
        
        private void iterate(int times) {
            for (int i = 0; i < times; i++) {
                doIterate();
            }
        }
        
        private void doIterate() {
            int p1 = links.get(current);
            int p2 = links.get(p1);
            int p3 = links.get(p2);
            int p4 = links.get(p3);
            links.put(current, p4);
            int destination = current - 1;
            if (destination == 0) {
                destination = maxValue;
            }
            while (p1 == destination || p2 == destination || p3 == destination) {
                destination--;
                if (destination == 0) {
                    destination = maxValue;
                }
            }
            links.put(p3, links.get(destination));
            links.put(destination, p1);
            current = links.get(current);
        }
        
        private int solution1() {
            int value = links.get(1);
            StringBuilder solution = new StringBuilder();
            while (value != 1) {
                solution.append(value);
                value = links.get(value);
            }
            return Integer.parseInt(solution.toString());
        }
        
        private long solution2() {
            int v1 = links.get(1);
            int v2 = links.get(v1);
            return (long) v1 * v2;
        }
    }
}
