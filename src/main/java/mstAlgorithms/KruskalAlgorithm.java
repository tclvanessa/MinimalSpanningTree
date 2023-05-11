package mstAlgorithms;

import graph.Edge;
import graph.Graph;
import sets.DisjointSets;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Subclass of MSTAlgorithm. Computes MST of the graph using Kruskal's algorithm. */
public class KruskalAlgorithm extends MSTAlgorithm {

    /**
     * Constructor for KruskalAlgorithm. Takes the graph
     * @param graph input graph
     */
    public KruskalAlgorithm(Graph graph) {
        super(graph);
    }

    /**
     * Compute minimum spanning tree for this graph.
     * Add edges of MST using the addMSTEdge method inherited from the parent
     * class MSTAlgorithm.
     * Should use Kruskal's algorithm and DisjointSets class.
     */
    @Override
    public void computeMST() {
        // Create sets
        DisjointSets sets = new DisjointSets();
        sets.createSets(numNodes());

        // Create ArrayList to store the edges
        List<graph.Edge> sortList = new ArrayList<>();
        int count = 0;
        // Iterate through all the linked list of edges
        while (count < numNodes()) {
            Edge head = getFirstEdge(count++);
            while (head != null) {
                sortList.add(head);
                head = head.next();
            }
        }
        // Sort edges in increasing order
        Collections.sort(sortList);

        // For loop to compute the representatives of the sets that x and y belong to
        for (int i = 0; i < sortList.size(); i++) {
            Edge nextEdge = sortList.get(i);
            int x = sets.find(nextEdge.getId1());
            int y = sets.find(nextEdge.getId2());

            // If x and y do not belong to the same set,
            // add to MST and merge the sets that they belong to
            if (x != y) {
                addMSTEdge(nextEdge);
                sets.union(x, y);
            }
        }
        System.out.println("Kruskal's ————————————");
        printMST();
    }
}

