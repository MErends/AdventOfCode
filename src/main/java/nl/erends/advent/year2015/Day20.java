package nl.erends.advent.year2015;

import nl.erends.advent.util.Timer;

import java.util.HashSet;
import java.util.Set;

public class Day20 {

    public static void main(String[] args) {
        Timer.start1();
        int maxPresent = 33100000;
        for (int house = 1; ; house++) {
            Set<Integer> delers = delers(house);
            int presents = delers.stream().mapToInt(i -> i).sum() * 10;
            if (presents >= maxPresent) {
                Timer.end1();
                System.out.println(house);
                break;
            }
        }

        Timer.start2();
        for (int house = 1; ; house++) {
            Set<Integer> delers = delers(house);
            int finalHouse = house;
            int presents = delers.stream().mapToInt(i -> i).filter(deler -> deler * 50 >= finalHouse).sum() * 11;
            if (presents >= maxPresent) {
                Timer.end2();
                System.out.println(house);
                break;
            }
        }
        Timer.printStats();
    }

    private static Set<Integer> delers(int getal) {
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
