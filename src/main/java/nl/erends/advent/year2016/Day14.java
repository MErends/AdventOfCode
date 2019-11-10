package nl.erends.advent.year2016;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.MD5;

import java.util.ArrayList;
import java.util.List;

public class Day14 extends AbstractProblem<String, Integer> {
    
    private List<String> hashList = new ArrayList<>();
    private int hashLoops = 1;

    public static void main(String[] args) {
        new Day14().setAndSolve("jlmsuwbz");
    }
    
    @Override
    public Integer solve1() {
        int keysFound = 0;
        int nonce = -1;
        while(keysFound < 64) {
            nonce++;
            String hash = getHash(nonce);
            char triple = findTripleChar(hash);
            if (triple != '\0') {
                String five = new String(new char[]{triple, triple, triple, triple, triple});
                for (int index = 1; index <= 1000; index++) {
                    if (getHash(nonce + index).contains(five)) {
                        keysFound++;
                        break;
                    }
                }
            }
        }
        return nonce;
    }
    
    @Override
    public Integer solve2() {
        hashList = new ArrayList<>();
        hashLoops = 2017;
        return solve1();
    }

    private String getHash(int nonce) {
        if (nonce == hashList.size()) {
            String hash = input + nonce;
            for (int i = 0; i < hashLoops; i++) {
                hash = MD5.getHash(hash);
            }
            hashList.add(hash);
        }
        return hashList.get(nonce);
    }
    
    private char findTripleChar(String input) {
        char[] hash = input.toCharArray();
        for (int index = 0; index < hash.length - 2; index++) {
            if (hash[index] == hash[index + 1] && hash[index] == hash[index + 2]) {
                return hash[index];
            }
        }
        return '\0';
    }
}
