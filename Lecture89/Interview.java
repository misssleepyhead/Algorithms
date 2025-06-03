import edu.princeton.cs.algs4.In;

import java.io.Flushable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Interview questions of class
public class Interview {
    /**
     * module 10, interview question: Document search:  Design an algorithm that takes a sequence of n document words and a sequence of m
     * query words and find the shortest interval in which the m query words appear in the document in the order given.
     * The length of an interval is the number of words in that interval
     */
    public static int documentSearch(String[] document, String[] query) {
        // 1. create a map of query words to their indices in the document
        Map<String, List<Integer>> map = new HashMap<>();
        map = scan(map, document, query);

        int minIntervalLength = Integer.MAX_VALUE;

        // 2. Iterate through all occurences of the first query word(query[0])
        List<Integer> firstWordOccur = map.get(query[0]);
        for (int i = 0; i < firstWordOccur.size(); i++) {
            int currentStartIdx = firstWordOccur.get(i);
            int prev = currentStartIdx;
            int end = currentStartIdx;
            boolean found = true;
            for (int q = 1; q < query.length; q++) {
                List<Integer> posList = map.get(query[q]);
                int next = findNextOccurrence(posList, currentStartIdx);
                if (next != -1) {
                    prev = next;
                    end = next;
                } else {
                    found = false;
                    break;
                }
            }

        }

    }

    private static int findNextOccurrence(List<Integer> occurrences, int start) {
        for (int index : occurrences) {
            if (index > start) {
                return index;
            }
        }
        return -1; // not found
    }

    private static Map<String, List<Integer>> scan(Map<String, List<Integer>> map, String[] document, String[] query) {
        // create map by each query word and empty list
        for (String q : query) map.put(q, new ArrayList<>());

        // add pos in document of each word in query
        for (int i = 0; i < document.length; i++) {
            if (map.containsKey(document[i])) {
                map.get(document[i]).add(i);
            }
        }
        return map;
    }


}

