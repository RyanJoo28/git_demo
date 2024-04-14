package Introduction_to_Algorithms.Part6.BFS_and_DFS;

import java.util.*;

class Graph {
    private int numVertices;
    /**
     * LinkedList<Integer>[] adjLists: This is an array of LinkedLists. Each index of the array represents a vertex in the graph, and each element in the LinkedList at a given index represents the vertices that the vertex at that index is connected to. This is a more traditional way to implement an adjacency list, especially in languages like Java that have strong support for arrays.
     * LinkedList<LinkedList<Integer>> adjLists: This is a LinkedList of LinkedLists. The outer LinkedList represents the vertices in the graph, and each inner LinkedList represents the vertices that the corresponding vertex is connected to. This implementation is more dynamic and flexible because the size of the LinkedList can change as needed, which is not the case with arrays.
     */
    private LinkedList<Integer> adjLists[];

    Graph(int vertices) {
        numVertices = vertices;
        adjLists = new LinkedList[vertices];
        for (int i = 0; i < vertices; i++) {
            adjLists[i] = new LinkedList<>();
        }
    }

    void addEdge(int src, int dest) {
        adjLists[src].add(dest);
    }

    // BFS
    void BFS(int s) {
        boolean visited[] = new boolean[numVertices];
        LinkedList<Integer> queue = new LinkedList<>();

        visited[s] = true;
        queue.add(s);

        while (queue.size() != 0) {
            s = queue.poll();
            System.out.print(s + " ");

            Iterator<Integer> i = adjLists[s].listIterator();
            while (i.hasNext()) {
                int n = i.next();
                if (!visited[n]) {
                    visited[n] = true;
                    queue.add(n);
                }
            }
        }
    }

    // DFS
    void DFSUtil(int v, boolean visited[]) {
        visited[v] = true;
        System.out.print(v + " ");

        Iterator<Integer> i = adjLists[v].listIterator();
        while (i.hasNext()) {
            int n = i.next();
            if (!visited[n]) {
                DFSUtil(n, visited);
            }
        }
    }

    void DFS(int v) {
        boolean visited[] = new boolean[numVertices];
        DFSUtil(v, visited);
    }
}

class Driver {
    public static void main(String args[]) {
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        /**
         * Breadth First Traversal (starting from vertex 2):
         * 2 0 3 1
         * Depth First Traversal (starting from vertex 2):
         * 2 0 1 3
         */

        System.out.println("Breadth First Traversal (starting from vertex 2):");
        g.BFS(2);

        System.out.println("\nDepth First Traversal (starting from vertex 2):");
        g.DFS(2);
    }
}