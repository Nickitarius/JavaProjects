package test;

import model.Intervals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class IntervalsTest {
    public static void main(String[] args) { //+ Intervals.byteEncode()
        System.out.println("Исходные данные -- список словопозиций: [1010, 1012, 1020, 1050, 1200, 1000000]\n");
        Integer a[] = {1010, 1012, 1020, 1050, 1200, 1000000};
        //Integer b[] = {1010, 2, 8, 30, 150, 998800};
        List<Integer> input = Intervals.toIntervals(Arrays.asList(a));
        List<Byte> encoded = Intervals.byteEncode(input);
        System.out.println("Закодированная последовательность (по списку интервалов): " /*+ encoded*/);
        for (Byte item : encoded) {
            //System.out.println(item);
            System.out.println("" + String.format("%8s",
                    Integer.toBinaryString((byte) item)).replace(' ', '0') + " " + item);
        }
        List<Integer> decoded = Intervals.byteDecode(encoded);
        System.out.println("Декодированная последовательность: " + decoded);
        //System.out.println("Список интервалов (по списку словопозиций):" + Intervals.toIntervals(input1));
    }
}
