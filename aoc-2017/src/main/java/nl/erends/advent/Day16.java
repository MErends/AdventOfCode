package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.List;

public class Day16 extends AbstractProblem<String, String> {
    
    private String[] commands;

    static void main() {
        new Day16().setAndSolve(Util.readLine(2017, 16));
    }
    
    @Override
    public String solve1() {
        commands = input.split(",");
        List<String> history = new ArrayList<>();
        String programs = "abcdefghijklmnop";
        history.add(programs);
        programs = dance(programs);
        String answer1 = programs;
        history.add(programs);
        while (true) {
            programs = dance(programs);
            if (history.contains(programs)) {
                break;
            } else {
                history.add(programs);
            }
        }
        answer2 = history.get(1_000_000_000 % history.size());
        return answer1;
    }

    private String dance(String input) {
        StringBuilder programs = new StringBuilder(input);
        for (String command : commands) {
            if (command.startsWith("s")) {
                int spinAmount = Integer.parseInt(command.substring(1));
                programs.reverse();
                programs = new StringBuilder(programs.substring(spinAmount) + programs.substring(0, spinAmount));
                programs.reverse();
            } else if (command.startsWith("x")) {
                int positionA = Integer.parseInt(command.substring(1).split("/")[0]);
                int positionB = Integer.parseInt(command.substring(1).split("/")[1]);
                char charA = programs.charAt(positionA);
                programs.setCharAt(positionA, programs.charAt(positionB));
                programs.setCharAt(positionB, charA);
            } else {
                int positionA = programs.indexOf(command.charAt(1) + "");
                int positionB = programs.indexOf(command.charAt(3) + "");
                programs.setCharAt(positionA, command.charAt(3));
                programs.setCharAt(positionB, command.charAt(1));
            }
        }
        return programs.toString();
    }
}
