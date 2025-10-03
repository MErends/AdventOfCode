package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashSet;
import java.util.Set;

public class Day20 extends AbstractProblem<Integer, Integer> {

    void main() {
        new Day20().setAndSolve(Integer.parseInt(Util.readLine(2015, 20)));
    }

    @Override
    public Integer solve1() {
        for (int house = 1; ; house++) {
            Set<Integer> delers = delers(house);
            int presents = delers.stream().mapToInt(i -> i).sum() * 10;
            if (presents >= input) {
                return house;
            }
        }
    }

    @Override
    public Integer solve2() {
        for (int house = 1; ; house++) {
            Set<Integer> delers = delers(house);
            int finalHouse = house;
            int presents = delers.stream().mapToInt(i -> i).filter(deler -> deler * 50 >= finalHouse).sum() * 11;
            if (presents >= input) {
                return house;
            }
        }
    }

    private Set<Integer> delers(int getal) {
        Set<Integer> delers = new HashSet<>();
        delers.add(1);
        delers.add(getal);
        int limit  = (int) Math.sqrt(getal);
        for (int deler = 2; deler <= limit; deler++) {
            int noemer = getal / deler;
            if (noemer * deler == getal) {
                delers.add(deler);
                delers.add(noemer);
            }
        }
        return delers;
    }
}
