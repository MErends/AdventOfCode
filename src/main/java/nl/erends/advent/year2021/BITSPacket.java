package nl.erends.advent.year2021;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class BITSPacket {
    
    private String stringRemain;
    private int version;
    private int typeID;
    private long literalValue;
    private final List<BITSPacket> subPackets = new ArrayList<>();
    
    public static BITSPacket of(String hexString) {
        String binaryString = parseHexstring(hexString);
        return sub(binaryString);
    }
    
    private static BITSPacket sub(String binaryString) {
        BITSPacket bitsPacket = new BITSPacket();
        String versionString = binaryString.substring(0, 3);
        binaryString = binaryString.substring(3);
        bitsPacket.version = Integer.parseInt(versionString, 2);
        
        String typeIDString = binaryString.substring(0, 3);
        binaryString = binaryString.substring(3);
        bitsPacket.typeID = Integer.parseInt(typeIDString, 2);
        
        if (bitsPacket.typeID == 4) {
            StringBuilder literalString = new StringBuilder();
            while (true) {
                String litBit = binaryString.substring(0, 5);
                binaryString = binaryString.substring(5);
                literalString.append(litBit.substring(1));
                if (litBit.charAt(0) == '0') {
                    break;
                }
            }
            bitsPacket.literalValue = Long.parseLong(literalString.toString(), 2);
        } else {
            String lengthID = binaryString.substring(0, 1);
            binaryString = binaryString.substring(1);
            if (lengthID.equals("0")) {
                int subpackageLength = Integer.parseInt(binaryString.substring(0, 15), 2);
                binaryString = binaryString.substring(15);
                String subpackageBits = binaryString.substring(0, subpackageLength);
                binaryString = binaryString.substring(subpackageLength);
                while (!subpackageBits.isEmpty()) {
                    BITSPacket subpacket = BITSPacket.sub(subpackageBits);
                    subpackageBits = subpacket.stringRemain;
                    bitsPacket.subPackets.add(subpacket);
                }
            } else {
                int subPackageCount = Integer.parseInt(binaryString.substring(0, 11), 2);
                binaryString = binaryString.substring(11);
                for (int i = 0; i < subPackageCount; i++) {
                    BITSPacket subpacket = BITSPacket.sub(binaryString);
                    bitsPacket.subPackets.add(subpacket);
                    binaryString = subpacket.stringRemain;
                }
            }
        }
        bitsPacket.stringRemain = binaryString;
        return bitsPacket;
    }
    
    private static String parseHexstring(String hexString) {
        return hexString.chars()
                .mapToObj(c -> leftPad(Integer.toBinaryString(Integer.parseInt("" + (char)c, 16))))
                .collect(Collectors.joining());
    }
    
    private static String leftPad(String input) {
        StringBuilder inputBuilder = new StringBuilder(input);
        while (inputBuilder.length() != 4) {
            inputBuilder.insert(0, '0');
        }
        input = inputBuilder.toString();
        return input;
    }
    
    int getTotalVersions() {
        int totalVersions = version;
        for (BITSPacket sub : subPackets) {
            totalVersions += sub.getTotalVersions();
        }
        return totalVersions;
    }
    
    public long getValue() {
        LongStream valueStream = subPackets.stream().mapToLong(BITSPacket::getValue);
        switch (typeID) {
            case 0:
                return valueStream.sum();
            case 1:
                return valueStream.reduce(1L , (p1, p2) -> p1 * p2);
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
        }
        throw new IllegalArgumentException();
    }
}
