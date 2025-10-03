package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Day06 extends AbstractProblem<List<String>, Integer> {

    void main() {
        new Day06().setAndSolve(Util.readInput(2019, 6));
    }

    @Override
    public Integer solve1() {
        List<String> inputList = new ArrayList<>(input);
        Map<String, Integer> orbitmap = new HashMap<>();
        orbitmap.put("COM", 0);
        while (!inputList.isEmpty()) {
            Iterator<String> iterator = inputList.iterator();
            while (iterator.hasNext()) {
                String line = iterator.next();
                String[] words = line.split("\\)");
                String parent = words[0];
                String child = words[1];
                if (orbitmap.containsKey(parent)) {
                    orbitmap.put(child, orbitmap.get(parent) + 1);
                    iterator.remove();
                }
            }
        }
        return orbitmap.values().stream().mapToInt(Integer::intValue).sum();
    }

    @Override
    public Integer solve2() {
        Map<String, Integer> transfersYou = getTransfersYou();
        String currentChild = "SAN";
        int transfers = -1;
        while (true) {
            for (String line : input) {
                String[] words = line.split("\\)");
                String parent = words[0];
                String child = words[1];
                if (currentChild.equals(child)) {
                    transfers++;
                    if (transfersYou.containsKey(parent)) {
                        return transfers + transfersYou.get(parent);
                    }
                    currentChild = parent;
                    break;
                }
            }
        }
    }

    private Map<String, Integer> getTransfersYou() {
        Map<String, Integer> transfersYou = new HashMap<>();
        String currentChild = "YOU";
        int transfers = -1;
        while (!currentChild.equals("COM")) {
            for (String line : input) {
                String[] words = line.split("\\)");
                String parent = words[0];
                String child = words[1];
                if (currentChild.equals(child)) {
                    transfers++;
                    transfersYou.put(parent, transfers);
                    currentChild = parent;
                    break;
                }
            }
        }
        return transfersYou;
    }
}
