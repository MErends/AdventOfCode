package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * --- Day 4: Giant Squid ---
 * <p>You play bingo with a giant squid. What are the scores of the bingo boards
 * that are the first and last to win?
 * <p><a href="https://adventofcode.com/2021/day/4">2021 Day 4</a>
 */
public class Day04 extends AbstractProblem<List<String>, Integer> {
    
    private static final int CARD_SIZE = 5;
    private static final int MARKED = -1;
    
    static void main() {
        new Day04().setAndSolve(Util.readInput(2021, 4));
    }

    @Override
    protected Integer solve1() {
        List<Integer> drawnNumbers = Arrays.stream(input.removeFirst().split(","))
                .map(Integer::parseInt)
                .toList();
        input.removeIf(String::isEmpty);
        List<BingoCard> bingoCards = new ArrayList<>();
        for (int lineNr = 0; lineNr < input.size(); lineNr += CARD_SIZE) {
            bingoCards.add(new BingoCard(input.subList(lineNr, lineNr + CARD_SIZE)));
        }

        int answer1 = 0;
        for (int drawnNumber : drawnNumbers) {
            bingoCards.forEach(b -> b.mark(drawnNumber));
            List<BingoCard> winners = bingoCards.stream()
                    .filter(BingoCard::isWinner)
                    .toList();
            for (BingoCard winner : winners) {
                if (answer1 == 0) {
                    answer1 = winner.unmarkedTotal() * drawnNumber;
                }
                if (bingoCards.size() == 1) {
                    answer2 = winner.unmarkedTotal() * drawnNumber;
                    return answer1;
                }
                bingoCards.remove(winner);
            }
        }
        throw new IllegalStateException("No winning cards?");
    }
    
    private static class BingoCard {
        
        final int[][] card = new int[5][5];
        
        BingoCard(List<String> cardStrings) {
            for (int lineNr = 0; lineNr < CARD_SIZE; lineNr++) {
                card[lineNr] = Arrays.stream(cardStrings.get(lineNr).split(" "))
                        .filter(s -> !s.isEmpty())
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }
        }
        
        void mark(int mark) {
            for (int y = 0; y < CARD_SIZE; y++) {
                for (int x = 0; x < CARD_SIZE; x++) {
                    if (card[y][x] == mark) {
                        card[y][x] = MARKED;
                        return;
                    }
                }
            }
        }
        
        boolean isWinner() {
            for (int[] line : card) {
                int sum = Arrays.stream(line).sum();
                if (sum == CARD_SIZE * MARKED) {
                    return true;
                }
            }
            for (int col = 0; col < CARD_SIZE; col++) {
                int sum = 0;
                for (int line = 0; line < CARD_SIZE; line ++) {
                    sum += card[line][col];
                }
                if (sum == CARD_SIZE * MARKED) {
                    return true;
                }
            }
            // Here lived the code to check the diagonals :(
            return false;
        }
        
        int unmarkedTotal() {
            return Arrays.stream(card)
                    .flatMapToInt(Arrays::stream)
                    .filter(i -> i != MARKED)
                    .sum();
        }
    }
}
