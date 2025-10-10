package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static nl.erends.advent.util.Util.ASCII_OFFSET;

/**
 * --- Day 21: Dirac Dice ---
 * <p>There's not much to do as you slowly descend to the bottom of the ocean.
 * The submarine computer challenges you to a nice game of Dirac Dice. when you
 * roll the dice, the universe splits into multiple copies. In how many
 * universes does the player that wins most, win?
 * <p><a href="https://adventofcode.com/2021/day/21">2021 Day 21</a>
 */
public class Day21 extends AbstractProblem<List<String>, Number> {

    private int die = 1;
    private int dicerolls = 0;
    
    private long player1Wins = 0;
    private long player2Wins = 0;
    
    private final Map<Integer, Integer> diracDice = new HashMap<>();
    
    void main() {
        new Day21().setAndSolve(Util.readInput(2021, 21));
    }

    @Override
    protected Integer solve1() {
        int position1 = input.getFirst().charAt(28) - ASCII_OFFSET;
        int position2 = input.get(1).charAt(28) - ASCII_OFFSET;
        int score1 = 0;
        int score2 = 0;
        
        while (score2 < 1000) {
            position1 += roll();
            while (position1 > 10) {
                position1 -= 10;
            }
            score1 += position1;
            if (score1 >= 1000) {
                break;
            }
            position2 += roll();
            while (position2 > 10) {
                position2 -= 10;
            }
            score2 += position2;
        }
        
        return Math.min(score1, score2) * dicerolls;
    }
    
    private int roll() {
        int roll = 0;
        for (int i = 0; i < 3; i++) {
            dicerolls++;
            roll += die++;
            if (die > 100) {
                die = 1;
            }
        }
        return roll;
    }

    @Override
    public Long solve2() {
        for (int die1 = 1; die1 <= 3; die1++) {
            for (int die2 = 1; die2 <= 3; die2++) {
                for (int die3 = 1; die3 <= 3; die3++) {
                    diracDice.compute(die1 + die2 + die3, (_, v) -> v == null ? 1 : v + 1);
                }
            }    
        }
        int position1 = input.getFirst().charAt(28) - ASCII_OFFSET;
        int position2 = input.get(1).charAt(28) - ASCII_OFFSET;
        playGame(0, 0, position1, position2, 1, true);
        return Math.max(player1Wins, player2Wins);
    }

    private void playGame(int score1, int score2, int position1, int position2, long weight, boolean turn1) {
        if (score1 >= 21) {
            player1Wins += weight;
            return;
        }
        if (score2 >= 21) {
            player2Wins += weight;
            return;
        }
        for (Map.Entry<Integer, Integer> dieroll : diracDice.entrySet()) {
            int newScore1 = score1;
            int newScore2 = score2;
            int newPosition1 = position1;
            int newPosition2 = position2;
            if (turn1) {
                newPosition1 += dieroll.getKey();
                if (newPosition1 > 10) {
                    newPosition1 -= 10;
                }
                newScore1 += newPosition1;
            } else {
                newPosition2 += dieroll.getKey();
                if (newPosition2 > 10) {
                    newPosition2 -= 10;
                }
                newScore2 += newPosition2;

            }
            playGame(newScore1, newScore2, newPosition1, newPosition2, weight * dieroll.getValue(), !turn1);
        }
    }
}
