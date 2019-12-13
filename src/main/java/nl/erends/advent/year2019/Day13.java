package nl.erends.advent.year2019;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

public class Day13 extends AbstractProblem<String, Integer> {

    public static void main(String[] args) {
        new Day13().setAndSolve(Util.readLine(2019, 13));
    }

    @Override
    public Integer solve1() {
        Intcode intcode = new Intcode(input);
        int numberOfBlocks = 0;
        while (intcode.execute()) {
            intcode.execute();
            intcode.execute();
            long tileId = intcode.getOutput();
            if (tileId == 2) {
                numberOfBlocks++;
            }
        }
        return numberOfBlocks;
    }

    @Override
    public Integer solve2() {
        Game game = new Game(input);
        return game.score;
    }

    private class Game {

        private int score;

        private Game(String code) {
            int ballX = 0;
            int paddleX = 0;
            Intcode computer = new Intcode(code);
            computer.setCode(0, 2);
            while (computer.execute()) {
                if (computer.hasOutput()) {
                    int x = (int) computer.getOutput().longValue();
                    computer.execute();
                    computer.execute();
                    int tile = (int) computer.getOutput().longValue();
                    if (x == -1) {
                        score = tile;
                    } else {
                        if (tile == 4) {
                            ballX = x;
                        } else if (tile == 3) {
                            paddleX = x;
                        }
                    }
                } else {
                    computer.addInput(Integer.compare(ballX, paddleX));
                }
            }
        }
    }
}
