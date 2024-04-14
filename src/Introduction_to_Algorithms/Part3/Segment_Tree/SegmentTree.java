package Introduction_to_Algorithms.Part3.Segment_Tree;

class SegmentTree {
    private int[] tree;
    private int n;

    public SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[2 * n];
        buildTree(arr);
    }

    private void buildTree(int[] arr) {
        for (int i = 0; i < n; i++) {
            tree[n + i] = arr[i];
        }
        for (int i = n - 1; i > 0; --i) {
            tree[i] = tree[2 * i] + tree[2 * i + 1];
        }
    }

    public void update(int p, int value) {
        tree[p + n] = value;
        p = p + n;
        for (int i = p; i > 1; i >>= 1) {
            tree[i >> 1] = tree[i] + tree[i ^ 1];
        }
    }

    public int query(int l, int r) {
        int res = 0;
        for (l += n, r += n; l < r; l >>= 1, r >>= 1) {
            if ((l & 1) > 0) {
                res += tree[l++];
            }
            if ((r & 1) > 0) {
                res += tree[--r];
            }
        }
        return res;
    }
}

class Driver {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        SegmentTree segmentTree = new SegmentTree(arr);

        // Print original array
        System.out.println("Original array:");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        // Perform a query
        int sum = segmentTree.query(1, 5);
        System.out.println("Sum of elements from index 1 to 5: " + sum);

        // Update an element
        segmentTree.update(2, 10);

        // Perform a query after update
        sum = segmentTree.query(1, 5);
        System.out.println("Sum of elements from index 1 to 5 after update: " + sum);
    }
}