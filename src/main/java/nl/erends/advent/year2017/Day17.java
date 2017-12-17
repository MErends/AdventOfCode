package nl.erends.advent.year2017;

import nl.erends.advent.util.FileIO;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Day17 {

    public static void main(String[] args) {

        List<Integer> memory = new ArrayList<>();
        memory.add(0);
        int position = 0;
        int increment = 343;
        int insert = 1;
        for ( ; insert < 2018; insert++) {
            position = (position + increment) % memory.size();
            memory.add(position + 1, insert);
            position++;
        }
        for (int index = 0; index < memory.size(); index++) {
            if (memory.get(index) == 2017) {
                System.out.println(memory.get(index + 1));
                break;
            }
        }
        int valueAfterZero = memory.get(1);
        for ( ; insert < 50_000_001; insert++) {
            position = (position + increment) % insert;
            if (position == 0) {
                valueAfterZero = insert;
            }
            position++;
        }
        System.out.println(valueAfterZero);
    }


}
