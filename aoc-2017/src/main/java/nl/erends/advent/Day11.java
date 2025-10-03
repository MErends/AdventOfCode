package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

public class Day11 extends AbstractProblem<String, Integer> {

    private int n = 0;
    private int ne = 0;
    private int se = 0;
    private int s = 0;
    private int sw = 0;
    private int nw = 0;

	void main() {
        new Day11().setAndSolve(Util.readLine(2017, 11));
    }
    
    @Override
    public Integer solve1() {
		String[] words = input.split(",");
		int steps = 0;
		int maxSteps = 0;
		for (String direction : words) {
            updateDirection(direction);
            resolveOpposites();
            combineDirections();
			steps = n + ne + nw + sw + se + s;
			maxSteps = Math.max(steps, maxSteps);
		}
		answer2 = maxSteps;
		return steps;
	}

    private void combineDirections() {
        if (n > 0 && se > 0) {
            int save = Math.min(n, se);
            n -= save;
            se -= save;
            ne += save;
        }
        if (ne > 0 && s > 0) {
            int save = Math.min(ne, s);
            ne -= save;
            s -= save;
            se += save;
        }
        if (se > 0 && sw > 0) {
            int save = Math.min(se, sw);
            se -= save;
            sw -= save;
            s += save;
        }
        if (s > 0 && nw > 0) {
            int save = Math.min(s, nw);
            s -= save;
            nw -= save;
            sw += save;
        }
        if (sw > 0 && n > 0) {
            int save = Math.min(sw, n);
            sw -= save;
            n -= save;
            nw += save;
        }
        if (nw > 0 && ne > 0) {
            int save = Math.min(nw, ne);
            nw -= save;
            ne -= save;
            n += save;
        }
    }

    private void resolveOpposites() {
        if (n > s) {
            n -= s;
            s = 0;
        } else {
            s -= n;
            n = 0;
        }
        if (ne > sw) {
            ne -= sw;
            sw = 0;
        } else {
            sw -= ne;
            ne = 0;
        }
        if (se > nw) {
            se -= nw;
            nw = 0;
        } else {
            nw -= se;
            se = 0;
        }
    }

    private void updateDirection(String direction) {
        switch (direction) {
            case "n" -> n++;
            case "ne" -> ne++;
            case "se" -> se++;
            case "s" -> s++;
            case "sw" -> sw++;
            case "nw" -> nw++;
            default -> throw new IllegalArgumentException(direction);
        }
    }
}
