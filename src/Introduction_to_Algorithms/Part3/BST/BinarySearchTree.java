package Introduction_to_Algorithms.Part3.BST;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}

class BinarySearchTree {
    Node root;

    void insert(int key) {
        root = insertRec(root, key);
    }

    /* insert implementation */
    Node insertRec(Node root, int key) {
        if (root == null) {
            root = new Node(key);
            return root;
        }
        if (key < root.key)
            root.left = insertRec(root.left, key);
        else if (key > root.key)
            root.right = insertRec(root.right, key);
        return root;
    }

    void deleteKey(int key) {
        root = deleteRec(root, key);
    }

    /* delete implementation */
    Node deleteRec(Node root, int key) {
        if (root == null) return root;
        if (key < root.key)
            root.left = deleteRec(root.left, key);
        else if (key > root.key)
            root.right = deleteRec(root.right, key);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;
            root.key = minValue(root.right);
            root.right = deleteRec(root.right, root.key);
        }
        return root;
    }

    int minValue(Node root) {
        int minV = root.key;
        /* iteration */
        while (root.left != null) {
            minV = root.left.key;
            root = root.left;
        }
        return minV;
    }

    void search(int key) {
        Node node = searchRec(root, key);
        if (node != null)
            System.out.println("Key " + key + " is present in the tree");
        else
            System.out.println("Key " + key + " is not present in the tree");
    }

    /* search implementation */
    Node searchRec(Node root, int key) {
        if (root == null || root.key == key)
            return root;
        if (root.key > key)
            return searchRec(root.left, key);
        return searchRec(root.right, key);
    }

    void BFS() {
        if (root == null)
            return;

        /* level-order traversal */
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);

        while (!nodes.isEmpty()) {
            Node node = nodes.remove();
            System.out.print(node.key + " ");

            if (node.left != null) {
                nodes.add(node.left);
            }

            if (node.right != null) {
                nodes.add(node.right);
            }
        }

        System.out.println();
    }

    void DFS() {
        if (root == null)
            return;

        /* this DFS traversal follows a pre-order traversal (root, left, right) because it processes the right child first before the left child */
        /**
         * Pre-order DFS: Visit the root node first, then recursively visit the left subtree and then the right subtree.
         * In-order DFS: Recursively visit the left subtree first, then visit the root node, and finally the right subtree.
         * Post-order DFS: Recursively visit the left subtree, then the right subtree, and finally visit the root node.
         */
        Stack<Node> nodes = new Stack<>();
        nodes.push(root);

        while (!nodes.isEmpty()) {
            Node node = nodes.pop();
            System.out.print(node.key + " ");

            if (node.right != null) {
                nodes.push(node.right);
            }

            if (node.left != null) {
                nodes.push(node.left);
            }
        }

        System.out.println();
    }
}

class Driver {
    public static void main(String[] args) {
        BinarySearchTree binarySearchTree = new BinarySearchTree();

        /**
         * Key 50 is present in the tree
         * Key 30 is present in the tree
         * Key 100 is not present in the tree
         * Key 20 is not present in the tree
         *
         * 50 30 70 40 60 80
         * 50 30 40 70 60 80
         */

        // Insert keys
        binarySearchTree.insert(50);
        binarySearchTree.insert(30);
        binarySearchTree.insert(20);
        binarySearchTree.insert(40);
        binarySearchTree.insert(70);
        binarySearchTree.insert(60);
        binarySearchTree.insert(80);

        // Search for keys
        binarySearchTree.search(50);
        binarySearchTree.search(30);
        binarySearchTree.search(100);

        // Delete a key
        binarySearchTree.deleteKey(20);

        // Search for the deleted key
        binarySearchTree.search(20);

        System.out.println();

        binarySearchTree.BFS();
        binarySearchTree.DFS();
    }
}