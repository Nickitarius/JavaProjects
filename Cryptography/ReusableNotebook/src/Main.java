import java.nio.charset.StandardCharsets;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        String cipherText1 = "0510e24682425122e1fc40b921ea9d3dcb7e2b7eb868b1c60c3dfc066986\\\\ \n" +
                "9abed74e50765aff661ad3c2b4d805";
        String cipherText2 = "0d11ae08995e182de8fb09f820e0de30c3746d5eb63db7c75f2bb90e6980\\\\ \n" +
                "dfebd043472f1cec7d1a9dc3b9d2";
        String knownText = "All things are difficult beyond they are easy";
        String at = "attack at dawn";
        String ct = "2ae55a8d659a1a9c8484a781e80a";
        byte at1[] = at.getBytes();
        byte ct1[] = hexStringToByteArray(ct);
        byte k[] = new byte[at1.length];
        byte m2[] = "attack at dusk".getBytes();
        byte c2[] = new byte[m2.length];
        for (int i = 0; i < Math.min(k.length, at1.length); i++) {
            k[i] = (byte) (at1[i] ^ ct1[i]);
            c2[i] = (byte) (k[i] ^ m2[i]);
        }
        System.out.println(bytesToHexString(c2));
        byte xorText1[] = hexStringToByteArray(cipherText1);
        byte xorText2[] = hexStringToByteArray(cipherText2);
        byte xorText3[] = new byte[xorText1.length];
        for (int i = 0; i < Math.min(xorText1.length, xorText2.length); i++) {
            xorText3[i] = (byte) (xorText1[i] ^ xorText2[i]);
        }
        /*Шаг 2*/
        knownText.replace('*', ' ');
        byte knownByte[] = knownText.getBytes();
        //Шаг 3
        byte sumText[] = new byte[xorText3.length];
        //byte sumText[] = (cipherText1+cipherText2).getBytes();
        for (int i = 0; i < Math.min(xorText3.length, knownByte.length); i++) {
            sumText[i] = (byte) (xorText3[i] ^ knownByte[i]);
        }
        String finalText = new String(sumText, StandardCharsets.UTF_8);
        //StringBuffer sb = new StringBuffer(knownText);
        /*int c = 0;
        for (int i = 0; i < knownText.length(); i++) {
            if (knownText.charAt(i) == '*') {
                sb.setCharAt(i, finalText.charAt(c));
                c++;
                //System.out.println(finalText.charAt(c));
            }
        }*/
        System.out.println(finalText);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];
        for (int i = 0; i < len; i += 2) {
            data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4)
                    + Character.digit(s.charAt(i + 1), 16));
        }
        return data;
    }

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder(bytes.length * 2);
        Formatter formatter = new Formatter(sb);
        for (byte b : bytes) {
            formatter.format("%02x", b);
        }
        return sb.toString();
    }
}