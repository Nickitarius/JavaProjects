package oop4;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

@Component
public class Reader {

    public Abiturient[] readFile(File file/*String fileName*/) throws FileNotFoundException {
        //File data = new File(fileName);
        Scanner dataScanner = new Scanner(file);
        int s = dataScanner.nextInt();
        dataScanner.nextLine();
        Abiturient list[] = new Abiturient[s];
        for (int c = 0; c < s; c++) {
            list[c] = new Abiturient();
            dataScanner.nextLine();
            list[c].setId(dataScanner.nextInt());
            dataScanner.nextLine();
            list[c].setSurname(dataScanner.nextLine());
            list[c].setName(dataScanner.nextLine());
            list[c].setSecondName(dataScanner.nextLine());
            list[c].setPhoneNumber(dataScanner.nextLine());
            list[c].setFirstSubject(dataScanner.nextInt());
            list[c].setSecondSubject(dataScanner.nextInt());
            list[c].setThirdSubject(dataScanner.nextInt());
        }
        return list;
    }

    //Список всех записей
    public int getAllItems(Abiturient[] list) {
        System.out.println(Abiturient.getTableCaption());
        int c;
        for (c = 0; c < list.length; c++) {
            System.out.println(list[c].toString());
            System.out.println(Abiturient.getLine());
        }
        return c;
    }

    //Список абитуриентов, имеющих хотя бы одну оценку ниже 30 баллов
    public int getLoosers(Abiturient[] list, int minMark) {
        int ds = 0;
        for (int c = 0; c < list.length; c++) {
            if (list[c].getFirstSubject() < minMark || list[c].getSecondSubject() < minMark
                    || list[c].getThirdSubject() < minMark) {
                ds++;
            }
        }
        if (ds > 0) {
            Abiturient deplorables[] = new Abiturient[ds];
            ds = 0;
            for (int c = 0; c < list.length; c++) {
                if (list[c].getFirstSubject() < minMark
                        || list[c].getSecondSubject() < minMark || list[c].getThirdSubject() < minMark) {
                    deplorables[ds] = new Abiturient(list[c]);
                    ds++;
                }
            }
            System.out.println(Abiturient.getTableCaption());
            for (int c = 0; c < deplorables.length; c++) {
                System.out.println(deplorables[c]);
                System.out.println(Abiturient.getLine());
            }
        } else {
            System.out.println("No such records.");
        }
        return ds;
    }

    //список абитуриентов, средний балл у которых выше заданного
    public int getSmartAbiturients(Abiturient[] list, int minAverage) {
        int gs = 0;
        for (int c = 0; c < list.length; c++) {
            if ((list[c].getFirstSubject() + list[c].getSecondSubject() + list[c].getThirdSubject()) / 3 > minAverage) {
                gs++;
            }
        }
        if (gs > 0) {
            Abiturient goodEnough[] = new Abiturient[gs];
            gs = 0;
            for (int c = 0; c < list.length; c++) {
                if ((list[c].getFirstSubject() + list[c].getSecondSubject() + list[c].getThirdSubject()) / 3 > minAverage) {
                    goodEnough[gs] = new Abiturient(list[c]);
                    gs++;
                }
            }
            System.out.println(Abiturient.getTableCaption());
            for (int c = 0; c < goodEnough.length; c++) {
                System.out.println(goodEnough[c]);
                System.out.println(Abiturient.getLine());
            }
        } else {
            System.out.println("No such records.");
        }
        return gs;
    }

    //Выбрать заданное число n абитуриентов, имеющих самый высокий средний балл
    public int getNBestAbiturients(Abiturient[] list, int numberOfBest) throws ArrayIndexOutOfBoundsException {
        if (numberOfBest > list.length) {
            throw new ArrayIndexOutOfBoundsException("Required size of the sample" +
                    " is more than the size of the original array");
        }
        double scores[] = new double[list.length];
        Abiturient best[] = Arrays.copyOf(list, list.length);
        for (int c = 0; c < list.length; c++) {
            scores[c] = (list[c].getFirstSubject() + list[c].getSecondSubject() + list[c].getThirdSubject()) / 3.0;
        }
        double ts;
        int n1 = list.length;
        for (int c = 0; c < n1 - 1; c++) {
            for (int cc = n1 - 2; cc >= c; cc--) {
                if (scores[cc] < scores[cc + 1]) {
                    ts = scores[cc];
                    scores[cc] = scores[cc + 1];
                    scores[cc + 1] = ts;
                    Abiturient tb = best[cc];
                    best[cc] = best[cc + 1];
                    best[cc + 1] = tb;
                }
            }
        }
        best = Arrays.copyOfRange(best, 0, numberOfBest);
        System.out.println(Abiturient.getTableCaption());
        for (int c = 0; c < best.length; c++) {
            System.out.println(best[c]);
            System.out.println(Abiturient.getLine());
        }
        return best.length;
    }

    //Список всех записей, отсортированный по сумме оценок
    public int getSortedByOverallMark(Abiturient[] list) {
        double sums[] = new double[list.length];
        Abiturient sortedByMarks[] = Arrays.copyOf(list, list.length);
        for (int c = 0; c < list.length; c++) {
            sums[c] = list[c].getFirstSubject() + list[c].getSecondSubject() + list[c].getThirdSubject();
        }
        double t;
        int n2 = list.length;
        for (int c = 0; c < n2 - 1; c++) {
            for (int cc = n2 - 2; cc >= c; cc--) {
                if (sums[cc] < sums[cc + 1]) {
                    t = sums[cc];
                    sums[cc] = sums[cc + 1];
                    sums[cc + 1] = t;
                    Abiturient tb = sortedByMarks[cc];
                    sortedByMarks[cc] = sortedByMarks[cc + 1];
                    sortedByMarks[cc + 1] = tb;
                }
            }
        }
        System.out.println(Abiturient.getTableCaption());
        for (int c = 0; c < list.length; c++) {
            System.out.println(sortedByMarks[c]);
            System.out.println(Abiturient.getLine());
        }
        return list.length;
    }
}
