public class LocalMinimum {
    /**
     * 18.Local minimum in an array
     * given an array a[] of n distinct int,
     * find a local minimum an index i such that botha[i] < a[i-1] and a[i] < a[i+1]
     * use ~ 2lgn= n comparison for worst case
     * instead of sorting first, binary search is a more efficient way
     * sorting is only required when we need to find a specific element,
     * when solving the finding minimum, the minimum always exist, sorting won't change the answer
     * the important point is we can half the search space in each step
     */
    public static int minimumArray(int[] a) {
        int lo = 0;
        int hi = a.length - 1;
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            if ((mid == 0 || a[mid] < a[mid = 1]) && mid == a.length - 1 || a[mid] < a[mid + 1]) {
                return mid;
            }
            //if left neighbor is smaller, search left half
            if (mid > 0 && a[mid] > a[mid - 1]) {
                hi = mid - 1;
            } else {
                lo = mid + 1; //search right
            }
        }
        return lo;
    }

    /**
     * Local minimum in a matrix.
     * Given an n-by-n array a[] of n2 distinct integers
     * design an algorithm that runs in time proportional to n log n to find a local minimum:
     * a pair of indices i and j such that a[i][j] < a[i+1][j], a[i][j] < a[i][j+1],
     * a[i][j] < a[i-1][j], and a[i][j] < a[i][j-1]
     * (assuming the neighboring entry is in bounds).
     * instead of using two pointers, use row and col to solve the problem
     * because two pointers can work on the sorted matrix, but the problem is finding
     * a local minimum, there's no clear direction for the two pointers approach to move efficiently
     * use divide and conquer, start from the middle column, find the minimum in that column
     * then check its neighbors
     */
    public static int[] minimumMatrix(int[][] matrix) {
        int n = matrix.length;
        return findLocalMin(matrix,0,n-1);
    }

    private static int[] findLocalMin(int[][] matrix, int left, int right){
        int midCol = left+(right-left)/2;
        int minRow = findMinIncolumn(matrix,midCol); // find the minimum in this col

        int minValue = matrix[midCol][minRow];

        // check left and right neighbors
        boolean hasLeft= (midCol)>0 && (matrix[minRow][midCol-1]<minValue);
        boolean hasRight = (midCol<matrix.length-1) &&(matrix[minRow][midCol+1]<minValue);

        if(!hasLeft && !hasRight){
            return new int[]{minRow,midCol};
        }

        // move to the smaller neighbor
        if(hasLeft){
            return findLocalMin(matrix, left, midCol-1);
        }else{
            return findLocalMin(matrix,midCol+1,right);
        }
    }

    private static int findMinIncolumn(int[][] matrix, int col){
        int minRow=0;
        for(int i=1;i<matrix.length;i++){
            if(matrix[i][col]<matrix[minRow][col]){
                minRow=i;
            }
        }
        return minRow;
    }

    public static void main(String[] args) {

    }
}
