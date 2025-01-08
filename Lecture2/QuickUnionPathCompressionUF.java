
/** 1.5.12
 * Modify QuickUnionUF.java to include path compression,
 * by adding a loop to find() that links every sie on the path from p to the root.*/
public class QuickUnionPathCompressionUF {
    private int[] id;

    public QuickUnionPathCompressionUF(int N) {
        id=new int[N];
        for(int i=0;i<N;i++){
            id[i] = i;
        }
    }
    // keep upward looking until the id is itself
    private int root(int i){
        int root=i;
        while(root!=id[root]){
            root=id[root];
        }
        while(i!=root){
            int newI=id[i];
            id[i]=root;
            i=newI;
        }
        return i;
    }

    public boolean connected(int p, int q){
        return root(p)==root(p);
    }

    public void union(int p, int q){
        int proot = root(p);
        int qroot = root(q);
        if(proot==qroot){
            return;
        }

    }

    public int find(int p){
        return root(p);
    }

    public int count(){
        return id.length;
    }

}
