package Introduction_to_Algorithms.Part6.Graph_Representation;

import java.util.*;

class Graph {
    private List<List<Integer>> adjList;
    private int[][] adjMatrix;

    // Constructor for adjacency list representation (Adjacency List)
    public Graph(int vertices) {
        adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }

    // Add edge for adjacency list
    public void addEdgeList(int source, int destination, boolean isDirected) {
        adjList.get(source).add(destination);
        if (!isDirected) {
            adjList.get(destination).add(source);
        }
    }

    // Display adjacency list
    public void printAdjList() {
        System.out.println("Adjacency List:");
        for (int i = 0; i < adjList.size(); i++) {
            System.out.println(i + " --> " + adjList.get(i));
        }
    }

    // Constructor for adjacency matrix representation (Adjacency Matrix)
    public Graph(int vertices, boolean isDirected) {
        adjMatrix = new int[vertices][vertices];
    }

    // Add edge for adjacency matrix
    public void addEdgeMatrix(int source, int destination, boolean isDirected) {
        adjMatrix[source][destination] = 1;
        if (!isDirected) {
            adjMatrix[destination][source] = 1;
        }
    }

    // Display adjacency matrix
    public void printAdjMatrix() {
        System.out.println("Adjacency Matrix:");
        for (int[] row : adjMatrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}

class Driver {
    public static void main(String[] args) {
        /**
         * Adjacency List:
         * 0 --> [1]
         * 1 --> [0, 2]
         * 2 --> [1]
         * 3 --> []
         * 4 --> []
         * Adjacency Matrix:
         * [0, 1, 0, 0, 0]
         * [1, 0, 1, 0, 0]
         * [0, 1, 0, 0, 0]
         * [0, 0, 0, 0, 0]
         * [0, 0, 0, 0, 0]
         */

        // Create a graph with 5 vertices
        Graph graph = new Graph(5);

        // Add edges to the graph
        graph.addEdgeList(0, 1, false); // Add undirected edge between 0 and 1
        graph.addEdgeList(1, 2, false); // Add undirected edge between 1 and 2
        // ... (add other edges as needed)

        // Print adjacency list
        graph.printAdjList();

        // Create a graph with 5 vertices for adjacency matrix
        Graph matrixGraph = new Graph(5, false);

        // Add edges to the matrix graph
        matrixGraph.addEdgeMatrix(0, 1, false); // Add undirected edge between 0 and 1
        matrixGraph.addEdgeMatrix(1, 2, false); // Add undirected edge between 1 and 2
        // ... (add other edges as needed)

        // Print adjacency matrix
        matrixGraph.printAdjMatrix();
    }
}