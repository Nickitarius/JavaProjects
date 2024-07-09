import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.CRC32;

public class Reading {
    public static void main(String[] args) throws IOException {
        long hash2 = 0x0BA02B6E1L;
        File f = new File("10k-most-common.txt");
        Scanner in = new Scanner(f);
        String current;
        CRC32 crc32 = new  CRC32();
        while (in.hasNext()) {
            current = in.next();
            for (int i = 0; i < 10000; i++) {
                String plaintext = current+i;
                crc32.update(plaintext.getBytes());
                long checksum = crc32.getValue();
                if (checksum == hash2) {
                    System.out.println(current + i);
                }
                crc32.reset();
            }
        }
    }
}
