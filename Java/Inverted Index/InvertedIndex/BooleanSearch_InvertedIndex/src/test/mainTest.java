package test;

import model.InvertedIndex;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class mainTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        InvertedIndex index;
        try {
            FileInputStream fis = new FileInputStream("index.out");
            ObjectInputStream oin = new ObjectInputStream(fis);
            index = (InvertedIndex) oin.readObject();
        } catch (Exception e) {
            index = new InvertedIndex();
            index.indexCollection("collection/");
            System.out.println("\n\n");
        }

        System.out.println("Brutus " + index.executeQuery("Brutus"));
        System.out.println("Caesar " + index.executeQuery("Caesar"));
        System.out.println("Calpurnia " + index.executeQuery("Calpurnia"));

        System.out.println("Brutus AND Brutus " + index.executeQuery("Brutus AND Brutus"));
        System.out.println("Brutus AND Caesar " + index.executeQuery("Brutus AND Caesar"));
        System.out.println("Brutus AND Caesar AND Calpurnia " + index.executeQuery("Brutus AND Caesar AND Calpurnia"));

        System.out.println("Brutus OR Brutus " + index.executeQuery("Brutus OR Brutus"));
        System.out.println("Brutus OR Caesar " + index.executeQuery("Brutus OR Caesar"));
        System.out.println("Brutus OR Caesar OR Calpurnia " + index.executeQuery("Brutus OR Caesar OR Calpurnia"));

        System.out.println("Spiderman " + index.executeQuery("Spiderman"));
        System.out.println("Brutus AND Spiderman " + index.executeQuery("Brutus AND Spiderman"));
        System.out.println("Caesar OR Spiderman " + index.executeQuery("Caesar OR Spiderman"));

        System.out.println("Caesar AND Calpurnia " + index.executeQuery("Caesar AND Calpurnia"));

        //Сериализация
        FileOutputStream fos = new FileOutputStream("index.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(index);
        oos.flush();
        oos.close();
    }
}
