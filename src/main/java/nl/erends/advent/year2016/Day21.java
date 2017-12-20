package nl.erends.advent.year2016;

import nl.erends.advent.util.FileIO;

import java.util.Collections;
import java.util.List;

public class Day21 {
    
    private static StringBuilder password = new StringBuilder("abcdefgh");
    
    public static void main(String[] args) {
        List<String> commands = FileIO.getFileAsList("2016day21.txt");
        for (String command : commands) {
            String[] words = command.split(" ");
            switch (words[0]) {
                case "swap":
                    if (words[1].equals("position")) {
                        swapPositions(Integer.parseInt(words[2]), Integer.parseInt(words[5]));
                    } else {
                        swapLetter(words[2].charAt(0), words[5].charAt(0));
                    }
                    break;
                case "rotate":
                    switch (words[1]) {
                        case "left":
                            rotateLeft(Integer.parseInt(words[2]));
                            break;
                        case "right":
                            rotateRight(Integer.parseInt(words[2]));
                            break;
                        default:
                            rotateIndex(words[6].charAt(0));
                            break;
                    }
                    break;
                case "reverse":
                    reversePositions(Integer.parseInt(words[2]), Integer.parseInt(words[4]));
                    break;
                default:
                    movePosition(Integer.parseInt(words[2]), Integer.parseInt(words[5]));
                    break;
            }
        }
        System.out.println(password);
        
        password = new StringBuilder("fbgdceah");
        Collections.reverse(commands);
        for (String command : commands) {
            String[] words = command.split(" ");
            switch (words[0]) {
                case "swap":
                    if (words[1].equals("position")) {
                        swapPositions(Integer.parseInt(words[2]), Integer.parseInt(words[5]));
                    } else {
                        swapLetter(words[2].charAt(0), words[5].charAt(0));
                    }
                    break;
                case "rotate":
                    switch (words[1]) {
                        case "right":
                            rotateLeft(Integer.parseInt(words[2]));
                            break;
                        case "left":
                            rotateRight(Integer.parseInt(words[2]));
                            break;
                        default:
                            unrotateIndex(words[6].charAt(0));
                            break;
                    }
                    break;
                case "reverse":
                    reversePositions(Integer.parseInt(words[2]), Integer.parseInt(words[4]));
                    break;
                default:
                    movePosition(Integer.parseInt(words[5]), Integer.parseInt(words[2]));
                    break;
            }
        }
        System.out.println(password);
    }
    
    
    
    private static void swapPositions(int a, int b) {
        char temp = password.charAt(a);
        password.setCharAt(a, password.charAt(b));
        password.setCharAt(b, temp);
    }
    
    private static void swapLetter(char a, char b) {
        int indexOfA = password.indexOf("" + a);
        password.setCharAt(password.indexOf("" + b), a);
        password.setCharAt(indexOfA, b);
    }
    
    private static void rotateLeft(int steps) {
        steps %= password.length();
        String a = password.substring(0, steps);
        String b = password.substring(steps);
        password = new StringBuilder(b + a);
    }
    
    private static void rotateRight(int steps) {
        password.reverse();
        rotateLeft(steps);
        password.reverse();
    }
    
    private static void rotateIndex(char a) {
        int index = password.indexOf("" + a);
        if (index > 3) index++;
        rotateRight(index + 1);
    }
    
    private static void unrotateIndex(char a) {
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
    
    private static void reversePositions(int a, int b) {
        StringBuilder newPassword = new StringBuilder(password.substring(0, a));
        StringBuilder midsection = new StringBuilder(password.substring(a, b + 1));
        newPassword.append(midsection.reverse()).append(password.substring(b + 1));
        password = newPassword;
    }
    
    private static void movePosition(int a, int b) {
        char c = password.charAt(a);
        password.deleteCharAt(a);
        password.insert(b, c);
    }
}
