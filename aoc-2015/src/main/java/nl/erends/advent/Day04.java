package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.MD5;
import nl.erends.advent.util.Util;

public class Day04 extends AbstractProblem<String, Integer> {

    private int nonce = 0;
    
    void main() {
        new Day04().setAndSolve(Util.readLine(2015, 4));
    }

    @Override
    public Integer solve1() {
        while(true) {
            String hash = MD5.getHash(input + nonce);
            if (hash.startsWith("00000")) {
                return nonce;
            }
            nonce++;
        }
    }

    @Override
    public Integer solve2() {
        if (nonce == 0) {
            solve1();
        }
        while(true) {
            String hash = MD5.getHash(input + nonce);
            if (hash.startsWith("000000")) {
                return nonce;
            }
            nonce++;
        }
    }

    public void setStartNonce(int nonce) {
        this.nonce = nonce;
    }
}
