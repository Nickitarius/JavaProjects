import org.junit.Ignore;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws FileNotFoundException, IOException {
        /*String str1 = "1\n" +
                "2\n" +
                "3\n" +
                "4\n" +
                "5\n" +
                "6\n" +
                "7\n" +
                "0";
        Scanner in1 = new Scanner(str1);
        System.out.println("Test 1 (no maximum): " + str1 + "\nResult: " + Sequence.getLocalMaximumsDistance(in1));
        String str2 = "1\n" +
                "2\n" +
                "1\n" +
                "1\n" +
                "2\n" +
                "1\n" +
                "2\n" +
                "1\n" +
                "0";
        Scanner in2 = new Scanner(str2);
        System.out.println("Test 2 (several maximums): " + str2 + "\nResult: " + Sequence.getLocalMaximumsDistance(in2));
        String str3 = "2\n" +
                "0\n" +
                "3\n" +
                "1\n" +
                "2";
        Scanner in3 = new Scanner(str3);
        System.out.println("Test 3 (sequence keeps going after \'0\'; should not read after \'0\'): " + str3 + "\nResult: " + Sequence.getLocalMaximumsDistance(in3));
        String str4 = "-1\n" +
                "3\n" +
                "-2\n" +
                "4\n" +
                "5\n" +
                "0";
        Scanner in4 = new Scanner(str4);
        System.out.println("Test 4 (negative numbers; should throw IOException - number does not belong to N): " + str4 + "\nResult: ");
        try {
            System.out.println(Sequence.getLocalMaximumsDistance(in4));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        test5();
        String str6 = "2\n" +
                "2\n" +
                "4\n" +
                "0\n" +
                "2";
        Scanner in6 = new Scanner(str6);
        System.out.println("Test 6 : " + str6 + "\nResult: " + Sequence.getLocalMaximumsDistance(in6));
        String str7 = "2\n" +
                "2\n" +
                "3\n" +
                "2\n" +
                "2\n" +
                "4\n"+
                "0";
        Scanner in7 = new Scanner(str7);
        System.out.println("Test 7 : " + str7 + "\nResult: " + Sequence.getLocalMaximumsDistance(in7));
        String str8 = "0";
        Scanner in8 = new Scanner(str8);
        System.out.println("Test 8 : " + str8 + "\nResult: " + Sequence.getLocalMaximumsDistance(in8));

        File file = new File("file.txt");
        Scanner in9 = new Scanner(file);
        System.out.println("Test 9 (reading a text file): " + "See \'file.txt\'" + "\nResult: " + Sequence.getLocalMaximumsDistance(in9));
        Scanner in10 = new Scanner(System.in);
        System.out.println("Test 10 (reading from console): " + "Enter numbers yourself: ");
        System.out.println(Sequence.getLocalMaximumsDistance(in10));
    }

    public static void test5() {
        String str5 = "1\n" +
                "2\n" +
                "3\n" +
                "4\n" +
                "3\n" +
                "5\n" +
                "6";
        Scanner in5 = new Scanner(str5);
        System.out.println("Test 5 (no \'0\' in the end of a string): " + str5 + "\nResult: ");
        try {
            System.out.println(Sequence.getLocalMaximumsDistance(in5));
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    */
    }
}
