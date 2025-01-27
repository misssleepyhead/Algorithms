import edu.princeton.cs.algs4.BinarySearch;
import edu.princeton.cs.algs4.In;

public class ThreeSumFast {
    public static int count(int[] a){
        // count triple that sum to 0
        int N = a.length;
        int cnt=0;
        for(int i=0;i<N;i++){
            for(int j=i+1;j<N;j++){
                if(BinarySearch.rank(-a[i]-a[i], a)>j){
                    cnt++;
                }
            }
        }
        return cnt;
    }

    public static void main(String[] args) {
        int[] a = In.readInts(args[0]);
        System.out.println(count(a));
    }
}
