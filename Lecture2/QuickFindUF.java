
/** Implement quick-find
 * Using Integer array to represent the data structure
 * if two object have the same id, means they are in the same component*/
public class QuickFindUF {
    private int[] id;

    public QuickFindUF(int N){
        id=new int[N];
        for(int i=0;i<N;i++){
            id[i]=i;
        }
    }
    // Change all elements from the component of p to q's
    public void union(int p, int q){
        int pid = id[p];
        int qid=id[q];
        if(pid==qid){
            return;
        }
        for(int i=0;i<id.length;i++){
            if(id[i]==pid){
                id[i]=qid;
            }
        }
    }

    public boolean connected(int p, int q){
        return id[p]==id[q];
    }

    public int find(int p){
        return id[p];
    }

    public int count(){
        return id.length;
    }

    public static void main(String[] args) {
        QuickFindUF q = new QuickFindUF(10);
        q.union(9,0);
        q.union(3,4);
        q.union(5,8);
        q.union(7,2);
        q.union(2,1);
        q.union(5,7);
        q.union(0,3);
        q.union(4,2);
        System.out.println(q.connected(9,2)); // should be true
        System.out.println(q.connected(3,9));
    }
}
