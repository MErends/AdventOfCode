package nl.erends.advent.year2017;

public class Day25 {
    
    public static int[] tape = {0};
    public static int position = 0;
    public static char state = 'A';
    public static void main(String[] args) {
    
        int maxSteps = 12425180;
        for (int step = 0; step < maxSteps; step++) {
            try {
                takeStep();
            } catch (Exception e) {
                System.out.println("error in step " + step);
                e.printStackTrace();
                throw new RuntimeException();
            }
        }
        int checksum = 0;
        for (int c : tape) {
            if (c == 1) {
                checksum++;
            }
        }
        System.out.println(checksum);
    }
    
    private static void takeStep() {
        switch (state) {
            case 'A':
                if (tape[position] == 0) {
                    tape[position] = 1;
                    goRight();
                    state = 'B';
                } else {
                    tape[position] = 0;
                    goRight();
                    state = 'F';
                }
                break;
            case 'B':
                if (tape[position] == 0) {
                    tape[position] = 0;
                    goLeft();
                    state = 'B';
                } else {
                    tape[position] = 1;
                    goLeft();
                    state = 'C';
                }
                break;
            case 'C':
                if (tape[position] == 0) {
                    tape[position] = 1;
                    goLeft();
                    state = 'D';
                } else {
                    tape[position] = 0;
                    goRight();
                    state = 'C';
                }
                break;
            case 'D':
                if (tape[position] == 0) {
                    tape[position] = 1;
                    goLeft();
                    state = 'E';
                } else {
                    tape[position] = 1;
                    goRight();
                    state = 'A';
                }
                break;
            case 'E':
                if (tape[position] == 0) {
                    tape[position] = 1;
                    goLeft();
                    state = 'F';
                } else {
                    tape[position] = 0;
                    goLeft();
                    state = 'D';
                }
                break;
            case 'F':
                if (tape[position] == 0) {
                    tape[position] = 1;
                    goRight();
                    state = 'A';
                } else {
                    tape[position] = 0;
                    goLeft();
                    state = 'E';
                }
                break;
        }
    }
    
    private static void printTape() {
        for (int c : tape) {
            System.out.print(c);
        }
        System.out.println();
    }
    
    private static void goLeft() {
        if (position == 0) {
            addToFront();
        } else {
            position--;
        }
    }
    
    private static void goRight() {
        if (position == tape.length - 1) {
            addToEnd();
        }
        position++;
    }
    
    private static void addToFront() {
        int[] target = new int[tape.length + 1];
        System.arraycopy(tape, 0, target, 1, tape.length);
        tape = target;
    }

    private static void addToEnd() {
        int[] target = new int[tape.length + 1];
        System.arraycopy(tape, 0, target, 0, tape.length);
        tape = target;
    }
}