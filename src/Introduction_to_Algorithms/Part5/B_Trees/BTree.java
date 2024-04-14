package Introduction_to_Algorithms.Part5.B_Trees;

import java.util.ArrayList;
import java.util.List;

class BTree {
    private static final int MIN_DEGREE = 2; // Minimum degree of the B-tree
    private Node root;

    private static class Node {
        int n; // Number of keys currently in the node
        List<Integer> keys;
        List<Node> children;
        boolean leaf;

        Node(boolean leaf) {
            this.leaf = leaf;
            this.keys = new ArrayList<>();
            this.children = new ArrayList<>();
        }
    }

    public BTree() {
        this.root = new Node(true);
    }

    public void insert(int key) {
        Node r = root;
        if (r.n == 2 * MIN_DEGREE - 1) {
            Node s = new Node(false);
            root = s;
            s.children.add(r);
            splitChild(s, 0);
            insertNonFull(s, key);
        } else {
            insertNonFull(r, key);
        }
    }

    private void splitChild(Node x, int i) {
        Node y = x.children.get(i);
        Node z = new Node(y.leaf);
        z.n = MIN_DEGREE - 1;
        for (int j = 0; j < MIN_DEGREE - 1; j++) {
            z.keys.add(y.keys.get(j + MIN_DEGREE));
        }
        if (!y.leaf) {
            for (int j = 0; j < MIN_DEGREE; j++) {
                z.children.add(y.children.get(j + MIN_DEGREE));
            }
        }
        y.n = MIN_DEGREE - 1;
        x.keys.add(i, y.keys.get(MIN_DEGREE - 1));
        x.children.add(i + 1, z);
        x.n = x.n + 1;
    }

    private void insertNonFull(Node x, int key) {
        int i = x.n - 1;
        if (x.leaf) {
            while (i >= 0 && key < x.keys.get(i)) {
                x.keys.add(i + 1, x.keys.get(i));
                i--;
            }
            x.keys.add(i + 1, key);
            x.n = x.n + 1;
        } else {
            while (i >= 0 && key < x.keys.get(i)) {
                i--;
            }
            i++;
            if (x.children.get(i).n == 2 * MIN_DEGREE - 1) {
                splitChild(x, i);
                if (key > x.keys.get(i)) {
                    i++;
                }
            }
            insertNonFull(x.children.get(i), key);
        }
    }

    public boolean search(int key) {
        return search(root, key);
    }

    // search implementation
    private boolean search(Node x, int key) {
        int i = 0;
        while (i < x.n && key > x.keys.get(i)) {
            i++;
        }
        if (i < x.n && key == x.keys.get(i)) {
            return true;
        }
        if (x.leaf) {
            return false;
        }
        return search(x.children.get(i), key);
    }
}

class Driver {
    public static void main(String[] args) {
        BTree bTree = new BTree();
        bTree.insert(10);
        bTree.insert(20);
        bTree.insert(5);
        bTree.insert(6);
        bTree.insert(12);

        /**
         * Is 5 present in the tree? true
         * Is 15 present in the tree? false
         */

        System.out.println("Is 5 present in the tree? " + bTree.search(5));
        System.out.println("Is 15 present in the tree? " + bTree.search(15));
    }
}