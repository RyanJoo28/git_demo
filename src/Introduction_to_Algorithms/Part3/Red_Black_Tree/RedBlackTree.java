package Introduction_to_Algorithms.Part3.Red_Black_Tree;

class Node {
    int data;
    Node parent;
    Node left;
    Node right;
    int color;

    public Node(int data) {
        this.data = data;
        left = right = parent = null;
    }
}

class RedBlackTree {
    private Node root;
    private Node TNULL;

    private void preOrderHelper(Node node) {
        if (node != TNULL) {
            System.out.print(node.data + " ");
            preOrderHelper(node.left);
            preOrderHelper(node.right);
        }
    }

    private void inOrderHelper(Node node) {
        if (node != TNULL) {
            inOrderHelper(node.left);
            System.out.print(node.data + " ");
            inOrderHelper(node.right);
        }
    }

    private void postOrderHelper(Node node) {
        if (node != TNULL) {
            postOrderHelper(node.left);
            postOrderHelper(node.right);
            System.out.print(node.data + " ");
        }
    }

    // Balance the tree after deletion of a node
    private void fixDelete(Node x) {
        // More code here
    }

    // Balance the tree after insertion of a node
    private void fixInsert(Node k) {
        // More code here
    }

    public void insert(int key) {
        Node node = new Node(key);
        if (root == null) {
            root = node;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (key < current.data) {
                    current = current.left;
                    if (current == null) {
                        parent.left = node;
                        return;
                    }
                } else {
                    current = current.right;
                    if (current == null) {
                        parent.right = node;
                        return;
                    }
                }
            }
        }
    }

    public void deleteNode(int key) {
        root = deleteRec(root, key); // This method is not defined
    }

    private Node deleteRec(Node root, int key) {
        // Base Case: If the tree is empty
        if (root == null) {
            return root;
        }

        // Otherwise, recur down the tree
        if (key < root.data) {
            root.left = deleteRec(root.left, key);
        } else if (key > root.data) {
            root.right = deleteRec(root.right, key);

            // If key is same as root's key, then this is the node to be deleted
        } else {
            // Node with only one child or no child
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            // Node with two children: Get the inorder successor (smallest in the right subtree)
            root.data = minValue(root.right);

            // Delete the inorder successor
            root.right = deleteRec(root.right, root.data);
        }

        return root;
    }

    private int minValue(Node root) {
        int minV = root.data;
        while (root.left != null) {
            minV = root.left.data;
            root = root.left;
        }
        return minV;
    }

    public void prettyPrint() {
        printRec(root);
    }

    /* inorder traversal */
    private void printRec(Node root) {
        if (root != null) {
            printRec(root.left);
            System.out.print(root.data + " ");
            printRec(root.right);
        }
    }
}

class Driver {
    public static void main(String[] args) {
        RedBlackTree redBlackTree = new RedBlackTree();

        redBlackTree.insert(7);
        redBlackTree.insert(3);
        redBlackTree.insert(4);
        redBlackTree.insert(9);
        redBlackTree.insert(12);
        redBlackTree.insert(16);
        redBlackTree.insert(15);

        /**
         * 3 4 7 9 12 15 16
         */

        redBlackTree.prettyPrint();
        System.out.println();
    }
}