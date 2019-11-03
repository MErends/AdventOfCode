package nl.erends.advent.year2015;

import nl.erends.advent.util.Problem;
import nl.erends.advent.util.Timer;
import nl.erends.advent.util.Util;
import org.apache.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

public class Day20 implements Problem<Integer, Integer> {
    
    private static final Logger LOG = Logger.getLogger(Day20.class);

    public static void main(String[] args) {
        int input = Integer.parseInt(Util.readLine(2015, 20));
        Day20 problem = new Day20();
        LOG.info(problem.solve1(input));
        LOG.info(problem.solve2(input));
        Timer.printStats();
    }
    
    public Integer solve1(Integer input) {
        Timer.start1();
        for (int house = 1; ; house++) {
            Set<Integer> delers = delers(house);
            int presents = delers.stream().mapToInt(i -> i).sum() * 10;
            if (presents >= input) {
                Timer.end1();
                return house;
            }
        }
    }
    
    public Integer solve2(Integer input) {
        Timer.start2();
        for (int house = 1; ; house++) {
            Set<Integer> delers = delers(house);
            int finalHouse = house;
            int presents = delers.stream().mapToInt(i -> i).filter(deler -> deler * 50 >= finalHouse).sum() * 11;
            if (presents >= input) {
                Timer.end2();
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
