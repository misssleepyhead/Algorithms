import java.util.Hashtable;


/**Sparse vector example, use symbol table to represent nonzeros*/
public class SparseVector {
    private Hashtable<Integer,Double> v; // use hashtable cause order is not important


    public SparseVector() {
        v= new Hashtable<>();
    }

    public void put(int i, double x){
        v.put(i,x);
    }
    public int size(){
        return v.size();
    }

    public double get(int i){
        if(!v.containsKey(i)) return 0.0;
        else return v.get(i);
    }

    public double dot(double[] that){
        double sum=0.0;
        for(int i:v.keySet()){
            sum+=that[i]*this.get(i);
        }
        return sum;
    }
}
