package oop4;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NewMain {

    public static void main(String[] args) throws FileNotFoundException {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        File file = new File("input.txt");
        Scanner keyboard = new Scanner(System.in);
        Reader r = context.getBean("reader", Reader.class);
        Abiturient[] list = r.readFile(file);
        //list =
        int value;
        int cr = 1;
        while (cr > 0 && cr < 6) {
            System.out.println("\nChoose output criterion (1-5): ");
            cr = keyboard.nextInt();
            switch (cr) {
                //список всех записей;
                case 1:
                    r.getAllItems(list);
                    break;
                //список абитуриентов, имеющих хотя бы одну оценку ниже 30 баллов;
                case 2:
                    r.getLoosers(list, 30);
                    break;
                //список абитуриентов, средний балл у которых выше заданного;
                case 3:
                    System.out.println("Enter min average mark: ");
                    value = keyboard.nextInt();
                    r.getSmartAbiturients(list, value);
                    break;
                //выбрать заданное число n абитуриентов, имеющих самый высокий средний балл;
                case 4:
                    System.out.println("Enter number of best abiturients: ");
                    value = keyboard.nextInt();
                    try {
                        r.getNBestAbiturients(list, value);
                    }
                    catch (ArrayIndexOutOfBoundsException e){
                        System.out.println(e);
                    }
                    break;
                //список всех записей, отсортированный по сумме оценок.
                case 5:
                    r.getSortedByOverallMark(list);
                    break;
            }
        }
    }

}
