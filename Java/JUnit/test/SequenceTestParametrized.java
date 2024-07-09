import org.junit.*;
import org.junit.runner.*;
import org.junit.runners.*;

import java.io.*;
import java.util.*;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class SequenceTestParametrized {

    @Parameterized.Parameters
    public static List<Object[]> data() throws FileNotFoundException {
        return Arrays.asList(new Object[][]{
                {new Scanner("1\n" +
                        "2\n" +
                        "3\n" +
                        "4\n" +
                        "5\n" +
                        "6\n" +
                        "7\n" +
                        "0"), 0},
                {new Scanner("1\n" +
                        "2\n" +
                        "1\n" +
                        "1\n" +
                        "2\n" +
                        "1\n" +
                        "2\n" +
                        "1\n" +
                        "0"), 2},
                {new Scanner("2\n" +
                        "0\n" +
                        "3\n" +
                        "1\n" +
                        "2"), 0}
        });
    }

    private Scanner input;
    private int expected;

    public SequenceTestParametrized(Scanner p1, int p2) {
        input = p1;
        expected = p2;
    }

    @Test
    public void testGetLocalMaximumsDistance() throws Exception {
        int result = Sequence.getLocalMaximumsDistance(input);
        assertEquals(expected, result);
    }

}
