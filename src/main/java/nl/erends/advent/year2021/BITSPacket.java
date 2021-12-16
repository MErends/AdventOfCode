package nl.erends.advent.year2021;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class BITSPacket {
    
    private static StringBuilder binaryString;
    private static int pointer;
    
    private final int version;
    private final int typeID;
    private long literalValue;
    private final List<BITSPacket> subPackets = new ArrayList<>();
    
    public static BITSPacket of(String hexString) {
        pointer = 0;
        binaryString = new StringBuilder(hexString.chars()
                .mapToObj(c -> leftPad(Integer.toBinaryString(Integer.parseInt("" + (char) c, 16))))
                .collect(Collectors.joining()));
        return new BITSPacket();
    }
    
    private BITSPacket() {
        version = Integer.parseInt(getBits(3), 2);
        typeID = Integer.parseInt(getBits(3), 2);
        if (typeID == 4) {
            StringBuilder literal = new StringBuilder();
            while (true) {
                String lastBit = getBits(1);
                literal.append(getBits(4));
                if ("0".equals(lastBit)) {
                    break;
                }
            }
            literalValue = Long.parseLong(literal.toString(), 2);
            return;
        }
        if ("0".equals(getBits(1))) {
            int subpackageLength = Integer.parseInt(getBits(15), 2);
            int pointerBefore = pointer;
            while (pointer != pointerBefore + subpackageLength) {
                BITSPacket subpacket = new BITSPacket();
                subPackets.add(subpacket);
            }
        } else {
            int subpackageCount = Integer.parseInt(getBits(11), 2);
            for (int i = 0; i < subpackageCount; i++) {
                BITSPacket subpacket = new BITSPacket();
                subPackets.add(subpacket);
            }
        }
    }

    private static String leftPad(String input) {
        String zeroes = LongStream.range(0, 4L - input.length())
                .mapToObj(i -> "0")
                .collect(Collectors.joining());
        return zeroes + input;
    }
    
    int getTotalVersions() {
        return version + subPackets.stream()
                .mapToInt(BITSPacket::getTotalVersions)
                .sum();
    }
    
    public long getValue() {
        LongStream valueStream = subPackets.stream().mapToLong(BITSPacket::getValue);
        switch (typeID) {
            case 0:
                return valueStream.sum();
            case 1:
                return valueStream.reduce(1L, (p1, p2) -> p1 * p2);
            case 2:
                return valueStream.min().orElseThrow();
            case 3:
                return valueStream.max().orElseThrow();
            case 4:
                return literalValue;
            case 5:
                return subPackets.get(0).getValue() > subPackets.get(1).getValue() ? 1L : 0L;
            case 6:
                return subPackets.get(0).getValue() < subPackets.get(1).getValue() ? 1L : 0L;
            case 7:
                return subPackets.get(0).getValue() == subPackets.get(1).getValue() ? 1L : 0L;
            default:
                throw new IllegalArgumentException();
        }
    }
    
    private static String getBits(int numBits) {
        String s = binaryString.substring(pointer, pointer + numBits);
        pointer += numBits;
        return s;
    }
}
