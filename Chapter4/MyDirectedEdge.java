/***
 * 4.4 textbook imp
 * directed weighted edge
 */
public class MyDirectedEdge {
    private final int v; //edge source
    private final int w; // edge target
    private final double weight;

    public MyDirectedEdge(int v,int w, double weight){
        this.v=v;
        this.w=w;
        this.weight=weight;
    }

    public double getWeight() {
        return weight;
    }
    public int from(){
        return v;
    }

    public int to(){
        return w;
    }

    public String toString(){
        return String.format("%d -> %d %.2f", v,w,weight);
    }
}
