package Introduction_to_Algorithms.Part5.Disjoint_Set;

import java.util.HashMap;
import java.util.Map;

class DisjointSetWithArray {
    private int[] parent;
    private int[] rank;

    public DisjointSetWithArray(int size) {
        parent = new int[size];
        rank = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]); // Path compression
        }
        return parent[x];
    }

    public void union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        if (rootX != rootY) {
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
        }
    }
}

// Disjoint Set Forest
class DisjointSetNode {
    int data;
    DisjointSetNode parent;
    int rank;

    DisjointSetNode(int data) {
        this.data = data;
        this.parent = this;
        this.rank = 0;
    }
}

class DisjointSetForest {
    private Map<Integer, DisjointSetNode> nodes;

    public DisjointSetForest() {
        nodes = new HashMap<>();
    }

    public void makeSet(int data) {
        DisjointSetNode node = new DisjointSetNode(data);
        nodes.put(data, node);
    }

    public DisjointSetNode find(DisjointSetNode node) {
        if (node != node.parent) {
            node.parent = find(node.parent); // Path compression
        }
        return node.parent;
    }

    public int find(int data) {
        DisjointSetNode node = nodes.get(data);
        return find(node).data;
    }

    public void union(int data1, int data2) {
        DisjointSetNode parent1 = find(nodes.get(data1));
        DisjointSetNode parent2 = find(nodes.get(data2));

        if (parent1 == parent2) {
            return; // Same set
        }

        if (parent1.rank >= parent2.rank) {
            parent2.parent = parent1;
            if (parent1.rank == parent2.rank) {
                parent1.rank++;
            }
        } else {
            parent1.parent = parent2;
        }
    }
}

// Disjoint Set with Linked List
class Node {
    int data;
    Node next;
    Node(int data) {
        this.data = data;
        this.next = null;
    }
}

class DisjointSetWithLinkedList {
    private Node[] sets;

    public DisjointSetWithLinkedList(int size) {
        sets = new Node[size];
        for (int i = 0; i < size; i++) {
            sets[i] = new Node(i);
        }
    }

    private Node find(Node node) {
        if (node != node.next) {
            node.next = find(node.next); // Path compression
        }
        return node.next;
    }

    public int find(int x) {
        return find(sets[x]).data;
    }

    public void union(int x, int y) {
        Node rootX = find(sets[x]);
        Node rootY = find(sets[y]);
        if (rootX != rootY) {
            Node temp = rootX;
            while (temp.next != temp) {
                temp = temp.next;
            }
            temp.next = rootY.next;
            rootY.next = rootX;
        }
    }
}

class Driver {
    public static void main(String[] args) {
        DisjointSetWithArray disjointSetWithArray = new DisjointSetWithArray(6);
        disjointSetWithArray.union(0, 1);
        disjointSetWithArray.union(1, 2);
        disjointSetWithArray.union(3, 4);
        disjointSetWithArray.union(4, 5);

        /**
         * 0
         * 3
         * true
         */

        System.out.println(disjointSetWithArray.find(2)); // Output: 0 (representing the representative element of the set containing element 2)
        System.out.println(disjointSetWithArray.find(5)); // Output: 3 (representing the representative element of the set containing element 5)
        System.out.println(disjointSetWithArray.find(3) == disjointSetWithArray.find(5)); // Output: true (indicating that elements 3 and 5 are in the same set)
    }
}