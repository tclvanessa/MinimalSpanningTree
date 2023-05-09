package mstAlgorithms;

import graph.Graph;

/** Subclass of MSTAlgorithm. Uses Prim's algorithm to compute MST of the graph. */
public class PrimAlgorithm extends MSTAlgorithm {
    private int sourceVertex;
    private Graph graph;
    private boolean[] added;
    private int[] cost;
    private int[] path;

    /**
     * Constructor for PrimAlgorithm. Takes the graph
     * @param graph input graph
     * @param sourceVertex the first vertex of MST
     */
    public PrimAlgorithm(Graph graph, int sourceVertex) {
        super(graph);
        this.sourceVertex = sourceVertex;
    }

    /**
     * Compute minimum spanning tree for this graph using Prim's algorithm.
     * Add edges of MST using the addMSTEdge method inherited from the parent
     * class MSTAlgorithm.
     * */
    @Override
    public void computeMST() {
        // FILL IN CODE
        int g[][] = new int[numNodes()][numNodes()];
        boolean added[] = new boolean[numNodes()];
        int cost[] = new int[numNodes()];
        int path[] = new int[numNodes()];

        for (int i = 0; i < numNodes(); i++) {
            added[i] = false;
            cost[i] = Integer.MAX_VALUE;
            path[i] = -1;
        }
        added[sourceVertex] = true;
        cost[sourceVertex] = 0;


        for (int i = 0; i < numNodes() - 1; i++) {
            int v = findMinimumNonAddedVertex(added, cost);
            added[v] = true;

            for (int u = 0; u < numNodes(); u++) {
                if (!added[u]) {
                    if (cost[u] > g[v][u]) {
                        cost[u] = g[v][u];
                        path[u] = v;
                    }
                }
            }
        }
    }

    private int findMinimumNonAddedVertex(boolean[] added, int[] cost) {
        int min = Integer.MAX_VALUE;
        int minIndex = 0;
        for (int i = 0; i < numNodes(); i++) {
            if (added[i] == false && cost[i] < min) {
                min = cost[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

}
