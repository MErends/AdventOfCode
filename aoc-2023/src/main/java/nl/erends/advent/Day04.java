package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.Arrays;
import java.util.List;

/**
 * <h1>--- Day 4: Scratchcards ---</h1>
 * <p>The gondola takes you to another island. The Elf there will let you borrow
 * his boat if you figure aut what he has won with his scratchcards. In fact,
 * how many scratchcards does he end up with in total?</p>
 * <p><a href="https://adventofcode.com/2023/day/4">2023 Day 4</a></p>
 */
public class Day04 extends AbstractProblem<List<String>,  Integer> {

    public static void main(String[] args) {
        new Day04().setAndSolve(Util.readInput(2023, 4));
    }

    @Override
    protected Integer solve1() {
        return input.stream()
                .map(Card::new)
                .mapToInt(c -> c.score)
                .sum();
    }

    @Override
    public Integer solve2() {
        List<Card> cards = input.stream().map(Card::new).toList();
        int[] cardCount = new int[cards.size() + 1];
        for (int id = 1; id <= cards.size(); id++) {
            Card currentCard = cards.get(id - 1);
            cardCount[id]++;
            for (int winner = 1; winner <= currentCard.wincount; winner++) {
                cardCount[id + winner] += cardCount[id];
            }
        }
        return Arrays.stream(cardCount).sum();
    }

    private static class Card {

        int id;
        List<Integer> winners;
        List<Integer> myNumbers;
        int wincount = 0;
        int score = 1;

        private Card(String line) {
            String[] lineSplit = line.split(": ");
            this.id = Integer.parseInt(lineSplit[0].substring(5).trim());
            String[] scoreSplit = lineSplit[1].split("\\|");
            String[] winnersString = scoreSplit[0].split(" ");
            String[] myNumbersString = scoreSplit[1].split(" ");
            winners = Arrays.stream(winnersString)
                    .filter(s -> !s.isBlank())
                    .map(Integer::parseInt).toList();
            myNumbers = Arrays.stream(myNumbersString)
                    .filter(s -> !s.isBlank())
                    .map(Integer::parseInt).toList();

            for (int winner : winners) {
                if (myNumbers.contains(winner)) {
                    score *= 2;
                    wincount++;
                }
            }
            score /= 2;
        }
    }
}
