package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;

import java.util.ArrayList;
import java.util.List;

public class Day19 extends AbstractProblem<Integer, Integer> {

    public static void main(String[] args) {
        new Day19().setAndSolve(3005290);
    }
    
    @Override
    public Integer solve1() {
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
                newCircle.addFirst(newCircle.removeLast());
            }
            elveCircle = newCircle;
        }
        return elveCircle.getFirst();
    }
    
    @Override
    public Integer solve2() {
        List<Integer> elveCircle = new ArrayList<>();
        for (int i = 1; i <= input; i++) {
            elveCircle.add(i);
        }
        while (elveCircle.size() != 1) {
            elveCircle.remove(elveCircle.size() / 2);
            elveCircle.add(elveCircle.removeFirst());
        }
        return elveCircle.getFirst();
    }
}
