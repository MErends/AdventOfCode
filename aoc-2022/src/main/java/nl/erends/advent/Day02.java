package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

/**
 * --- Day 2: Rock Paper Scissors ---
 * <p>The Elves begin to set up camp on the beach. To decide whose tent gets to
 * be closest to the snack storage, a giant Rock Paper Scissors tournament is
 * already in progress. What would your total score be if everything goes
 * exactly according to your strategy guide?
 * <p><a href="https://adventofcode.com/2022/day/2">2022 Day 2</a>
 */
public class Day02 extends AbstractProblem<List<String>, Integer> {

    static void main() {
        new Day02().setAndSolve(Util.readInput(2022, 2));
    }

    @Override
    protected Integer solve1() {
        int score = 0;
        for (String line : input) {
            Move me = Move.getMove(line.charAt(2));
            Move them = Move.getMove(line.charAt(0));
            if (me == Move.ROCK && them == Move.SCISSORS
            || me == Move.PAPER && them == Move.ROCK
            || me == Move.SCISSORS && them == Move.PAPER) {
                score += 6;
            } else if (me == them) {
                score += 3;
            }
            score += me.score;
        }
        return score;
    }

    @Override
    public Integer solve2() {
        int score = 0;
        for (String line : input) {
            Move them = Move.getMove(line.charAt(0));
            switch (line.charAt(2)) {
                case 'X' -> score += Move.losesTo(them).score;
                case 'Y' -> score += 3 + them.score;
                default -> score += 6 + Move.winsOver(them).score;
            }
        }
        return score;
    }

    private enum Move {
        ROCK(1),
        PAPER(2),
        SCISSORS(3);

        private final int score;

        Move(int score) {
            this.score = score;
        }

        static Move getMove(char in) {
            return switch (in) {
                case 'A', 'X' -> ROCK;
                case 'B', 'Y' -> PAPER;
                default -> SCISSORS;
            };
        }

        static Move losesTo(Move other) {
            return switch (other) {
                case ROCK -> SCISSORS;
                case PAPER -> ROCK;
                default -> PAPER;
            };
        }

        static Move winsOver(Move other) {
            return switch (other) {
                case ROCK -> PAPER;
                case PAPER -> SCISSORS;
                default -> ROCK;
            };
        }
    }
}
