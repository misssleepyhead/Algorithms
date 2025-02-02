/**Web exercises 4: Identity
 * Given an array of N distinct int(positive or negative) in ascending order
 * find an index i s.t a[i] = i if such an index exists, use binary search*/

public class Identity {
    public static int findIdentity(int[]nums){
        int lo=0,hi= nums.length-1;
        while(lo<=hi){
            int mid = lo+(hi-lo)/2;
            if(nums[mid]>mid){ // if the mid> nums[mid], it means the order is def. broken in the right part
                hi=mid-1; // so search the left / smaller part
            } else if (nums[mid]<mid) {
                lo=mid+1;
            }else{
                return mid; // found
            }
        }
        return -1;

    }

    public static void main(String[] args) {
        int[] nums = {-3, -1, 1, 3, 5, 7};
        System.out.println(findIdentity(nums));  // Output: 3
    }
}
