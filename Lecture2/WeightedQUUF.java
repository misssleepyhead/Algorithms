
/** Weight quick union
 * Keep track of the size of the component
 * when union, connect the smaller component to the larger one*/
public class WeightedQUUF {
    private int[] id;
    private int[] size;

    public WeightedQUUF(int N) {
        id=new int[N];
        size = new int[N];
        for(int i=0;i<N;i++){
            id[i] = i;
            size[i]=1;
        }
    }
    // keep upward looking until the id is itself
    private int root(int i){
        while(id[i]!=i){
            i=id[i];
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
        if(size[p]<size[q]){
            id[proot]=qroot;
            size[qroot] +=size[proot];
        }else{
            size[proot] +=size[qroot];
            id[qroot]=proot;
        }

    }

    public int find(int p){
        return root(p);
    }

    public int count(){
        return id.length;
    }

}
