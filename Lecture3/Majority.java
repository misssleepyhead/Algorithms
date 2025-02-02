import java.util.Arrays;

/**
 * Web exercises: 5. Majority
 * given an array of N strings, An element is a majority if it appears more than N/2 times.
 * Devise an algorithm to identify the majority if it exists.
 * Your algorithm should run in linearithmic time
 */

public class Majority {
    public static String majority(String[] arr) {
        int N = arr.length;
        Arrays.sort(arr); // O(N log N)
        // when the array is sorted, the majority element must always appear at index N/2 If exist
        String candidate = arr[N / 2];
        int cnt = 0;

        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(candidate)) {
                cnt++;
            }
        }
        return (cnt>N/2)?candidate:"No majority element";
    }

    /**6.  this time your algorithm should run in linear time, and only use a constant amount of extra space.
     * Moreover, you may only compare elements for equality, not for lexicographic order
     *
     * using Boyer-Moore Voting Algorithm*/
    public static String majority2(String[] arr){
        String candidate = "";
        int count =0;

        // Step 1: find candidate using boyer moore voting algorithm
        for(String s:arr){
            if(count==0){
                candidate=s;
            }
            count+= (s.equals(candidate))?1:-1;
        }

        // step 2: verify that candidate is truly a majority
        count=0;
        for(String s:arr){
            if(s.equals(candidate)){
                count++;
            }
        }

        return (count> arr.length/2)?candidate:"NO majority element";

    }
    public static void main(String[] args) {
        String[] arr1 = {"apple", "banana", "apple", "apple", "orange", "apple"};
        System.out.println(majority(arr1)); // Output: "apple"
        System.out.println(majority2(arr1));

        String[] arr2 = {"apple", "banana", "apple", "banana", "orange", "apple"};
        System.out.println(majority(arr2)); // Output: "No Majority Element"
        System.out.println(majority2(arr2));
    }
}
