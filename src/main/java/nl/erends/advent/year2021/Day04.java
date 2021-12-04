package nl.erends.advent.year2021;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * --- Day 4: Giant Squid ---
 * You play bingo with a giant squid. What are the scores of bingo boards that
 * are the first and last to win?
 * <p><a href="https://adventofcode.com/2021/day/4">2021 Day 4</a>
 */
public class Day04 extends AbstractProblem<List<String>, Integer> {
    
    public static void main(String[] args) {
        new Day04().setAndSolve(Util.readInput(2021, 4));
    }

    @Override
    protected Integer solve1() {
        List<Integer> drawnNumbers = Arrays.stream(input.get(0).split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        List<BingoCard> bingoCards = new ArrayList<>();
        for (int lineNr = 2; lineNr < input.size(); lineNr += 6) {
            bingoCards.add(new BingoCard(input.subList(lineNr, lineNr + 5)));
        }

        int answer1 = 0;
        for (int drawnNumber : drawnNumbers) {
            bingoCards.forEach(b -> b.mark(drawnNumber));
            List<BingoCard> winners = bingoCards.stream()
                    .filter(BingoCard::isWinner)
                    .collect(Collectors.toList());
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
        
        int[][] card = new int[5][5];
        
        BingoCard(List<String> cardStrings) {
            for (int lineNr = 0; lineNr < cardStrings.size(); lineNr++) {
                card[lineNr] = Arrays.stream(cardStrings.get(lineNr).split(" "))
                        .filter(s -> !s.isEmpty())
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }
        }
        
        void mark(int mark) {
            for (int y = 0; y < card.length; y++) {
                for (int x = 0; x < card[y].length; x++) {
                    if (card[y][x] == mark) {
                        card[y][x] = -1;
                        return;
                    }
                }
            }
        }
        
        boolean isWinner() {
            for (int[] line : card) {
                int sum = line[0] + line[1] + line[2] + line[3] + line[4];
                if (sum == -5) {
                    return true;
                }
            }
            for (int col = 0; col < card[0].length; col++) {
                int sum = card[0][col] + card[1][col] + card[2][col] + card[3][col] + card[4][col];
                if (sum == -5) {
                    return true;
                }
            }
            return false;
        }
        
        int unmarkedTotal() {
            return Arrays.stream(card)
                    .flatMapToInt(Arrays::stream)
                    .filter(i -> i != -1)
                    .sum();
        }
    }
}
