package nl.erends.advent.year2019;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

public class Day01 extends AbstractProblem<List<Integer>, Integer> {

    public static void main(String[] args) {        
        new Day01().setAndSolve(Util.readIntegers(2019, 1));
    }
    
    @Override
    public Integer solve1() {
        return input.stream()
                .mapToInt(this::calculateFuel)
                .sum();
    }
    
    @Override
    public Integer solve2() {
        return input.stream()
                .mapToInt(this::calculateTotalFuel)
                .sum();
    }
    
    private int calculateFuel(int weight) {
        return Integer.max(0, weight / 3 - 2);
    }
    
    private int calculateTotalFuel(int weight) {
        int fuelNeeded = 0;
        while (weight > 0) {
            int additionalFuel = calculateFuel(weight);
            fuelNeeded += additionalFuel;
            weight = additionalFuel;
        }
        return fuelNeeded;
    }
}
