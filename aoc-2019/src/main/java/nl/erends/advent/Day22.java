package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Day22 extends AbstractProblem<List<String>, Integer> {
    
    private static final int NUM_CARDS = 10007;

    public static void main(String[] args) {
        new Day22().setAndSolve(Util.readInput(2019, 22));
    }

    @Override
    public Integer solve1() {
        List<Integer> deck = new ArrayList<>();
        for (int i = 0; i < NUM_CARDS; i++) {
            deck.add(i);
        }
        for (String line : input) {
            String[] words = line.split(" ");
            if (words[0].equals("cut")) {
                cutStack(deck, Integer.parseInt(words[1]));
            } else if (words[2].equals("increment")) {
                dealIncrement(deck, Integer.parseInt(words[3]));
            } else {
                dealNewStack(deck);
            }
        }
        return deck.indexOf(2019); // 8562 too high
    }

    @Override
    public Integer solve2() {
        return null;
    }

    private void dealNewStack(List<Integer> deck) {
        Collections.reverse(deck);
    }
    
    private void cutStack(List<Integer> deck, int n) {
        if (n < 0) {
            n = deck.size() + n;
        }
        List<Integer> tempList = new ArrayList<>(deck.subList(n, deck.size()));
        tempList.addAll(deck.subList(0, n));
        deck.clear();
        deck.addAll(tempList);
    }
    
    private void dealIncrement(List<Integer> deck, int increment) {
        List<Integer> tempList = new ArrayList<>(deck);
        for (int index = 0; index < deck.size(); index++) {
            int newIndex = (index * increment) % deck.size();
            tempList.set(newIndex, deck.get(index));
        }
        deck.clear();
        deck.addAll(tempList);
    }
    
    
}
