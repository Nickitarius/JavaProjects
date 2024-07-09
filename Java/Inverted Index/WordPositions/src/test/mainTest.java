package test;

import model.InvertedIndex;
import org.tartarus.snowball.ext.englishStemmer;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class mainTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        InvertedIndex index;
        /*try {
            FileInputStream fis = new FileInputStream("indexByte.out");
            ObjectInputStream oin = new ObjectInputStream(fis);
            index = (InvertedIndex) oin.readObject();
        } catch (Exception e) {*/
        index = new InvertedIndex("stop_words.txt");
//        index.indexDocument("collection/folder2/King_Lear.txt");
        System.out.println(index.getIndex());
        //index.indexCollection("collection_html");
        index.indexCollection("collection");
        //index.crawl("https://en.wikipedia.org/wiki/Main_Page");
        System.out.println("\n\n");
        //}

        System.out.println("Brutus " + index.executeQuery("Brutus"));
        System.out.println("Caesar " + index.executeQuery("Caesar"));
        System.out.println("[Stop Word] Above " + index.executeQuery("Above"));
        System.out.println("Work " + index.executeQuery("Work"));
        System.out.println("Works " + index.executeQuery("Works"));
        System.out.println("Work == Works: " + (index.executeQuery("Work").equals(index.executeQuery("Work"))));

        System.out.println();

        System.out.println("Brutus AND Brutus " + index.executeQuery("Brutus AND Brutus"));
        System.out.println("Brutus AND Caesar " + index.executeQuery("Brutus AND Caesar"));
        System.out.println("Brutus AND Caesar AND Calpurnia " + index.executeQuery("Brutus AND Caesar AND Calpurnia"));
        System.out.println("Caesar AND Brutus AND [Stop Word] Above " + index.executeQuery("Caesar AND Above"));

        System.out.println();

        System.out.println("Brutus OR Brutus " + index.executeQuery("Brutus OR Brutus"));
        System.out.println("Brutus OR Caesar " + index.executeQuery("Brutus OR Caesar"));
        System.out.println("Brutus OR Caesar OR Calpurnia " + index.executeQuery("Brutus OR Caesar OR Calpurnia"));
        System.out.println("Brutus OR [Stop Word] Who " + index.executeQuery("Brutus OR Who"));
        System.out.println("Brutus OR [Stop Word] about " + index.executeQuery("Brutus OR about"));


        System.out.println();

        //System.out.println(index.getIndex());

        //Сериализация
        FileOutputStream fos = new FileOutputStream("indexByte.out");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(index);
        oos.flush();
        oos.close();
    }
}
