package mstAlgorithms;

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
        // FILL IN CODE

        // Create sets
        DisjointSets sets = new DisjointSets();
        sets.createSets(numNodes());

        List<graph.Edge> sortList = new ArrayList<>();
        int count = 0;
        while (count < numNodes()) {
            graph.Edge head = getFirstEdge(count++);
            while (head != null) {
                sortList.add(head);
                head = head.next();
            }
        }
        // Sort edges in increasing order
        Collections.sort(sortList);


        for (int i = 0; i < sortList.size(); i++) {
            graph.Edge nextEdge = sortList.get(i);
            int x = sets.find(nextEdge.getId1());
            int y = sets.find(nextEdge.getId2());

            if (x != y) {
                addMSTEdge(nextEdge);
                sets.union(x, y);
            }
        }
        printMST();

    }
}

