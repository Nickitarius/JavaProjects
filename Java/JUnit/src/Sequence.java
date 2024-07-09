import java.io.IOException;
import java.util.Scanner;

public class Sequence {

    public static int getLocalMaximumsDistance(Scanner input) throws IOException {
        boolean hasMax = false;
        int cur = 0, prev = 0, next, dist = 1, res = 0;
        if (input.hasNext()) {
            prev = input.nextInt();
            if (prev < 0) {
                throw new IOException("Error! Negative numbers are not allowed!");
            }
        }
        if (input.hasNext()) {
            cur = input.nextInt();
        }
        while (cur != 0 && input.hasNext()) {
            next = input.nextInt();
            if (next == 0) {
                break;
            }
            if (next!=0 && !input.hasNext()){
                throw new IOException("Error! End of sequence not specified. \'0\' should be placed as the last symbol!");
            }
            if (cur > prev && cur > next) {
                if (res == 0) {
                    res = Integer.MAX_VALUE;
                }
                if (hasMax) {
                    if (dist < res) {
                        res = dist;
                    }
                    dist = 1;
                } else {
                    hasMax = true;

                }
            } else if (hasMax) {
                dist++;
            }
            prev = cur;
            cur = next;
        }
        return res;
    }
}
