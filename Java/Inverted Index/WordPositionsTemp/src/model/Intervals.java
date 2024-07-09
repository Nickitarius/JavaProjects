package model;

import jdk.jfr.Unsigned;

import java.util.LinkedList;
import java.util.List;

public class Intervals {

    public static List<Byte> byteEncode(List<Integer> list) {
        List<Byte> res = new LinkedList<>();
        for (Integer number : list) {
            res.addAll(byteEncodeNumber(number));
        }
        return res;
    }

    private static List<Byte> byteEncodeNumber(Integer number) {
       // System.out.println("\nnumber: " + number + "\n");
        List<Byte> res = new LinkedList<>();
        while (true) {
            res.add(0, (byte) (number % 128));
            if (number < 128) {
                break;
            }
            number = number / 128;
        }
        byte last = res.get(res.size() - 1);
        last += 128;
        res.set(res.size() - 1, last);
        return res;
    }

    public static List<Integer> byteDecode(List<Byte> list) {
        //System.out.println("------------------------------\n\n\n");
        List<Integer> res = new LinkedList<>();
        int n = 0;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) > 0) {
                n = 128 * n + (list.get(i));
                //System.out.println("if " + n);
            } else {
                //System.out.println(128 * n);
                n = 128 * n + (list.get(i) + 128);
                res.add(n);
                //System.out.println("else n=" + n + " list[i]= " + list.get(i));
                n = 0;
            }
            //System.out.println("----------------------------------");
        }
        return res;
    }

    public static List<Integer> toIntervals(List<Integer> list) {
        List<Integer> res = new LinkedList<>();
        if (!list.isEmpty()) {
            int prev = list.get(0);
            int temp;
            res.add(prev);
            for (int i = 1; i < list.size(); i++) {
                temp = list.get(i);
                res.add(temp - prev);
                prev = temp;
            }
        }
        return res;
    }

    public static List<Integer> toDocIds(List<Integer> list) {
        LinkedList<Integer> res = new LinkedList<>();
        if (!list.isEmpty()) {
            int prev = list.get(0);
            int temp;
            res.add(prev);
            for (int i = 1; i < list.size(); i++) {
                temp = list.get(i);
                res.add(prev + temp);
                prev = prev + temp;
            }
        }
        return res;
    }
}
