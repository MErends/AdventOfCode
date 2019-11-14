package nl.erends.advent.year2017;

import nl.erends.advent.util.AbstractProblem;

public class Day25 extends AbstractProblem<String, Integer> {
    
    private int[] tape = {0};
    private int position = 0;
    private char state = 'A';
    
    public static void main(String[] args) {
        new Day25().setAndSolve(null);
    }
    
    @Override
    public Integer solve1() {
        int maxSteps = 12425180;
        for (int step = 0; step < maxSteps; step++) {
            takeStep();
        }
        int checksum = 0;
        for (int c : tape) {
            if (c == 1) {
                checksum++;
            }
        }
        return checksum;
    }
    
    private void takeStep() {
        switch (state) {
            case 'A':
                doA();
                break;
            case 'B':
                doB();
                break;
            case 'C':
                doC();
                break;
            case 'D':
                doD();
                break;
            case 'E':
                doE();
                break;
            case 'F':
                doF();
                break;
            default:
        }
    }

    private void doA() {
        if (tape[position] == 0) {
            tape[position] = 1;
            goRight();
            state = 'B';
        } else {
            tape[position] = 0;
            goRight();
            state = 'F';
        }
    }

    private void doB() {
        if (tape[position] == 0) {
            tape[position] = 0;
            goLeft();
            state = 'B';
        } else {
            tape[position] = 1;
            goLeft();
            state = 'C';
        }
    }

    private void doC() {
        if (tape[position] == 0) {
            tape[position] = 1;
            goLeft();
            state = 'D';
        } else {
            tape[position] = 0;
            goRight();
            state = 'C';
        }
    }

    private void doD() {
        if (tape[position] == 0) {
            tape[position] = 1;
            goLeft();
            state = 'E';
        } else {
            tape[position] = 1;
            goRight();
            state = 'A';
        }
    }

    private void doE() {
        if (tape[position] == 0) {
            tape[position] = 1;
            goLeft();
            state = 'F';
        } else {
            tape[position] = 0;
            goLeft();
            state = 'D';
        }
    }

    private void doF() {
        if (tape[position] == 0) {
            tape[position] = 1;
            goRight();
            state = 'A';
        } else {
            tape[position] = 0;
            goLeft();
            state = 'E';
        }
    }

    private void goLeft() {
        if (position == 0) {
            addToFront();
        } else {
            position--;
        }
    }
    
    private void goRight() {
        if (position == tape.length - 1) {
            addToEnd();
        }
        position++;
    }
    
    private void addToFront() {
        int[] target = new int[tape.length + 1];
        System.arraycopy(tape, 0, target, 1, tape.length);
        tape = target;
    }

    private void addToEnd() {
        int[] target = new int[tape.length + 1];
        System.arraycopy(tape, 0, target, 0, tape.length);
        tape = target;
    }
}