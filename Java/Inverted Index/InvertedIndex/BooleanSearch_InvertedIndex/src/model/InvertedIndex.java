package model;

import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.Serializable;
import java.util.*;

public class InvertedIndex implements Serializable {

    private List<String> documents;
    private Map<String, LinkedList<Integer>> index;

    public InvertedIndex() {
        this.documents = new LinkedList<String>();
        this.index = new LinkedHashMap<String, LinkedList<Integer>>();
    }

    public List<String> getDocuments() {
        return documents;
    }

    public Map<String, LinkedList<Integer>> getIndex() {
        return index;
    }

    public void indexDocument(String path) {
        File file = new File(path);
        try {
            if (file.exists()) {
                this.documents.add(path);
                Scanner scanner = new Scanner(file);
                String tempLine;
                String terms[];
                while (scanner.hasNext()) {
                    tempLine = scanner.nextLine().toLowerCase();
                    terms = tempLine.split("\\W+");
                    for (String tempTerm : terms) {
                        LinkedList<Integer> tempIndex;
                        if (this.index.containsKey(tempTerm)) {
                            tempIndex = this.index.get(tempTerm);
                            if (!tempIndex.contains(this.documents.indexOf(path))) {
                                tempIndex.add(this.documents.indexOf(path));
                            }
                        } else {
                            tempIndex = new LinkedList<Integer>();
                            tempIndex.add(this.documents.indexOf(path));
                        }
                        this.index.put(tempTerm, tempIndex);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("File" + path + " not found!");
        }
    }

    public void indexCollection(String path) {
        File folder = new File(path);
        System.out.println("Index sizes after each file's scan. \n");
        for (String file : folder.list()) {
            File f = new File(path + "/" + file);
            if (!f.isDirectory()) {
                indexDocument(path + "/" + file);
                System.out.println("File " + file + " Index size: " + index.size());
            } else {
                indexCollection(f.getPath());
            }
        }
    }

    private LinkedList<Integer> getIntersection(LinkedList<Integer> list1, LinkedList<Integer> list2) {
        LinkedList<Integer> intersection = new LinkedList<>();
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
        } while (iterator1.hasNext() || iterator2.hasNext());
        //Иначе последнее совпадение может не обработаться.
        if (current1 == current2 && intersection.getLast() != current1) {
            intersection.add(current1);
        }
        return intersection;
    }

    private LinkedList<Integer> getUnion(LinkedList<Integer> list1, LinkedList<Integer> list2) {
        LinkedList<Integer> union = new LinkedList<>();
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
                current2 = iterator2.next();
            }
        } while (iterator1.hasNext() || iterator2.hasNext());
        if (union.getLast() != current1) {
            union.add(current1);
        }
        if (union.getLast() != current2) {
            union.add(current2);
        }
        return union;
    }

    //Обрабатывать в порядке возрастания частот вхождения слов в документы
    //Начать с пересечения двух наименьших списков, продолжить по возрастающей
    public LinkedList<Integer> executeQuery(String query) {
        LinkedList<String> terms;
        LinkedList<Integer> res = new LinkedList<>();
        //Тип запроса.
        //0 - простой запрос из одного терма.
        //1 - многоместная конъюнкция (AND).
        //2 - многоместная дизъюнкция (OR).
        int queryType;
        //'AND' и 'OR' (в верхнем регистре) предполагаются зарезервированными специальными словами.
        if (query.contains(" AND ")) {
            query = query.replaceAll("AND", "");
            queryType = 1;
        } else if (query.contains(" OR ")) {
            query = query.replaceAll("OR", "");
            queryType = 2;
        } else {
            if (this.index.get(query.toLowerCase()) != null) {
                return this.index.get(query.toLowerCase());
            } else {
                return res;
            }
        }
        query = query.toLowerCase();
        terms = new LinkedList<>(Arrays.asList(query.split("\\W+")));
        for (int i = 0; i < terms.size(); i++) {
            terms.set(i, terms.get(i).toLowerCase());
        }
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
                res = new LinkedList<>();
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
        return res;
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
