public class FourSum {
    /** brute force to find distinct 4 tuples that sums to 0*/
    public static int bruteFourSum(int[] nums){
        int cnt=0;
        int n = nums.length;
        for(int i=0;i<n;i++){
            for(int j=i+1;j<n;j++){
                for(int k=j+1;k<n;k++){
                    for(int u=k+1;u<n;u++){
                        if(nums[i]+nums[j]+nums[k]+nums[u]==0){
                            cnt++;
                        }
                    }
                }
            }
        }
        return cnt;
    }
}
