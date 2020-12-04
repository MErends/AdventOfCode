package nl.erends.advent.year2020;

import nl.erends.advent.util.AbstractProblem;
import nl.erends.advent.util.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day04 extends AbstractProblem<List<String>, Integer> {
    
    private static final List<String> VALID_ECL = Arrays.asList("amb", "blu", "brn", "gry", "grn", "hzl", "oth");

    public static void main(String[] args) {
        new Day04().setAndSolve(Util.readInput(2020, 4));
    }
    
    @Override
    public Integer solve1() {
        answer2 = 0;
        List<Passport> passportList = new ArrayList<>();
        StringBuilder passportLine = new StringBuilder();
        for (String line : input) {
            if (line.isEmpty()) {
                passportList.add(new Passport(passportLine.toString().trim()));
                passportLine = new StringBuilder();
            } else {
                passportLine.append(' ').append(line);
            }
        }
        passportList.add(new Passport(passportLine.toString().trim()));
        
        int semiValidCount = 0;
        for (Passport passport : passportList) {
            if (passport.isSemiValid()) {
                semiValidCount++;
                if (passport.isSuperValid()) {
                    answer2++;
                }
            }
        }
        return semiValidCount;
    }
    
    private static class Passport {
        
        private Map<String, String> attrs = new HashMap<>();
        
        private Passport(String line) {
            String[] words = line.split(" ");
            for (String word : words) {
                String[] split = word.split(":");
                attrs.put(split[0], split[1]);
            }   
        }
        
        private boolean isSemiValid() {
            return attrs.get("byr") != null
                    && attrs.get("iyr") != null
                    && attrs.get("eyr") != null
                    && attrs.get("hgt") != null
                    && attrs.get("hcl") != null
                    && attrs.get("ecl") != null
                    && attrs.get("pid") != null;
        }
        
        private boolean isSuperValid() {
            return isValidByr()
                    && isValidIyr()
                    && isValidEyr()
                    && isValidHgt()
                    && isValidHcl()
                    && isValidEcl()
                    && isValidPid();
        }
        
        private boolean isValidByr() {
            int byr = Integer.parseInt(attrs.get("byr"));
            return byr >= 1920 && byr <= 2002; 
        }
        
        private boolean isValidIyr() {
            int iyr = Integer.parseInt(attrs.get("iyr"));
            return iyr >= 2010 && iyr <= 2020;
        }
        
        private boolean isValidEyr() {
            int eyr = Integer.parseInt(attrs.get("eyr"));
            return eyr >= 2020 && eyr <= 2030;
        }
        
        private boolean isValidHgt() {
            String hgt = attrs.get("hgt");
            String unit = hgt.substring(hgt.length() - 2);
            if (!unit.equals("cm") && !unit.equals("in")) {
                return false;
            } else {
                int length = Integer.parseInt(hgt.substring(0, hgt.length() - 2));
                if ("cm".equals(unit)) {
                    return length >= 150 && length <= 193;
                }
                return length >= 59 && length <= 76;
            }
        }
        
        private boolean isValidHcl() {
            return attrs.get("hcl").matches("#[0-9a-f]{6}");
        }
        
        private boolean isValidEcl() {
            return VALID_ECL.contains(attrs.get("ecl"));
        }
        
        private boolean isValidPid() {
            return attrs.get("pid").matches("[0-9]{9}");
        }
    }
}
