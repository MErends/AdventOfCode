package nl.erends.advent.year2015;


import nl.erends.advent.util.MD5;
import nl.erends.advent.util.Problem;
import nl.erends.advent.util.Timer;
import nl.erends.advent.util.Util;
import org.apache.log4j.Logger;

public class Day04 implements Problem<String, Integer> {
    
    private static final Logger LOG = Logger.getLogger(Day04.class);
    private int nonce;
    
    public static void main(String[] args) {
        String input = Util.readLine(2015, 4);
        Day04 problem = new Day04();
        LOG.info(problem.solve1(input));
        LOG.info(problem.solve2(input));
        Timer.printStats();
    }
    
    public Integer solve1(String input) {
        Timer.start();
        nonce = 0;
        while(true) {
            String hash = MD5.getHash(input + nonce);
            if (hash.startsWith("00000")) {
                Timer.end1();
                return nonce;
            }
            nonce++;
        }
    }
    
    public Integer solve2(String input) {
        Timer.start2();
        if (nonce == 0) {
            solve1(input);
        }
        nonce = 0;
        while(true) {
            String hash = MD5.getHash(input + nonce);
            if (hash.startsWith("000000")) {
                Timer.end2();
                return nonce;
            }
            nonce++;
        }
    }
}
