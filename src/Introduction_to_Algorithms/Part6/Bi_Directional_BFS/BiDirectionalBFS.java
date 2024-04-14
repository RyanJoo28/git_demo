package Introduction_to_Algorithms.Part6.Bi_Directional_BFS;

import java.util.*;

class BiDirectionalBFS {
    static class Graph {
        private int V; // maximum vertex value
        private LinkedList<Integer>[] adj; // adjacent vertices

        public Graph(int V) {
            this.V = V;
            adj = new LinkedList[V];
            for (int i = 0; i < V; ++i) {
                adj[i] = new LinkedList<>();
            }
        }

        public void addEdge(int v, int w) {
            adj[v].add(w); // vertex v -> vertex w
        }

        public int biDirBFS(int start, int end) {
            boolean[] visitedStart = new boolean[V];
            boolean[] visitedEnd = new boolean[V];

            Queue<Integer> queueStart = new LinkedList<>();
            Queue<Integer> queueEnd = new LinkedList<>();

            queueStart.add(start); //
            visitedStart[start] = true;

            queueEnd.add(end); //
            visitedEnd[end] = true;

            while (!queueStart.isEmpty() && !queueEnd.isEmpty()) {
                /* Dequeue the front nodes from both queues (currStart from queueStart and currEnd from queueEnd) */
                int currStart = queueStart.poll();
                int currEnd = queueEnd.poll();

                /* If no intersection is found, explore neighbors of currStart and currEnd */
                for (int neighbor : adj[currStart]) {
                    if (!visitedStart[neighbor]) { // if it hasn't been visited (!visitedStart[neighbor]), add it to queueStart and mark it as visited in visitedStart
                        queueStart.add(neighbor);
                        visitedStart[neighbor] = true;
                    }
                }

                for (int neighbor : adj[currEnd]) {
                    if (!visitedEnd[neighbor]) { // similarly
                        queueEnd.add(neighbor);
                        visitedEnd[neighbor] = true;
                    }
                }

                /* Check if there's an intersection point by examining if the current start node has been visited from the end side (visitedEnd[currStart]) or if the current end node has been visited from the start side (visitedStart[currEnd]). If so, return the current start node (currStart) as the intersection point */
                if (visitedEnd[currStart] || visitedStart[currEnd]) {
                    return currStart; // Intersection point found
                }
            }

            return -1; // No path found
        }
    }
}

class Driver {
    public static void main(String[] args) {
        int V = 9;
        BiDirectionalBFS.Graph graph = new BiDirectionalBFS.Graph(V);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 5);
        graph.addEdge(2, 6);
        graph.addEdge(3, 7);
        graph.addEdge(4, 8);

        int start = 0;
        int end = 8;

        int intersection = graph.biDirBFS(start, end);

        /**
         * No intersection found.
         */

        if (intersection != -1) {
            System.out.println("Intersection found at node: " + intersection);
        } else {
            System.out.println("No intersection found.");
        }
    }
}