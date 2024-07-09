import org.junit.Ignore;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import static org.junit.Assert.*;

public class SequenceTest {

    @Test
    public void getLocalMaximumsDistance1() throws IOException {
        System.out.println("Test 1 (no maximum)");
        String str = "1\n" +
                "2\n" +
                "3\n" +
                "4\n" +
                "5\n" +
                "6\n" +
                "7\n" +
                "0";
        Scanner in = new Scanner(str);
        assertEquals(0, Sequence.getLocalMaximumsDistance(in));
    }

    @Test
    public void getLocalMaximumsDistance2() throws IOException {
        System.out.println("Test 2 (several maximums)");
        String str = "1\n" +
                "2\n" +
                "1\n" +
                "1\n" +
                "2\n" +
                "1\n" +
                "2\n" +
                "1\n" +
                "0";
        Scanner in = new Scanner(str);
        assertEquals(2, Sequence.getLocalMaximumsDistance(in));
    }

    @Test
    public void getLocalMaximumsDistance3() throws IOException {
        System.out.println("Test 3 (sequence keeps going after '0'; should not read after '0'");
        String str3 = "2\n" +
                "0\n" +
                "3\n" +
                "1\n" +
                "2";
        Scanner in = new Scanner(str3);
        assertEquals(0, Sequence.getLocalMaximumsDistance(in));
    }

    @Test
    public void getLocalMaximumsDistance4() throws IOException {
        System.out.println("Test 4 (negative numbers; should throw IOException - number does not belong to N)");
        String str4 = "-1\n" +
                "3\n" +
                "-2\n" +
                "4\n" +
                "5\n" +
                "0";
        Scanner in = new Scanner(str4);
        assertEquals(new IOException(), Sequence.getLocalMaximumsDistance(in));
    }

    @Test
    public void getLocalMaximumsDistance5() throws IOException {
        System.out.println("Test 5 (no \'0\' in the end of a string)");
        String str5 = "1\n" +
                "2\n" +
                "3\n" +
                "4\n" +
                "3\n" +
                "5\n" +
                "6";
        Scanner in = new Scanner(str5);
        assertEquals(2, Sequence.getLocalMaximumsDistance(in));
    }

    @Test
    public void getLocalMaximumsDistance6() throws IOException {
        System.out.println("Test 6");
        String str6 = "2\n" +
                "2\n" +
                "4\n" +
                "0\n" +
                "2";
        Scanner in = new Scanner(str6);
        assertEquals(0, Sequence.getLocalMaximumsDistance(in));
    }

    @Ignore("Message for ignored test")
    @Test
    public void getLocalMaximumsDistance7() throws IOException {
        System.out.println("Test 7");
        String str7 = "2\n" +
                "2\n" +
                "3\n" +
                "2\n" +
                "2\n" +
                "4\n"+
                "0";
        Scanner in = new Scanner(str7);
        assertEquals(2147483647, Sequence.getLocalMaximumsDistance(in));
    }

    @Test
    public void getLocalMaximumsDistance8() throws IOException {
        System.out.println("Test 8");
        String str8 = "0";
        Scanner in = new Scanner(str8);
        assertEquals(0, Sequence.getLocalMaximumsDistance(in));
    }

    @Test
    public void getLocalMaximumsDistance9() throws IOException {
        System.out.println("Test 9(reading a text file)");
        File file = new File("file.txt");
        Scanner in = new Scanner(file);
        assertEquals(2, Sequence.getLocalMaximumsDistance(in));
    }

    @Test
    public void getLocalMaximumsDistance10() throws IOException {
        System.out.println("Test 10(Enter tour array, then enter the expected result)");
        File file = new File("file.txt");
        Scanner in = new Scanner(System.in);
        System.out.println("Enter expected result: ");
        //Scanner consoleIn = new Scanner(System.in);
        int expected = in.nextInt();
        System.out.println("");
        assertEquals(expected, Sequence.getLocalMaximumsDistance(in));
    }
}