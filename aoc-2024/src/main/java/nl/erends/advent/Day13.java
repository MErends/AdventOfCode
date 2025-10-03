package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <h1>--- Day 13: Claw Contraption ---</h1>
 * <p>Maybe you can win some prizes from the claw machines? The claw machines
 * here are a little unusual. Instead of a joystick or directional buttons to
 * control the claw, these machines have two buttons labeled A and B. Each
 * machine contains one prize; to win the prize, the claw must be positioned
 * exactly above the prize on both the X and Y axes. Figure out how to win as
 * many prizes as possible. What is the fewest tokens you would have to spend to
 * win all possible prizes?</p>
 * <p><a href="https://adventofcode.com/2024/day/13">2024 Day 13</a></p>
 */
public class Day13 extends AbstractProblem<List<String>, Long> {

    final Pattern p = Pattern.compile("\\D*(\\d+)\\D*(\\d+)");

    void main() {
        new Day13().setAndSolve(Util.readInput(2024, 13, 1));
    }

    @Override
    protected Long solve1() {
        long totalTokens = 0;
        for (int i = 0; i < input.size(); i += 4) {
            Matcher m = p.matcher(input.get(i));
            if (!m.find()) throw new IllegalArgumentException();
            int aX = Integer.parseInt(m.group(1));
            int aY = Integer.parseInt(m.group(2));
            m = p.matcher(input.get(i + 1));
            if (!m.find()) throw new IllegalArgumentException();
            int bX = Integer.parseInt(m.group(1));
            int bY = Integer.parseInt(m.group(2));
            m = p.matcher(input.get(i + 2));
            if (!m.find()) throw new IllegalArgumentException();
            int prizeX = Integer.parseInt(m.group(1));
            int prizeY = Integer.parseInt(m.group(2));
            totalTokens += getTokens(aX, aY, bX, bY, prizeX, prizeY);
        }
        return totalTokens;
    }
        
     long getTokens(long aX, long aY, long bX, long bY, long prizeX, long prizeY) {
        if (part2) {
            prizeX += 10000000000000L;
            prizeY += 10000000000000L;
        }
        long lowerA = 0;
        long higherA = Math.min(prizeX / aX, prizeY / aY);
        double bRatio = (double) bY / bX;
        double prizeRatio = (double) prizeY / prizeX;
        boolean bOvershoots = bRatio > prizeRatio;
        long guessA = 0;
        while (lowerA <= higherA) {
            guessA = (higherA + lowerA) / 2;
            long newPrizeX = prizeX - aX * guessA;
            long newPrizeY = prizeY - aY * guessA;
            double newRatio = (double) newPrizeY / newPrizeX;
            if (bRatio == newRatio) {
                break;
            }
            if ((bOvershoots && bRatio > newRatio) || (!bOvershoots && bRatio < newRatio)) {
                lowerA = guessA + 1;
            } else {
                higherA = guessA - 1;
            }
        }
        long guessB = (prizeX - aX * guessA) / bX;
        if (aX * guessA + bX * guessB == prizeX
                && aY * guessA + bY * guessB == prizeY)  {
            return guessA * 3 + guessB;
        }
        return 0;
     }
}
