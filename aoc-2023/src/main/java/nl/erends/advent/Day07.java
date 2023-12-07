package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <h1>--- Day 7: Camel Cards ---</h1>
 * <p>Because the journey will take a few days, the Elf offers to teach you the
 * game of Camel Cards. Camel Cards is sort of similar to poker except it's
 * designed to be easier to play while riding a camel. Hands are primarily
 * ordered based on type; for example, every full house is stronger than any
 * three of a kind. Now, you can determine the total winnings of this set of
 * hands by adding up the result of multiplying each hand's bid with its rank.
 * </p>
 * <p><a href="https://adventofcode.com/2023/day/7">2023 Day 7</a></p>
 */
public class Day07 extends AbstractProblem<List<String>, Number> {

    public static void main(String[] args) {
        new Day07().setAndSolve(Util.readInput(2023, 7));
    }

    @Override
    protected Number solve1() {
        List<Hand> hands = input.stream()
                .map(Hand::new)
                .collect(Collectors.toList());
        List<Hand> fiveKind = hands.stream()
                .filter(h -> h.cardCount.size() <= 1)
                .sorted(Hand::comparing).toList();
        List<Hand> ordered = new ArrayList<>(fiveKind);
        hands.removeAll(fiveKind);
        List<Hand> fourkind = hands.stream()
                .filter(h -> h.hasCardCount(4))
                .sorted(Hand::comparing).toList();
        hands.removeAll(fourkind);
        ordered.addAll(fourkind);
        List<Hand> fullHouse = hands.stream()
                .filter(h -> h.cardCount.size() == 2)
                .sorted(Hand::comparing).toList();
        hands.removeAll(fullHouse);
        ordered.addAll(fullHouse);
        List<Hand> threeKind = hands.stream()
                .filter(h -> h.hasCardCount(3))
                .sorted(Hand::comparing).toList();
        hands.removeAll(threeKind);
        ordered.addAll(threeKind);
        List<Hand> twoPair = hands.stream()
                .filter(h -> h.cardCount.size() == 3)
                .sorted(Hand::comparing).toList();
        hands.removeAll(twoPair);
        ordered.addAll(twoPair);
        List<Hand> pair = hands.stream()
                .filter(h -> h.hasCardCount(2))
                .sorted(Hand::comparing).toList();
        hands.removeAll(pair);
        ordered.addAll(pair);
        hands.sort(Hand::comparing);
        ordered.addAll(hands);
        int rank = ordered.size();
        int winnings = 0;
        for (Hand hand : ordered) {
            winnings += rank * hand.bid;
            rank--;
        }
        return winnings;
    }

    @Override
    public Number solve2() {
        Hand.setJokerEnabled();
        return solve1();
    }

    private static class Hand {

        private static boolean jokerEnabled = false;

        private final List<Character> cardOrder = List.of('A', 'K', 'Q', 'J', 'T', '9', '8', '7', '6', '5', '4', '3', '2');
        private final List<Character> jokerOrder = List.of('A', 'K', 'Q', 'T', '9', '8', '7', '6', '5', '4', '3', '2', 'J');
        private final Map<Character, Integer> cardCount = new HashMap<>();
        private final String cards;
        private final int bid;
        private int jokers;

        private Hand(String line) {
            cards = line.substring(0, 5);
            for (int index = 0; index < 5; index++) {
                char card = line.charAt(index);
                if (card == 'J' && jokerEnabled) {
                    jokers++;
                } else {
                    cardCount.compute(card, (c, i) -> i == null ? 1 : i + 1);
                }
            }
            bid = Integer.parseInt(line.substring(6));
        }

        public int comparing(Hand o) {
            List<Character> currentOrder = jokerEnabled ? jokerOrder : cardOrder;
            for (int i = 0; i < 5; i++) {
                int compare = currentOrder.indexOf(this.cards.charAt(i)) - currentOrder.indexOf(o.cards.charAt(i));
                if (compare != 0) {
                    return compare;
                }
            }
            return 0;
        }

        private boolean hasCardCount(int count) {
            if (jokerEnabled) {
                return cardCount.values().stream().
                        map(i -> i + jokers)
                        .anyMatch(i -> i == count);
            } else {
                return cardCount.containsValue(count);
            }
        }

        public static void setJokerEnabled() {
            jokerEnabled = true;
        }
    }
}
