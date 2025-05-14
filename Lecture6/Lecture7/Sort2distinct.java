package Lecture7;
/** Exercises 5:  sorts an array that is known to contain just two distinct key values.*/
public class Sort2distinct {
    private static void sort(Comparable[] a, int lo, int hi){
        // i start at 0 like lt, is because in here we don't store pivot.
        int lt=0, i=0,gt=a.length-1;
        while(i<=gt){
            int cmp=a[i].compareTo(a[lt]); // dynamically treating a[lt] as the pivot at every step
            if(cmp<0) exch(a,lt++,i++);
            else if(cmp>0) exch(a,i,gt--);
            else i++;
        }
    }

    private static void exch(Comparable[] a, int i, int j){
        Comparable swap=a[i];
        a[i]=a[j];
        a[j]=swap;
    }
}
