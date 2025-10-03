package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.List;

/**
 * --- Day 18: Snailfish  ---
 * <p>You descend into the ocean trench and encounter some snailfish. They say
 * they saw the sleigh keys! They'll even tell you which direction the keys went
 * if you help one of the smaller snailfish with his math homework.
 * <p><a href="https://adventofcode.com/2021/day/18">2021 Day 18</a>
 */
public class Day18 extends AbstractProblem<List<String>,Integer> {
    
    void main() {
        new Day18().setAndSolve(Util.readInput(2021, 18));
    }

    @Override
    protected Integer solve1() {
        SnailNumber number = new SnailNumber(input.getFirst(), 0, null);
        for (int index = 1; index < input.size(); index++) {
            number = new SnailNumber(number.toString(), input.get(index));
            number.reduce();
        }
        return number.getMagnitude();
    }

    @Override
    public Integer solve2() {
        int maxMagnitude = Integer.MIN_VALUE;
        for (int index1 = 0; index1 < input.size(); index1++) {
            for (int index2 = 0; index2 < input.size(); index2++) {
                if (index1 == index2) {
                    continue;
                }
                SnailNumber number = new SnailNumber(input.get(index1), input.get(index2));
                number.reduce();
                maxMagnitude = Integer.max(number.getMagnitude(), maxMagnitude);
            }
        }
        return maxMagnitude;
    }

    private static class SnailNumber {
        
        SnailNumber left;
        SnailNumber right;
        boolean isRegular;
        int value;
        final int nest;
        final SnailNumber parent;
        
        SnailNumber(String number1, String number2) {
            this('[' + number1 + ',' + number2 + ']', 0, null);
        }
        
        private SnailNumber(String text, int nest, SnailNumber parent) {
            this.nest = nest;
            this.parent = parent;
            if (text.indexOf(',') == -1) {
                isRegular = true;
                value = Integer.parseInt(text);
                return;
            }
            int commaIndex = 1;
            int braceNest = 0;
            for ( ; commaIndex < text.length(); commaIndex++) {
                if (text.charAt(commaIndex) == '[') {
                    braceNest++;
                } else if (text.charAt(commaIndex) == ']') {
                    braceNest--;
                } else if (text.charAt(commaIndex) == ',' && braceNest == 0) {
                    break;
                }
            }
            left = new SnailNumber(text.substring(1, commaIndex), nest + 1, this);
            right = new SnailNumber(text.substring(commaIndex + 1, text.length() - 1), nest + 1, this);
        }
        
        void reduce() {
            boolean exploded = explode();
            if (exploded) {
                reduce();
            } else {
                boolean split = split();
                if (split) {
                    reduce();
                }
            }
        }
        
        int getMagnitude() {
            if (isRegular) {
                return value;
            }
            return 3 * left.getMagnitude() + 2 * right.getMagnitude();
        }
        
        private boolean explode() {
            if (isRegular) {
                return false;
            }
            if (nest >= 4) {
                SnailNumber current = this;
                SnailNumber currentParent = current.parent;
                while (currentParent != null && currentParent.right != current) {
                    current = currentParent;
                    currentParent = current.parent;
                }
                if (currentParent != null) {
                    SnailNumber numberToLeft = currentParent.left.getRightmost();
                    numberToLeft.value += this.left.value;
                }
                current = this;
                currentParent = this.parent;
                while (currentParent != null && currentParent.left != current) {
                    current = currentParent;
                    currentParent = current.parent;
                }
                if (currentParent != null) {
                    SnailNumber numberToRight = currentParent.right.getLeftmost();
                    numberToRight.value += this.right.value;
                }
                left = null;
                right = null;
                isRegular = true;
                value = 0;
                return true;
            }
            return left.explode() || right.explode();
        }
        
        private boolean split() {
            if (!isRegular) {
                return left.split() || right.split();
            }
            if (value < 10) {
                return false;
            }
            int leftValue = value / 2;
            int rightValue = value - leftValue;
            isRegular = false;
            left = new SnailNumber(Integer.toString(leftValue), nest + 1, this);
            right = new SnailNumber(Integer.toString(rightValue), nest + 1, this);
            return true;
        }
        
        private SnailNumber getRightmost() {
            if (isRegular) {
                return this;
            }
            return right.getRightmost();
        }

        private SnailNumber getLeftmost() {
            if (isRegular) {
                return this;
            }
            return left.getLeftmost();
        }

        @Override
        public String toString() {
            if (isRegular) {
                return Integer.toString(value);
            }
            return "[" + left + "," + right + "]";
        }
    }
}
