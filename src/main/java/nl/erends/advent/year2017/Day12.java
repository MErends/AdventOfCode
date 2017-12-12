package nl.erends.advent.year2017;


import nl.erends.advent.util.FileIO;

import java.util.*;

public class Day12 {


    public static Map<Integer, Program> programs = new HashMap<>();

    public static void main(String[] args) {
        List<String> input = FileIO.getFileAsList("2017day12.txt");
        for (String nodeLine : input) {
            Program program = new Program(nodeLine);
            programs.put(program.getId(), program);
        }
        Program programZero = programs.get(0);
        programZero.setGroupid(0);
        programZero.cascadeGroupid();
        int groupZero = 0;
        for (Program program : programs.values()) {
            if (program.getGroupid() == 0) {
                groupZero++;
            }
        }
        System.out.println(groupZero);

        Set<Integer> uniqueGroups = new HashSet<>();
        for (int index = 0; index < programs.size(); index++) {
            Program program = programs.get(index);
            if (program.getGroupid() == -1) {
                program.setGroupid(program.getId());
                program.cascadeGroupid();
            }
            uniqueGroups.add(program.getGroupid());
        }
        System.out.println(uniqueGroups.size());
    }

}

class Program {
    int id;
    int groupid;
    int[] linked;

    public Program(String input) {
        //6 <-> 107, 136, 366, 1148, 1875
        this.id = Integer.parseInt(input.substring(0, input.indexOf("<->")).trim());
        String[] linkedNodes = input.substring(input.indexOf("<->") + 3).split(",");
        this.linked = new int[linkedNodes.length];
        for (int index = 0; index < linkedNodes.length; index++) {
            linked[index] = Integer.parseInt(linkedNodes[index].trim());
        }
        groupid = -1;
    }

    public int getId() {
        return id;
    }

    public int getGroupid() {
        return groupid;
    }

    public int[] getLinked() {
        return linked;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public void setLinked(int[] linked) {
        this.linked = linked;
    }

    public boolean isLinked() {
        return groupid != -1;
    }

    public void cascadeGroupid() {
        for (int linkedProgramid : linked) {
            Program program = Day12.programs.get(linkedProgramid);
            if (!program.isLinked()) {
                program.setGroupid(this.groupid);
                program.cascadeGroupid();
            }
        }
    }
}