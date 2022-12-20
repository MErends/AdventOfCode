package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;

public class Day04 extends AbstractProblem<String, Integer> {

    public static void main(String[] args) {
        new Day04().setAndSolve("357253-892942");
    }

    @Override
    public Integer solve1() {
        int validPasswords = 0;
        int strictValidPasswords = 0;
        String[] range = input.split("-");
        for (int number = Integer.parseInt(range[0]);
             number < Integer.parseInt(range[1]);
             number++) {
            String numberString = Integer.toString(number);
            if (everIncreases(numberString) && hasDoubleDigit(numberString)) {
                validPasswords++;
                if (hasStrictDoubleDigit(numberString)) {
                    strictValidPasswords++;
                }
            }
        }
        answer2 = strictValidPasswords;
        return validPasswords;
    }

    
    private boolean hasDoubleDigit(String number) {
        for (int pointer = 0; pointer < number.length() - 1; pointer++) {
            if (number.charAt(pointer) == number.charAt(pointer + 1)) {
                return true;
            }
        }
        return false;
    }
    
    private boolean hasStrictDoubleDigit(String number) {
        for (int pointer = 0; pointer < number.length() - 1; pointer++) {
            if (number.charAt(pointer) == number.charAt(pointer + 1)
                    && (pointer + 2 == number.length() || number.charAt(pointer) != number.charAt(pointer + 2))
                    && (pointer == 0                   || number.charAt(pointer) != number.charAt(pointer - 1))) {
                return true;
            }
        }
        return false;
    }
    
    private boolean everIncreases(String number) {
        for (int pointer = 0; pointer < number.length() - 1; pointer++) {
            if (number.charAt(pointer) > number.charAt(pointer + 1)) {
                return false;
            }
        }
        return true;
    }
}
