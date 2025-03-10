package Lecture7;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**interview question 3:
 * Decimal dominants. Given an array with n keys, design an algorithm to find all values that occur more than
 * n/10 times. The expected running time of your algorithm should be linear.*/
public class DecimalDominantsArray {
    public static List<Integer> findDecimalDominants(int[] nums){
        int n= nums.length;
        int k=n/10;

        int[] candidates = new int[9];
        int[] counts = new int[9];
        int candidateCount=0;
        boolean found = false;
        // step 1: Identify potential candidates
        for(int num:nums){

            for(int i=0;i<candidateCount;i++){
                if(candidates[i]==num){
                    counts[i] ++;
                    found=true;
                    break;
                }
            }

            // if not found, insert a new candidate
            if(!found){
                if(candidateCount<9){
                    candidates[candidateCount]=num;
                    counts[candidateCount]=1;
                    candidateCount++;
                }else {
                    // reduce all counts by 1
                    for(int i=0;i<9;i++){
                        counts[i]--;
                    }
                    // Remove candidates with zero count
                    int newCount = 0;
                    for (int i = 0; i < 9; i++) {
                        if (counts[i] > 0) {
                            candidates[newCount] = candidates[i];
                            counts[newCount] = counts[i];
                            newCount++;
                        }
                    }
                    candidateCount = newCount;
                }
            }
        }
        // Step 2: Verify actual counts
        Arrays.fill(counts, 0); // Reset counts for verification
        for (int num : nums) {
            for (int i = 0; i < candidateCount; i++) {
                if (candidates[i] == num) {
                    counts[i]++;
                }
            }
        }

        // Collect elements appearing more than n/10 times
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < candidateCount; i++) {
            if (counts[i] > k) {
                result.add(candidates[i]);
            }
        }

        return result;

    }
    public static void main(String[] args) {
        int[] nums = {1, 2, 3, 1, 2, 1, 1, 3, 4, 1, 5, 6, 7, 8, 1, 9, 1, 1};
        System.out.println("Decimal dominants: " + findDecimalDominants(nums));
    }
}
