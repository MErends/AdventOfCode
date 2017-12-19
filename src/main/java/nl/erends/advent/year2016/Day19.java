package nl.erends.advent.year2016;

import java.util.ArrayList;
import java.util.List;

public class Day19 {

    public static void main(String[] args) {
        int input = 3005290;
        List<Integer> elveCircle = new ArrayList<>();
        for (int i = 1; i <= input; i++) {
            elveCircle.add(i);
        }
        while (elveCircle.size() != 1) {
            List<Integer> newCircle = new ArrayList<>();
            for (int pointer = 0; pointer < elveCircle.size(); pointer += 2) {
                newCircle.add(elveCircle.get(pointer));
            }
            if (elveCircle.size() % 2 == 1) {
                newCircle.add(0, newCircle.remove(newCircle.size() - 1));
            }
            elveCircle = newCircle;
        }
        System.out.println(elveCircle.get(0));
        
        List<Integer> elveCircle2 = new ArrayList<>();
        for (int i = 1; i <= input; i++) {
            elveCircle2.add(i);
        }
        while (elveCircle2.size() != 1) {
            elveCircle2.remove(elveCircle2.size() / 2);
            elveCircle2.add(elveCircle2.remove(0));
        }
        System.out.println(elveCircle2.get(0));
    }
}
