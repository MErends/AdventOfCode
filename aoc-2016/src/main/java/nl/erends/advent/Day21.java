package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day21 extends AbstractProblem<List<String>, String> {
    
    private String input1 = "abcdefgh";
    private String input2 = "fbgdceah";
    
    public static void main(String[] args) {
        new Day21().setAndSolve(Util.readInput(2016, 21));
    }
    
    @Override
    public String solve1() {
        PasswordTool passwordTool = new PasswordTool(input);
        return passwordTool.scramble(input1);
    }
    
    @Override
    public String solve2() {
        PasswordTool passwordTool = new PasswordTool(input);
        return passwordTool.unscramble(input2);
    }

    public void setInput1(String input1) {
        this.input1 = input1;
    }

    public void setInput2(String input2) {
        this.input2 = input2;
    }

    private static class PasswordTool {

        private final List<String> instructions;
        private StringBuilder password;

        PasswordTool(List<String> instructions) {
            this.instructions = instructions;
        }

        String scramble(String input) {
            password = new StringBuilder(input);
            for (String instruction : instructions) {
                String[] words = instruction.split(" ");
                if (instruction.startsWith("swap position")) {
                    swapPositions(Integer.parseInt(words[2]), Integer.parseInt(words[5]));
                } else if (instruction.startsWith("swap letter")) {
                    swapLetter(words[2], words[5]);
                } else if (instruction.startsWith("rotate based")) {
                    rotateIndex(words[6]);
                } else if (instruction.startsWith("rotate left")) {
                    rotateLeft(Integer.parseInt(words[2]));
                } else if (instruction.startsWith("rotate right")) {
                    rotateRight(Integer.parseInt(words[2]));
                } else if (instruction.startsWith("reverse")) {
                    reversePositions(Integer.parseInt(words[2]), Integer.parseInt(words[4]));
                } else {
                    movePosition(Integer.parseInt(words[2]), Integer.parseInt(words[5]));
                }
            }
            return password.toString();
        }

        String unscramble(String input) {
            password = new StringBuilder(input);
            List<String> reverseInstructions = new ArrayList<>(instructions);
            reverseInstructions = reverseInstructions.reversed();
            for (String instruction : reverseInstructions) {
                String[] words = instruction.split(" ");
                if (instruction.startsWith("swap position")) {
                    swapPositions(Integer.parseInt(words[2]), Integer.parseInt(words[5]));
                } else if (instruction.startsWith("swap letter")) {
                    swapLetter(words[2], words[5]);
                } else if (instruction.startsWith("rotate based")) {
                    unrotateIndex(words[6]);
                } else if (instruction.startsWith("rotate left")) {
                    rotateRight(Integer.parseInt(words[2]));
                } else if (instruction.startsWith("rotate right")) {
                    rotateLeft(Integer.parseInt(words[2]));
                } else if (instruction.startsWith("reverse")) {
                    reversePositions(Integer.parseInt(words[2]), Integer.parseInt(words[4]));
                } else {
                    movePosition(Integer.parseInt(words[5]), Integer.parseInt(words[2]));
                }
            }
            return password.toString();
        }

        private void swapPositions(int a, int b) {
            char temp = password.charAt(a);
            password.setCharAt(a, password.charAt(b));
            password.setCharAt(b, temp);
        }

        private void swapLetter(String a, String b) {
            swapPositions(password.indexOf(a), password.indexOf(b));
        }

        private void rotateLeft(int steps) {
            steps %= password.length();
            String a = password.substring(0, steps);
            String b = password.substring(steps);
            password = new StringBuilder(b).append(a);
        }

        private void rotateRight(int steps) {
            password.reverse();
            rotateLeft(steps);
            password.reverse();
        }

        private void rotateIndex(String a) {
            int index = password.indexOf(a);
            if (index >= 4) index++;
            rotateRight(index + 1);
        }

        private void unrotateIndex(String a) {
            String target = password.toString();
            int timesRotated = 0;
            while (true) {
                rotateLeft(timesRotated);
                rotateIndex(a);
                if (password.toString().equals(target)) {
                    break;
                } else {
                    password = new StringBuilder(target);
                    timesRotated++;
                }
            }
            rotateLeft(timesRotated);
        }

        private void reversePositions(int a, int b) {
            StringBuilder newPassword = new StringBuilder(password.substring(0, a));
            StringBuilder midsection = new StringBuilder(password.substring(a, b + 1));
            newPassword.append(midsection.reverse()).append(password.substring(b + 1));
            password = newPassword;
        }

        private void movePosition(int a, int b) {
            char c = password.charAt(a);
            password.deleteCharAt(a);
            password.insert(b, c);
        }
    }
}
