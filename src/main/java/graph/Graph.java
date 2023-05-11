package graph;

import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * A class that represents a graph: stores the array of city nodes, the
 * adjacency list, as well as the hash table that maps city names to node ids.
 * Nodes are cities (of type CityNode); edges connect them and the cost of each edge
 * is the distance between the cities.
 * Fill in code in this class. You may add additional methods and variables.
 * You are required to implement a MinHeap from scratch, instead of using Java's built in PriorityQueue.
 */
public class Graph {
    private CityNode[] nodes; // nodes of the graph
    private Edge[] adjacencyList; // adjacency list; for each vertex stores a linked list of edges
    private int numEdges; // total number of edges
    private int countNodes; // count number of nodes
    private HashMap cityToNodeID; // hashmap that maps each city name to node id
    private int ID; // id of city

    /**
     * Constructor. Read graph info from the given file,
     * and create nodes and edges of the graph.
     *
     * @param filename name of the file that has nodes and edges
     */
    public Graph(String filename) {
        try (FileReader f = new FileReader(filename)) {
            BufferedReader br = new BufferedReader(f);

            // Variables
            String line;
            int numLines = 0;
            String arc = "ARCS";
            int nextData = Integer.MAX_VALUE;

            // Variables that represent a graph
            this.numEdges = 0;
            this.countNodes = 0;
            this.cityToNodeID = new HashMap();
            this.ID = 0;

            // While line in the file doesn't equal null
            while ((line = br.readLine()) != null) {
                numLines++;

                // Get the number of vertices from the USA.txt line 2
                if (numLines == 2) {
                    int numNodes = Integer.parseInt(line);
                    this.nodes = new CityNode[numNodes];
                    this.adjacencyList = new Edge[numNodes];
                }

                // Reading the NODES
                // numLines needs to be greater than 2 because the data starts on the third line.
                if (numLines > 2 && !line.equals(arc) && numLines < nextData) {
                    String[] data = line.split("\\s+");

                    String cityName = data[0];
                    double xCoor = Double.parseDouble(data[1]);
                    double yCoor = Double.parseDouble(data[2]);
                    CityNode node = new CityNode(cityName, xCoor, yCoor);
                    addCityNode(node); // Adds node to nodes array containing the nodes of graph
                    this.cityToNodeID.put(cityName, this.ID++); // Adds the city name and its id to the hashmap
                }

                // If the line equals "ARCS," continue in the loop
                if (line.equals(arc)) {
                    nextData = numLines + 1;
                    continue;
                }

                // Reading the ARCS
                if (numLines >= nextData) {
                    String[] data = line.split("\\s+");

                    String origin = data[0];
                    String dest = data[1];
                    int cost = Integer.parseInt(data[2]);

                    int originID = (int) this.cityToNodeID.get(origin);
                    int destID = (int) this.cityToNodeID.get(dest);
                    Edge edge0 = new Edge(originID, destID, cost);
                    Edge edge1 = new Edge(destID, originID, cost); // Edge going in opposite direction
                    addEdge(originID, edge0);
                    addEdge(destID, edge1);
                }
            }
        } catch (IOException e) {
            System.out.println("No file found.");
        }
    }

    /**
     * Helper method of the Graph constructor.
     * Adds the CityNode to the nodes array containing the nodes of the graph.
     * @param node city node
     * */
    public void addCityNode(CityNode node) {
        nodes[this.countNodes++] = node;
    }

    /**
     * Helper method of the Graph constructor.
     * Adds the edge to the adjacency list at the given vertex
     * @param nodeID ID of city node
     * @param edge edge
     * */
    public void addEdge(int nodeID, Edge edge) {
        // If the index of adjacency list at nodeID isn't null,
        // iterate through the linked list to add the edge to the end
        if (this.adjacencyList[nodeID] != null) {
            Edge curr = this.adjacencyList[nodeID];
            while (curr.next() != null) {
                curr = curr.next();
            }
            curr.setNext(edge);
        } else {
            this.adjacencyList[nodeID] = edge; // Adds edge to adjacency list if there are no edges there already
        }
        this.numEdges++;
    }

    /**
     * Return the number of nodes in the graph
     * @return number of nodes
     */
    public int numNodes() {
        return nodes.length;
    }

    /** Return the head of the linked list that contains all edges outgoing
     * from nodeId
     * @param nodeId id of the node
     * @return head of the linked list of Edges
     */
    public Edge getFirstEdge(int nodeId) {
        return adjacencyList[nodeId];
    }

    /**
     * Return the edges of the graph as a 2D array of points.
     * Called from GUIApp to display the edges of the graph.
     *
     * @return a 2D array of Points.
     * For each edge, we store an array of two Points, v1 and v2.
     * v1 is the source vertex for this edge, v2 is the destination vertex.
     * This info can be obtained from the adjacency list
     */
    public Point[][] getEdges() {
        if (adjacencyList == null || adjacencyList.length == 0) {
            System.out.println("Adjacency list is empty. Load the graph first.");
            return null;
        }
        Point[][] edges2D = new Point[numEdges][2];
        int idx = 0;
        for (int i = 0; i < adjacencyList.length; i++) {
            for (Edge tmp = adjacencyList[i]; tmp != null; tmp = tmp.next(), idx++) {
                edges2D[idx][0] = nodes[tmp.getId1()].getLocation();
                edges2D[idx][1] = nodes[tmp.getId2()].getLocation();
            }
        }

        return edges2D;
    }

    /**
     * Get the nodes of the graph as a 1D array of Points.
     * Used in GUIApp to display the nodes of the graph.
     * @return a list of Points that correspond to nodes of the graph.
     */
    public Point[] getNodes() {
        if (nodes == null) {
            System.out.println("Array of nodes is empty. Load the graph first.");
            return null;
        }
        Point[] nodes = new Point[this.nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = this.nodes[i].getLocation();
        }

        return nodes;
    }

    /**
     * Used in GUIApp to display the names of the cities.
     * @return the list that contains the names of cities (that correspond
     * to the nodes of the graph)
     */
    public String[] getCities() {
        if (nodes == null) {
            return null;
        }
        String[] labels = new String[nodes.length];
        for (int i = 0; i < nodes.length; i++) {
            labels[i] = nodes[i].getCity();
        }

        return labels;

    }

    /**
     * Return the CityNode for the given nodeId
     * @param nodeId id of the node
     * @return CityNode
     */
    public CityNode getNode(int nodeId) {
        return nodes[nodeId];
    }


}
