package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * --- Day 21: Monkey Math ---
 * <p>The monkeys are back! You're worried they're going to try to steal your
 * stuff again, but it seems like they're just holding their ground and making
 * various monkey noises at you. Each monkey is given a job: either to yell a
 * specific number or to yell the result of a math operation. Your job is to
 * work out the number the monkey named root will yell before the monkeys figure
 * it out themselves.
 *<p><a href="https://adventofcode.com/2022/day/21">2022 Day 21</a>
 */
public class Day21 extends AbstractProblem<List<String>, Number> {

    private Map<String, Monkey> monkeyMap;

    public static void main(String[] args) {
        new Day21().setAndSolve(Util.readInput(2022, 21));
    }

    @Override
    protected Number solve1() {
        monkeyMap = new HashMap<>();
        for (String line : input) {
            Monkey monkey = new Monkey(line);
            monkeyMap.put(monkey.name, monkey);
        }
        return monkeyMap.get("root").getValue();
    }

    @Override
    public Number solve2() {
        monkeyMap = new HashMap<>();
        for (String line : input) {
            Monkey monkey = new Monkey(line);
            monkeyMap.put(monkey.name, monkey);
        }
        Monkey root = monkeyMap.get("root");
        root.calculation = root.calculation.replace('+', '-');
        Fraction[] rootFactors = root.getFactors();
        Fraction solveForX = rootFactors[0].div(rootFactors[1]);
        return -1 * solveForX.numerator.divide(solveForX.denominator).longValue();
    }

    private class Monkey {

        String name;
        String calculation;

        Monkey(String line) {
            this.name = line.substring(0, 4);
            this.calculation = line.substring(6);
        }

        long getValue() {
            long value;
            if (calculation.length() < 11) {
                value = Long.parseLong(calculation);
            } else {
                Monkey monkeyA = monkeyMap.get(calculation.substring(0, 4));
                Monkey monkeyB = monkeyMap.get(calculation.substring(7));
                value = switch (calculation.charAt(5)) {
                    case '+' -> monkeyA.getValue() + monkeyB.getValue();
                    case '-' -> monkeyA.getValue() - monkeyB.getValue();
                    case '*' -> monkeyA.getValue() * monkeyB.getValue();
                    default -> monkeyA.getValue() / monkeyB.getValue();
                };
            }
            return value;
        }

        Fraction[] getFactors() {
            Fraction[] factors;
            if ("humn".equals(name)) {
                factors = new Fraction[]{new Fraction(0, 1), new Fraction(1, 1)};
            } else if (calculation.length() < 11) {
                factors = new Fraction[]{new Fraction(Long.parseLong(calculation), 1), new Fraction(0, 1)};
            } else {
                Fraction[] a = monkeyMap.get(calculation.substring(0, 4)).getFactors();
                Fraction[] b = monkeyMap.get(calculation.substring(7)).getFactors();
                factors = switch (calculation.charAt(5)) {
                    case '+' -> new Fraction[]{a[0].plus(b[0]), a[1].plus(b[1])};
                    case '-' -> new Fraction[]{a[0].min(b[0]), a[1].min(b[1])};
                    case '*' -> new Fraction[]{a[0].times(b[0]), (a[0].times(b[1])).plus((a[1].times(b[0])))};
                    default -> new Fraction[]{a[0].div(b[0]), a[1].div(b[0])};
                };
            }
            return factors;
        }
    }

    private static class Fraction {

        private final BigInteger numerator;
        private final BigInteger denominator;

        private Fraction(long numerator, long denominator) {
            this(BigInteger.valueOf(numerator), BigInteger.valueOf(denominator));
        }

        private Fraction(BigInteger teller, BigInteger noemer) {
            this.numerator = teller;
            this.denominator = noemer;
        }
        
        private Fraction plus(Fraction o) {
            BigInteger newNumerator = numerator.multiply(o.denominator).add(denominator.multiply(o.numerator));
            BigInteger newDenominator = denominator.multiply(o.denominator);
            return new Fraction(newNumerator, newDenominator);
        }

        private Fraction min(Fraction o) {
            BigInteger newNumerator = numerator.multiply(o.denominator).subtract(denominator.multiply(o.numerator));
            BigInteger newDenominator = denominator.multiply(o.denominator);
            return new Fraction(newNumerator, newDenominator);
        }

        private Fraction times(Fraction o) {
            BigInteger newNumerator = numerator.multiply(o.numerator);
            BigInteger newDenominator = denominator.multiply(o.denominator);
            return new Fraction(newNumerator, newDenominator);
        }

        private Fraction div(Fraction o) {
            BigInteger newNumerator = numerator.multiply(o.denominator);
            BigInteger newDenominator = denominator.multiply(o.numerator);
            return new Fraction(newNumerator, newDenominator);
        }
    }
}
