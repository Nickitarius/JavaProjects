package oop4;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;


//Старая реализация
@Deprecated
public class OOP4 {

    public static void main(String[] args) throws FileNotFoundException {
        File data = new File("input.txt");
        Scanner dataScanner = new Scanner(data);
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
        Scanner keyboard = new Scanner(System.in);
        String fileName = "input.txt";
        //Abiturient[] list = Reader.readFile();

        int cr = 1;
        while (cr > 0 && cr < 6) {
            System.out.println("\nChoose output criterion (1-5): ");
            cr = keyboard.nextInt();
            switch (cr) {
                //список всех записей;
                case 1:
                    System.out.println(Abiturient.getTableCaption());
                    for (int c = 0; c < s; c++) {
                        System.out.println(list[c].toString());
                        System.out.println(Abiturient.getLine());
                    }
                    break;
                //список абитуриентов, имеющих хотя бы одну оценку ниже 30 баллов;
                case 2:
                    int ds = 0;
                    for (int c = 0; c < s; c++) {
                        if (list[c].getFirstSubject() < 30 || list[c].getSecondSubject() < 30 || list[c].getThirdSubject() < 30) {
                            ds++;
                        }
                    }
                    if (ds > 0) {
                        Abiturient deplorables[] = new Abiturient[ds];
                        ds = 0;
                        for (int c = 0; c < s; c++) {
                            if (list[c].getFirstSubject() < 30 || list[c].getSecondSubject() < 30 || list[c].getThirdSubject() < 30) {
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
                    break;
                //список абитуриентов, средний балл у которых выше заданного;
                case 3:
                    System.out.println("Enter the minimal average score: ");
                    double min = keyboard.nextDouble();
                    int gs = 0;
                    for (int c = 0; c < s; c++) {
                        if ((list[c].getFirstSubject() + list[c].getSecondSubject() + list[c].getThirdSubject()) / 3 > min) {
                            gs++;
                        }
                    }
                    if (gs > 0) {
                        Abiturient goodEnough[] = new Abiturient[gs];
                        gs = 0;
                        for (int c = 0; c < s; c++) {
                            if ((list[c].getFirstSubject() + list[c].getSecondSubject() + list[c].getThirdSubject()) / 3 > min) {
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
                    break;
                //выбрать заданное число n абитуриентов, имеющих самый высокий средний балл;
                case 4:
                    System.out.print("Enter n (number of most successful): ");
                    int n = keyboard.nextInt();
                    double scores[] = new double[s];
                    Abiturient best[] = Arrays.copyOf(list, list.length);
                    for (int c = 0; c < s; c++) {
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
                    best = Arrays.copyOfRange(best, 0, n);
                    System.out.println(Abiturient.getTableCaption());
                    for (int c = 0; c < best.length; c++) {
                        System.out.println(best[c]);
                        System.out.println(Abiturient.getLine());
                    }
                    break;
                //список всех записей, отсортированный по сумме оценок.
                case 5:
                    double sums[] = new double[s];
                    Abiturient sortedByMarks[] = Arrays.copyOf(list, list.length);
                    for (int c = 0; c < s; c++) {
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
                    for (int c = 0; c < s; c++) {
                        System.out.println(sortedByMarks[c]);
                        System.out.println(Abiturient.getLine());
                    }
                    break;
            }
        }
    }

}
