package Introduction_to_Algorithms.Part3.Interval_Tree;

class Interval {
    int low, high;
}

class Node {
    Interval i;  // 'i' could also be a normal variable
    int max;
    Node left, right;
}

class IntervalTree {
    Node root;

    Node newNode(Interval i) {
        Node temp = new Node();
        temp.i = i;
        temp.max = i.high;
        temp.left = temp.right = null;
        return temp;
    }

    Node insert(Node root, Interval i) {
        // Base case: Tree is empty, new node becomes root
        if (root == null)
            return newNode(i);

        // Get low value of interval at root
        int l = root.i.low;

        // If root's low value is smaller, then new interval goes to left subtree
        if (i.low < l)
            root.left = insert(root.left, i);

            // Else, new node goes to right subtree.
        else
            root.right = insert(root.right, i);

        // Update the max value of this ancestor if needed
        if (root.max < i.high)
            root.max = i.high;

        return root;
    }

    boolean doOverlap(Interval i1, Interval i2) {
        if (i1.low < i2.high && i2.low < i1.high)
            return true;
        return false;
    }

    Interval overlapSearch(Node root, Interval i) {
        // Base Case, tree is empty
        if (root == null) return null;

        // If given interval overlaps with root
        if (doOverlap(root.i, i))
            return root.i;

        // If left child of root is present and max of left child is
        // greater than or equal to given interval, then i may overlap with an interval is left subtree
        if (root.left != null && root.left.max >= i.low)
            return overlapSearch(root.left, i);

        // Else interval can only overlap with right subtree
        return overlapSearch(root.right, i);
    }
}

class Driver {
    public static void main(String[] args) {
        // Create interval tree
        IntervalTree it = new IntervalTree();

        // Create intervals
        Interval int1 = new Interval();
        int1.low = 15;
        int1.high = 20;

        Interval int2 = new Interval();
        int2.low = 10;
        int2.high = 30;

        Interval int3 = new Interval();
        int3.low = 17;
        int3.high = 19;

        Interval int4 = new Interval();
        int4.low = 5;
        int4.high = 20;

        Interval int5 = new Interval();
        int5.low = 12;
        int5.high = 15;

        Interval int6 = new Interval();
        int6.low = 30;
        int6.high = 40;

        // Insert intervals into tree
        it.root = it.insert(it.root, int1);
        it.root = it.insert(it.root, int2);
        it.root = it.insert(it.root, int3);
        it.root = it.insert(it.root, int4);
        it.root = it.insert(it.root, int5);
        it.root = it.insert(it.root, int6);

        // Create an interval to search for overlaps
        Interval x = new Interval();
        x.low = 14;
        x.high = 16;

        // Search for overlapping interval
        Interval res = it.overlapSearch(it.root, x);

        /**
         * Overlaps with [15, 20]
         */

        // Print result
        if (res == null)
            System.out.println("No Overlapping Interval");
        else
            System.out.println("Overlaps with [" + res.low + ", " + res.high + "]");
    }
}