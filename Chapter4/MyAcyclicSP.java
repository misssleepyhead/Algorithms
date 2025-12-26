import edu.princeton.cs.algs4.DirectedEdge;
import edu.princeton.cs.algs4.EdgeWeightedDigraph;
import edu.princeton.cs.algs4.Topological;

/***
 * 4.4 book imp
 * shortest paths in edge weighted DAGs
 */
public class MyAcyclicSP {
    private DirectedEdge[] edgeTo;
    private double[] distTo;

    public MyAcyclicSP(EdgeWeightedDigraph graph, int s) {
        edgeTo = new DirectedEdge[graph.V()];
        distTo = new double[graph.V()];

        for(int v=0;v< graph.V();v++){
            distTo[v]=Double.POSITIVE_INFINITY;
        }
        distTo[s]=0.0;
        Topological topo = new Topological(graph);
        for(int v: topo.order()){
            relax(graph,v);
        }
    }

    private void relax(EdgeWeightedDigraph graph,int v){
        for(DirectedEdge e:graph.adj(v) ){
            int w = e.to();
            if(distTo[w]>distTo[v]+e.weight()){
                distTo[w] = distTo[v]+e.weight();
                edgeTo[w]=e;

            }
        }
    }
}
