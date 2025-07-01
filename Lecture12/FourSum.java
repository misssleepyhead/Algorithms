import edu.princeton.cs.algs4.In;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Interview questions 1: 4 sum
 * Given an array a[] of n integers, determine if there exist distinct indices i,j,l,k
 * s.t. a[i] + a[j] = a[k]+a[l]. Design an algorithm for the 4-SUM problem that takes time proportional to n^2
 * <p>
 * <p>
 * Solution:
 * 1. For every i,j index, where i< j, compute a[i]+a[j].
 * 2. Use hash table, store each sum (a[i] + a[j]) in the bucket as key, and each key's value is the list of index
 * 3. if there are at least to non-overlapping pairs, that is the answer
 */
public class FourSum {
    static class Pair {
        int i, j;

        Pair(int i, int j) {
            this.i = i;
            this.j = j;
        }
    }


    public boolean fourSum(int[] a) {
        HashMap<Integer, List<Pair>> map = new HashMap<>();

        // 1. store all unique pairs
        for (int i = 0; i < a.length; i++) {
            for (int j = i + 1; j < a.length; j++) {
                int sum = a[i] + a[j];
                map.computeIfAbsent(sum, x -> new ArrayList<>()).add(new Pair(i, j));
            }
        }

        //  2. for each sum(key), look for two non-overlapping pairs
        for (List<Pair> pairs : map.values()) {
            if (pairs.size() >= 2) {
                for (int p = 0; p < pairs.size(); p++) {
                    for (int q = p + 1; q < pairs.size(); q++) {
                        Pair first = pairs.get(p);
                        Pair second = pairs.get(q);
                        if (first.i != second.i && first.i != second.j && first.j != second.i && first.j != second.j) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }
}
