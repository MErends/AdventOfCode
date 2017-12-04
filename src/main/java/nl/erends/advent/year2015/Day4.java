package nl.erends.advent.year2015;


import java.security.MessageDigest;

public class Day4 {
    public static void main(String[] args) throws Exception {

        String input = "ckczppom";
        int nonce = 0;
        MessageDigest md5er = MessageDigest.getInstance("MD5");
        StringBuilder sb = new StringBuilder();
        while(true) {
            sb = new StringBuilder();
            byte[] digest = md5er.digest(input.concat(Integer.toString(nonce)).getBytes("UTF-8"));
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));
            }
            String hash = sb.toString();
            if (hash.startsWith("000000")) {
                break;
            }
            nonce++;
        }
        System.out.print(nonce);
    }
}
