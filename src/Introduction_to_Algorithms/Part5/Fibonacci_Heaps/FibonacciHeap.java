package Introduction_to_Algorithms.Part5.Fibonacci_Heaps;

import java.util.*;

public class FibonacciHeap {
    private Node min = null;
    private int size = 0;

    private class Node {
        int key;
        Node parent;
        Node left;
        Node right;
        Node child;
        int degree;
        boolean mark;

        public Node(int key) {
            this.key = key;
            this.right = this;
            this.left = this;
        }
    }

    public void insert(int key) {
        Node node = new Node(key);
        min = mergeLists(min, node);
        size++;
    }

    public void merge(FibonacciHeap other) {
        min = mergeLists(min, other.min);
        size += other.size;
    }

    public int extractMin() {
        if (min == null) return -1;
        size--;
        Node minNode = this.min;
        if (minNode.child != null) {
            Node child = minNode.child;
            do {
                child.parent = null;
                child = child.right;
            } while (child != minNode.child);
        }
        Node right = minNode.right;
        if (minNode == right) {
            min = null;
        } else {
            min = right;
            consolidate();
        }
        return minNode.key;
    }

    private void consolidate() {
        int maxDegree = (int) Math.floor(Math.log(size) / Math.log(2)) + 1; // Maximum possible degree in the heap

        // Initialize an array to hold roots of trees by degree
        /**
         * The Issue Line:
         *
         * Node[] degreeArray = new Node[maxDegree + 3];
         * Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 5 out of bounds for length 5
         *
         * Node[] degreeArray = new Node[maxDegree + 2];
         * Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 4 out of bounds for length 4
         *
         * Node[] degreeArray = new Node[maxDegree + 1];
         * Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: Index 3 out of bounds for length 3
         */
        Node[] degreeArray = new Node[maxDegree + 10];
        Arrays.fill(degreeArray, null);

        // Traverse the root list and merge trees of the same degree
        Node current = min;
        do {
            Node next = current.right;
            int degree = current.degree;

            // Merge current with any existing tree of the same degree
            while (degreeArray[degree] != null) {
                Node sameDegreeNode = degreeArray[degree];
                if (current.key > sameDegreeNode.key) {
                    Node temp = current;
                    current = sameDegreeNode;
                    sameDegreeNode = temp;
                }
                link(sameDegreeNode, current);
                degreeArray[degree] = null;
                degree++;
            }

            degreeArray[degree] = current;
            current = next;
        } while (current != min);

        // Reconstruct the root list from the degree array
        min = null;
        for (Node node : degreeArray) {
            if (node != null) {
                if (min == null) {
                    min = node;
                } else {
                    min = mergeLists(min, node);
                }
            }
        }
    }

    private void link(Node child, Node parent) {
        // Remove child from the root list
        child.left.right = child.right;
        child.right.left = child.left;

        // Make child a child of parent
        child.parent = parent;
        if (parent.child == null) {
            parent.child = child;
            child.right = child;
            child.left = child;
        } else {
            Node parentChild = parent.child;
            child.right = parentChild.right;
            parentChild.right.left = child;
            parentChild.right = child;
            child.left = parentChild;
        }

        parent.degree++;
        child.mark = false; // Child loses its mark when it becomes a child
    }

    private Node mergeLists(Node a, Node b) {
        if (a == null && b == null) return null;
        if (a != null && b == null) return a;
        if (a == null) return b;
        Node temp = a.right;
        a.right = b.right;
        a.right.left = a;
        b.right = temp;
        b.right.left = b;
        return a.key < b.key ? a : b;
    }
}

class Driver {
    public static void main(String[] args) {
        FibonacciHeap heap = new FibonacciHeap();

        heap.insert(10);
        heap.insert(20);
        heap.insert(30);

        /**
         * The minimum value in the Fibonacci heap after insertions is: 10
         * The minimum value in the Fibonacci heap after merging with another heap is: 5
         */

        System.out.println("The minimum value in the Fibonacci heap after insertions is: " + heap.extractMin());

        FibonacciHeap anotherHeap = new FibonacciHeap();
        anotherHeap.insert(5);
        anotherHeap.insert(15);

        heap.merge(anotherHeap);

        System.out.println("The minimum value in the Fibonacci heap after merging with another heap is: " + heap.extractMin());
    }
}