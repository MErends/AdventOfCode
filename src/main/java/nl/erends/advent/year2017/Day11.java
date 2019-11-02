package nl.erends.advent.year2017;

import nl.erends.advent.util.Util;

public class Day11 {


	public static void main(String[] args) {
		String line = Util.getFileAsString("2017day11.txt");
		String[] words = line.split(",");
		int n = 0;
		int ne = 0;
		int se = 0;
		int s = 0;
		int sw = 0;
		int nw = 0;
		int steps = 0;
		int maxSteps = 0;
		for (String direction : words) {
			switch (direction) {
				case "n":
					n++;
					break;
				case "ne":
					ne++;
					break;
				case "se":
					se++;
					break;
				case "s":
					s++;
					break;
				case "sw":
					sw++;
					break;
				case "nw":
					nw++;
					break;
			}
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
			steps = n + ne + nw + sw + se + s;
			maxSteps = Math.max(steps, maxSteps);
		}
		System.out.println(steps + "\n" + maxSteps);
	}

}