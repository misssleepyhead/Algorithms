/**
 * Network-flow algorithm page.897
 */
public class MyFlowEdge {
    private final int v; // edge source
    private final int w; // edge target
    private final double capacity;
    private double flow;

    public MyFlowEdge(int v, int w, double capacity) {
        this.v = v;
        this.w = w;
        this.capacity = capacity;
        this.flow = 0.0;
    }

    public int from() {
        return v;
    }

    public int to() {
        return w;
    }

    public double capacity() {
        return capacity;
    }

    public double flow() {
        return flow;
    }

    public double residualCapacityTo(int vertex) {
        if (vertex == v) return flow;
        else if (vertex == w) return capacity - flow;
        else throw new RuntimeException("Inconsistent edge");
    }

    public void addResidualFlowTo(int vertex, double delta){
        if(vertex==v) flow-= delta;
        else if (vertex==w) flow +=delta;
        else throw new RuntimeException("Inconsistent edge");
    }

}
