package nl.erends.advent;

import java.util.ArrayList;
import java.util.List;

public class BITSPacket {

    private final String binaryString;
    private final StringBuilder literalValue = new StringBuilder();
    private int pointer;
    private int version;
    private long value;
    private final List<BITSPacket> subPackets = new ArrayList<>();
    private int length;


    BITSPacket(String binaryString, int pointer) {
        this.binaryString = binaryString;
        this.pointer = pointer;
    }

    void resolve() {
        version = Integer.parseInt(readBits(3), 2);
        int type = Integer.parseInt(readBits(3), 2);
        if (type == 4) {
            boolean lastBit = false;
            while (!lastBit) {
                lastBit = "0".equals(readBits(1));
                literalValue.append(readBits(4));
            }
        } else if ("0".equals(readBits(1))) { //bitLength
            int targetGength = Integer.parseInt(readBits(15), 2);
            int currentLength = 0;
            while (currentLength != targetGength) {
                BITSPacket subPacket = new BITSPacket(binaryString, pointer);
                subPacket.resolve();
                pointer += subPacket.length;
                currentLength += subPacket.length;
                this.length += subPacket.length;
                subPackets.add(subPacket);
            }
        } else {
            int targetPackets = Integer.parseInt(readBits(11), 2);
            for (int i = 0; i < targetPackets; i++) {
                BITSPacket subPacket = new BITSPacket(binaryString, pointer);
                subPacket.resolve();
                pointer += subPacket.length;
                this.length += subPacket.length;
                subPackets.add(subPacket);

            }
        }
        value = switch (type) {
            case 0 -> subPackets.stream().mapToLong(sb -> sb.value).sum();
            case 1 -> subPackets.stream().mapToLong(sb -> sb.value).reduce(1L, (l1, l2) -> l1 * l2);
            case 2 -> subPackets.stream().mapToLong(sb -> sb.value).min().orElseThrow();
            case 3 -> subPackets.stream().mapToLong(sb -> sb.value).max().orElseThrow();
            case 4 -> Long.parseLong(literalValue.toString(), 2);
            case 5 -> subPackets.get(0).value > subPackets.get(1).value ? 1 : 0;
            case 6 -> subPackets.get(0).value < subPackets.get(1).value ? 1 : 0;
            case 7 -> subPackets.get(0).value == subPackets.get(1).value ? 1 : 0;
            default -> throw new IllegalArgumentException();
        };
    }

    String readBits(int amount) {
        String bits = binaryString.substring(pointer, pointer + amount);
        pointer += amount;
        length += amount;
        return bits;
    }

    int getTotalVersions() {
        int totalVersions = version;
        for (BITSPacket s : subPackets) {
            totalVersions += s.getTotalVersions();
        }
        return totalVersions;
    }

    long getValue() {
        return value;
    }
}
