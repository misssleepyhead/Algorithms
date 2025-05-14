
/** Exercise: Give the sequence of subarray sizes after each merge performed by both the top-down and the bottom-up mergesort algorithms, for n = 39
 * imp top down merge and bottom up merge, print out the size of input of each merge*/
public class MergeSizes {
    public static void topDownMergeSort(Comparable[] a){
        topDownMergeSort(0,a.length-1);

    }

    private static void topDownMergeSort(int lo, int hi){
        if(hi<=lo) return;
        int mid = lo +(hi-lo)/2;
        topDownMergeSort(lo,mid);
        topDownMergeSort(mid+1,hi);
        merge(lo,mid,hi);
    }

    public static void bottomUpMergeSort(Comparable[] a){
        int n=a.length;
        for(int len=1;len<n;len*=2){
            for(int lo=0;lo<n-len;lo+=len+len){
                int mid=lo+len-1;
                int hi=Math.min(lo+len+len-1,n-1);
                merge(lo,mid,hi);
            }
        }
    }





    // print the size of subarray
    private static void merge(int lo,int mid, int hi){
        System.out.print(hi-lo+1);
        System.out.print(" ");
    }

    public static void main(String[] args) {
        int n = 39;
        String[] a = new String[n];
        MergeSizes.topDownMergeSort(a);
        System.out.println();
        MergeSizes.bottomUpMergeSort(a);
        System.out.println();
    }
}
