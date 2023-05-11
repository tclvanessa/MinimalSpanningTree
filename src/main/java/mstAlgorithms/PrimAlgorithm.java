package mstAlgorithms;

import graph.Edge;
import graph.Graph;

/** Subclass of MSTAlgorithm. Uses Prim's algorithm to compute MST of the graph. */
public class PrimAlgorithm extends MSTAlgorithm {
    private int sourceVertex;

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
        // Arrays of the table variables
        boolean added[] = new boolean[numNodes()];
        int cost[] = new int[numNodes()];
        int path[] = new int[numNodes()];

        // Initialize table
        for (int i = 0; i < numNodes(); i++) {
            added[i] = false;
            cost[i] = Integer.MAX_VALUE;
            path[i] = -1;
        }
        // Set source vertex
        added[sourceVertex] = true;
        cost[sourceVertex] = 0;

        // Repeat numNodes times
        for (int i = 0; i < numNodes(); i++) {
            int v = findMinimumNonAddedVertex(added, cost); // Find the minimum non-added vertex v
            added[v] = true;

            // Should not be adding edges that paths aren't reassigned yet.
            if (path[v] != -1) {
                Edge edgeMST = new Edge(v, path[v], cost[v]); // Create new edge with the minimum added vertex.
                addMSTEdge(edgeMST);
            }

            // Gets the head of the linked lists that contains outgoing edges of vertex v
            Edge head = getFirstEdge(v);
            while (head != null) {
                // If not added yet, check if cost and path of the neighbor (Id2) needs to be updated.
                if (!added[head.getId2()]) {
                    if (cost[head.getId2()] > head.getCost()) {
                        cost[head.getId2()] = head.getCost();
                        path[head.getId2()] = v;
                    }
                }
                head = head.next();
            }
        }
        System.out.println("Prim's —————————————");
        printMST();
    }

    /**
     * Helper method of computeMST() for Prim's.
     * Finds the minimum non-added vertex using the added and cost arrays.
     * Returns the index of the minimum.
     * @param added Array storing boolean values of the vertices
     * @param cost Array storing the cost of the vertices
     * @return min index
     * */
    private int findMinimumNonAddedVertex(boolean[] added, int[] cost) {
        int min = Integer.MAX_VALUE;
        int minIndex = 0;

        // Iterate through all the vertices,
        // and if it is not added and the cost is less than the min,
        // then update the min and minIndex variable.
        for (int i = 0; i < numNodes(); i++) {
            if (!added[i] && cost[i] < min) {
                min = cost[i];
                minIndex = i;
            }
        }
        return minIndex;
    }

}
