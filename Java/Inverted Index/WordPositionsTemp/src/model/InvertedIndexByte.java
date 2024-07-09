package model;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class InvertedIndexByte implements Serializable {

    private List<String> documents;
    private Map<String, LinkedList<Byte>> index;
    private LinkedList<String> stopWords;

    public InvertedIndexByte(List<String> documents,
                             Map<String, LinkedList<Integer>> index,
                             LinkedList<String> stopWords) {

        this.documents = documents;
        this.stopWords = stopWords;
        this.index = new LinkedHashMap<String, LinkedList<Byte>>();

        for (String k : index.keySet()) {
            this.index.put(k, (LinkedList<Byte>) Intervals.byteEncode(index.get(k)));
        }
    }
}
