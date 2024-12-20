package nl.erends.advent;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

/**
 * --- Day 16: Packet Decoder ---
 * <p>As you leave the cave and reach open waters, you receive a transmission
 * from the Elves back on the ship. The transmission was sent using the Buoyancy
 * Interchange Transmission System (BITS), a method of packing numeric
 * expressions into a binary sequence. Decode and evaluate the expression
 * represented by your hexadecimal-encoded BITS transmission.
 * <p><a href="https://adventofcode.com/2021/day/16">2021 Day 16</a>
 */
public class Day16 extends AbstractProblem<String, Number> {
    
    public static void main(String[] args) {
        new Day16().setAndSolve(Util.readLine(2021, 16));
    }

    @Override
    public Integer solve1() {
        String hex = input;
        StringBuilder binaryString = new StringBuilder();
        for (int i = 0; i < hex.length(); i++) {
            String hexChar = hex.substring(i, i+ 1);
            int value = Integer.parseInt(hexChar, 16);
            String charString = Integer.toBinaryString(value);
            charString = Util.leftPadWithZero(charString, 4);
            binaryString.append(charString);
        }
        BITSPacket bits = new BITSPacket(binaryString.toString(), 0);
        bits.resolve();
        answer2 = bits.getValue();
        return bits.getTotalVersions();
    }
}
