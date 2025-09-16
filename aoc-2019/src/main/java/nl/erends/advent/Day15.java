package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

public class Day15 extends AbstractProblem<String, Integer> {
    
    private Set<String> placesBeen;
    private Queue<State> states;
    private int stepsToSystem;
    private static final int NORTH = 1;
    private static final int SOUTH = 2;
    private static final int WEST = 3;
    private static final int EAST = 4;
    private State oxygenState;

    static void main() {
        new Day15().setAndSolve(Util.readLine(2019, 15));
    }

    @Override
    public Integer solve1() {
        answer2 = 0;
        placesBeen = new HashSet<>();
        states = new LinkedList<>();
        stepsToSystem = -1;
        Intcode droid = new Intcode(input);
        State state = new State(0, 0, 0, droid, 1);
        states.add(state);
        while (stepsToSystem == -1) {
            states.remove().explore();
        }
        return stepsToSystem;
    }

    @Override
    public Integer solve2() {
        if (oxygenState == null) {
            solve1();
        }
        states = new LinkedList<>();
        placesBeen = new HashSet<>();
        oxygenState.steps = 0;
        oxygenState.output = 1;
        states.add(oxygenState);
        while (!states.isEmpty()) {
            states.remove().explore();
        }
        return answer2;
    }

    private class State {
        final int x;
        final int y;
        int steps;
        final Intcode droid;
        int output;

        State(int x, int y, int steps, Intcode droid, int output) {
            this.x = x;
            this.y = y;
            this.steps = steps;
            this.droid = droid;
            this.output = output;
        }
        
        private void explore() {
            placesBeen.add(x + "," + y);
            if (output == 2) {
                oxygenState = this;
                stepsToSystem = steps;
                return;
            } else if (output == 0) {
                return;
            }
            answer2 = steps;
            if (!placesBeen.contains((x+1) + "," + y)) { // go up
                Intcode newDroid = new Intcode(droid);
                newDroid.addInput(NORTH);
                newDroid.execute();
                State newState = new State(x + 1, y, steps + 1, newDroid, newDroid.getOutput().intValue());
                states.add(newState);
            }
            if (!placesBeen.contains((x-1) + "," + y)) { // go down
                Intcode newDroid = new Intcode(droid);
                newDroid.addInput(SOUTH);
                newDroid.execute();
                State newState = new State(x - 1, y, steps + 1, newDroid, newDroid.getOutput().intValue());
                states.add(newState);
            }
            if (!placesBeen.contains(x + "," + (y-1))) { // go left
                Intcode newDroid = new Intcode(droid);
                newDroid.addInput(WEST);
                newDroid.execute();
                State newState = new State(x, y - 1, steps + 1, newDroid, newDroid.getOutput().intValue());
                states.add(newState);
            }
            if (!placesBeen.contains(x + "," + (y+1))) { // go right
                Intcode newDroid = new Intcode(droid);
                newDroid.addInput(EAST);
                newDroid.execute();
                State newState = new State(x, y + 1, steps + 1, newDroid, newDroid.getOutput().intValue());
                states.add(newState);
            }
        }
    }
}
