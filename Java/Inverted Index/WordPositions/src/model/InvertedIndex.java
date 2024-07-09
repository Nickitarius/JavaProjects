package model;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.tartarus.snowball.SnowballStemmer;
import org.tartarus.snowball.ext.englishStemmer;
import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class InvertedIndex implements Serializable {

    private List<String> documents;
    private Map<String, LinkedList<Integer>> index;
    private LinkedList<String> stopWords;

    public InvertedIndex() {
        this.documents = new LinkedList<String>();
        this.index = new LinkedHashMap<String, LinkedList<Integer>>();
        this.stopWords = new LinkedList<>();
    }

    public InvertedIndex(String stopWordsPath) throws FileNotFoundException {
        this.documents = new LinkedList<String>();
        this.index = new LinkedHashMap<String, LinkedList<Integer>>();
        this.stopWords = new LinkedList<>();
        File file = new File(stopWordsPath);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            this.stopWords.add(scanner.next());
        }
    }

    public List<String> getDocuments() {
        return documents;
    }

    public Map<String, LinkedList<Integer>> getIndex() {
        return index;
    }

    private void indexTXT(File file) {
        try {
            Scanner scanner = new Scanner(file);
            String tempLine;
            String terms[];
            int prev;
            while (scanner.hasNext()) {
                tempLine = scanner.nextLine().toLowerCase();
                terms = tempLine.split("\\W+");
                englishStemmer stemmer = new englishStemmer();
                for (String tempTerm : terms) {
                    stemmer.setCurrent(tempTerm);
                    stemmer.stem();
                    tempTerm = stemmer.getCurrent();
                    if (!this.stopWords.contains(tempTerm)) {
                        LinkedList<Integer> tempIndex;
                        if (this.index.containsKey(tempTerm)) {
                            tempIndex = this.index.get(tempTerm);
                            if (!tempIndex.contains(this.documents.indexOf(file.getPath()))) {
                                tempIndex.add(this.documents.indexOf(file.getPath()));
                            }
                            prev = 0;
                        } else {
                            tempIndex = new LinkedList<Integer>();
                            tempIndex.add(this.documents.indexOf(file.getPath()));
                        }
                        this.index.put(tempTerm, tempIndex);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    private void indexHTML(File file) {
        try {
            Document doc = Jsoup.parse(file, "UTF-8");
            Scanner scanner = new Scanner(doc.body().text());
            String tempLine;
            String terms[];
            englishStemmer stemmer = new englishStemmer();
            int prev;
            while (scanner.hasNext()) {
                tempLine = scanner.nextLine().toLowerCase();
                terms = tempLine.split("\\W+");
                for (String tempTerm : terms) {
                    stemmer.setCurrent(tempTerm);
                    stemmer.stem();
                    tempTerm = stemmer.getCurrent();
                    if (!this.stopWords.contains(tempTerm)) {
                        LinkedList<Integer> tempIndex;
                        if (this.index.containsKey(tempTerm)) {
                            tempIndex = this.index.get(tempTerm);
//                            if (!tempIndex.contains(this.documents.indexOf(file.getPath()))) {
//                                tempIndex.add(this.documents.indexOf(file.getPath()));
//                            }
                            prev = 0;
                            for (Integer i : tempIndex) {
                                prev += i;
                            }
                            tempIndex.add(documents.indexOf(file.getPath()) - prev);
                        } else {
                            tempIndex = new LinkedList<Integer>();
                            tempIndex.add(this.documents.indexOf(file.getPath()));
                        }
                        this.index.put(tempTerm, tempIndex);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error indexing file " + file);
        }
    }

    public void indexURL(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Scanner scanner = new Scanner(doc.body().text());
            String tempLine;
            String terms[];
            //System.out.println(doc.body().text());
            while (scanner.hasNext()) {
                tempLine = scanner.nextLine().toLowerCase();
                terms = tempLine.split("\\W+");
                englishStemmer stemmer = new englishStemmer();
                int prev;
                for (String tempTerm : terms) {
                    stemmer.setCurrent(tempTerm);
                    stemmer.stem();
                    tempTerm = stemmer.getCurrent();
                    if (!this.stopWords.contains(tempTerm)) {
                        LinkedList<Integer> tempIndex;
                        if (this.index.containsKey(tempTerm)) {
                            tempIndex = this.index.get(tempTerm);
//                            if (!tempIndex.contains(this.documents.indexOf(url))) {
//                                tempIndex.add(this.documents.indexOf(url));
//                            }
                            prev = 0;
                            for (Integer i : tempIndex) {
                                prev += i;
                            }
                            tempIndex.add(documents.indexOf(url) - prev);
                        } else {
                            tempIndex = new LinkedList<Integer>();
                            tempIndex.add(this.documents.indexOf(url));
                        }
                        this.index.put(tempTerm, tempIndex);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error parsing remote file " + url);
        }
    }

    //Web-краулер
    public void crawl(String url) {
        try {
            Document doc = Jsoup.connect(url).get();
            Scanner scanner = new Scanner(doc.body().text());
            String tempLine;
            String terms[];
            Elements links = doc.select("a[href]");
            for (Element link : links) {
                crawl(link.attr("abs:href"));
                //System.out.println("href: " +  + "text: " + link.text());
            }
            //System.out.println(doc.body().text());
            int prev;
            while (scanner.hasNext()) {
                tempLine = scanner.nextLine().toLowerCase();
                terms = tempLine.split("\\W+");
                englishStemmer stemmer = new englishStemmer();
                for (String tempTerm : terms) {
                    stemmer.setCurrent(tempTerm);
                    stemmer.stem();
                    tempTerm = stemmer.getCurrent();
                    if (!this.stopWords.contains(tempTerm)) {
                        LinkedList<Integer> tempIndex;
                        if (this.index.containsKey(tempTerm)) {
                            tempIndex = this.index.get(tempTerm);
//                            if (!tempIndex.contains(this.documents.indexOf(url))) {
//                                tempIndex.add(this.documents.indexOf(url));
//                            }
                            prev = 0;
                            for (Integer i : tempIndex) {
                                prev += i;
                            }
                            tempIndex.add(documents.indexOf(url) - prev);
                        } else {
                            tempIndex = new LinkedList<Integer>();
                            tempIndex.add(this.documents.indexOf(url));
                        }
                        this.index.put(tempTerm, tempIndex);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error parsing remote file " + url);
        }
    }

    public void indexDocument(String path) {
        File file = new File(path);
        try {
            if (file.exists()) {
                Path pathTemp = file.toPath();
                if (Files.probeContentType(pathTemp).equals("text/plain")) {
                    this.documents.add(path);
                    indexTXT(file);
                }
                if (Files.probeContentType(pathTemp).equals("text/html")) {
                    this.documents.add(path);
                    indexHTML(file);
                }
            }
        } catch (Exception e) {
            System.out.println("Error when indexing " + path);
        }

    }

    public void indexCollection(String path) {
        File folder = new File(path);
        System.out.println("Index sizes after each file's scan. \n");
        for (String file : folder.list()) {
            File f = new File(path + "/" + file);
            if (!f.isDirectory()) {
                indexDocument(f.getPath());
                System.out.println("File " + file + " Index size: " + index.size());
            } else {
                indexCollection(f.getPath());
            }
        }
        for (String k : index.keySet()) {
            index.replace(k, (LinkedList<Integer>) Intervals.toIntervals(index.get(k)));
        }
    }

    private LinkedList<Integer> getIntersection(LinkedList<Integer> list1, LinkedList<Integer> list2) {
        LinkedList<Integer> intersection = new LinkedList<>();
        list1 = (LinkedList<Integer>) Intervals.toDocIds(list1);
        list2 = (LinkedList<Integer>) Intervals.toDocIds(list2);

        Iterator<Integer> iterator1 = list1.iterator();
        Iterator<Integer> iterator2 = list2.iterator();
        Integer current1 = null;
        Integer current2 = null;
        if (iterator1.hasNext()) {
            current1 = iterator1.next();
        }
        if (iterator2.hasNext()) {
            current2 = iterator2.next();
        }
        if (current1 == null || current2 == null) {
            return intersection;
        }
        do {
            if (current1 == current2) {
                intersection.add(current1);
                if (iterator1.hasNext()) {
                    current1 = iterator1.next();
                }
                if (iterator2.hasNext()) {
                    current2 = iterator2.next();
                }
            } else if (current1 < current2 && iterator1.hasNext()) {
                current1 = iterator1.next();
            } else if (iterator2.hasNext()) {
                current2 = iterator2.next();
            }
        } while (iterator1.hasNext() && iterator2.hasNext());
        //Иначе последнее совпадение может не обработаться.
        if (current1 == current2 && !intersection.contains(current1)) {
            intersection.add(current1);
        }
        return (LinkedList<Integer>) Intervals.toIntervals(intersection);
    }

    private LinkedList<Integer> getUnion(LinkedList<Integer> list1, LinkedList<Integer> list2) {
        LinkedList<Integer> union = new LinkedList<>();
//        System.out.println("---------------------");
//        System.out.println(list1);
        list1 = (LinkedList<Integer>) Intervals.toDocIds(list1);
//        System.out.println(list1);
//        System.out.println(list2);
        list2 = (LinkedList<Integer>) Intervals.toDocIds(list2);
//        System.out.println(list2);
        Iterator<Integer> iterator1 = list1.iterator();
        Iterator<Integer> iterator2 = list2.iterator();
        Integer current1 = null;
        Integer current2 = null;
        if (iterator1.hasNext()) {
            current1 = iterator1.next();
        }
        if (iterator2.hasNext()) {
            current2 = iterator2.next();
        }
        if (current1 == null) {
            return list2;
        }
        if (current2 == null) {
            return list1;
        }
        do {
            if (current1 == current2) {
                union.add(current1);
                if (iterator1.hasNext()) {
                    current1 = iterator1.next();
                }
                if (iterator2.hasNext()) {
                    current2 = iterator2.next();
                }
            } else if (current1 < current2 && iterator1.hasNext()) {
                union.add(current1);
                current1 = iterator1.next();
            } else {
                union.add(current2);
                if (iterator2.hasNext())
                    current2 = iterator2.next();
            }
        } while (iterator1.hasNext() || iterator2.hasNext());
        if (!union.contains(current1)) {
            union.add(current1);
        }
        if (!union.contains(current2)) {
            union.add(current2);
        }
        return (LinkedList<Integer>) Intervals.toIntervals(union);
    }

    //Обрабатывать в порядке возрастания частот вхождения слов в документы
    //Начать с пересечения двух наименьших списков, продолжить по возрастающей
    public LinkedList<Integer> executeQuery(String query) {
        /*String[] results = query.split("\\s*[()]\\s*");
        for (String r : results) {
            LinkedList<Integer> q = executeQuery(r);
        }*/
        LinkedList<String> terms;
        LinkedList<Integer> res = new LinkedList<>();
        //Тип запроса.
        //0 - простой запрос из одного терма.
        //1 - многоместная конъюнкция (AND).
        //2 - многоместная дизъюнкция (OR).
        int queryType;
        englishStemmer stemmer = new englishStemmer();
        //'AND' и 'OR' (в верхнем регистре) предполагаются зарезервированными специальными словами.
        if (query.contains(" AND ")) {
            query = query.replaceAll("AND", "");
            queryType = 1;
        } else if (query.contains(" OR ")) {
            query = query.replaceAll("OR", "");
            queryType = 2;
        } else {
            stemmer.setCurrent(query);
            stemmer.stem();
            query = stemmer.getCurrent();
            if (this.index.get(query.toLowerCase()) != null) {
                return (LinkedList<Integer>) Intervals.toDocIds(this.index.get(query.toLowerCase()));
            } else {
                return res;
            }
        }
        query = query.toLowerCase();
        terms = new LinkedList<>(Arrays.asList(query.split("\\W+")));
        String term;
        for (int i = 0; i < terms.size(); i++) {
            term = terms.get(i);
            if (!this.stopWords.contains(term)) {
                stemmer.setCurrent(term);
                stemmer.stem();
                term = stemmer.getCurrent();
                terms.set(i, term);
            } else {
                terms.remove(i);
            }
        }
        if (terms.size() == 1) {
            return (LinkedList<Integer>) Intervals.toDocIds(this.index.get(terms.getFirst()));
        }
        /* (terms.isEmpty()){
            return res;
        }*/
        //if
        LinkedList<Integer> temp1;
        LinkedList<Integer> temp2;
        //Список индексов для каждого слова
        LinkedList<LinkedList<Integer>> indices = new LinkedList<>();
        for (String t : terms) {
            if (this.index.get(t) == null) {
                indices.add(new LinkedList<>());
            } else {
                indices.add(this.index.get(t));
            }
        }
        //AND
        if (queryType == 1) {
            while (indices.size() > 1) {
                indices = bubbleSortIndex(indices);
                temp1 = indices.get(0);
                temp2 = indices.get(1);
                res.addAll(getIntersection(temp1, temp2));
                indices.remove(0);
                indices.remove(0);
                indices.add(res);
            }
        }
        //OR
        if (queryType == 2) {
            while (indices.size() > 1) {
                indices = bubbleSortIndex(indices);
                temp1 = indices.get(0);
                temp2 = indices.get(1);
                res = new LinkedList<>();
                res.addAll(getUnion(temp1, temp2));
                indices.remove(0);
                indices.remove(0);
                indices.add(res);
            }
        }
        return (LinkedList<Integer>) Intervals.toDocIds(res);
    }

    private LinkedList<LinkedList<Integer>> bubbleSortIndex(LinkedList<LinkedList<Integer>> indices) {
        LinkedList<Integer> temp;
        int tempSizePrev;
        int tempSizeCur;
        for (int i = 0; i < indices.size(); i++) {
            for (int j = 1; j < (indices.size() - i); j++) {
                //System.out.println("\"" + indices.get(j - 1) + "\"");
                tempSizePrev = indices.get(j - 1).size();
                tempSizeCur = indices.get(j).size();
                if (tempSizePrev > tempSizeCur) {
                    temp = indices.get(j - 1);
                    indices.set(j - 1, indices.get(j));
                    indices.set(j, temp);
                }
            }
        }
        return indices;
    }
}
