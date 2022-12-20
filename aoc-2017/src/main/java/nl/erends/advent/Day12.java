package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Day12 extends AbstractProblem<List<String>, Integer> {

    private final Map<Integer, Program> programs = new HashMap<>();

    public static void main(String[] args) {
        new Day12().setAndSolve(Util.readInput(2017, 12));
    }

    @Override
    public Integer solve1() {
        for (String nodeLine : input) {
            Program program = new Program(nodeLine);
            programs.put(program.id, program);
        }
        Program programZero = programs.get(0);
        programZero.groupid = 0;
        programZero.cascadeGroupid();
        int groupZero = 0;
        for (Program program : programs.values()) {
            if (program.groupid == 0) {
                groupZero++;
            }
        }
        return groupZero;
    }

    @Override
    public Integer solve2() {
        Set<Integer> uniqueGroups = new HashSet<>();
        for (int index = 0; index < programs.size(); index++) {
            Program program = programs.get(index);
            if (program.groupid == -1) {
                program.groupid = program.id;
                program.cascadeGroupid();
            }
            uniqueGroups.add(program.groupid);
        }
        return uniqueGroups.size();
    }

    private class Program {
        
        final int id;
        int groupid;
        final int[] linked;

        Program(String input) {
            //6 <-> 107, 136, 366, 1148, 1875
            this.id = Integer.parseInt(input.substring(0, input.indexOf("<->")).trim());
            String[] linkedNodes = input.substring(input.indexOf("<->") + 3).split(",");
            this.linked = new int[linkedNodes.length];
            for (int index = 0; index < linkedNodes.length; index++) {
                linked[index] = Integer.parseInt(linkedNodes[index].trim());
            }
            groupid = -1;
        }

        boolean isLinked() {
            return groupid != -1;
        }

        void cascadeGroupid() {
            for (int linkedProgramid : linked) {
                Program program = programs.get(linkedProgramid);
                if (!program.isLinked()) {
                    program.groupid = this.groupid;
                    program.cascadeGroupid();
                }
            }
        }
    }
}
