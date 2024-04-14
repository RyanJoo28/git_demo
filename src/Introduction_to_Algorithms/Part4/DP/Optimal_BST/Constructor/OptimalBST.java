package Introduction_to_Algorithms.Part4.DP.Optimal_BST.Constructor;

class OptimalBST {
    private double[][] e, w;
    private int[][] root;
    private double[] p, q; // p and q are arrays representing probabilities or frequencies associated with the keys in the binary search tree
    private int n; // n represents the number of keys

    public OptimalBST(double[] p, double[] q, int n) {
        // Constructor
        this.p = p;
        this.q = q;
        this.n = n;
        this.e = new double[n + 2][n + 1];
        this.w = new double[n + 2][n + 1];
        this.root = new int[n + 1][n + 1];

        for (int i = 1; i <= n + 1; i++) {
            e[i][i - 1] = q[i - 1];
            w[i][i - 1] = q[i - 1];
        }

        // DP Loop
        for (int l = 1; l <= n; l++) {
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                e[i][j] = Double.MAX_VALUE;
                w[i][j] = w[i][j - 1] + p[j] + q[j];

                for (int r = i; r <= j; r++) {
                    double t = e[i][r - 1] + e[r + 1][j] + w[i][j];
                    if (t < e[i][j]) {
                        e[i][j] = t;
                        root[i][j] = r;
                    }
                }
            }
        }
    }

    // getE and getRoot Methods
    public double[][] getE() {
        return e;
    }

    public int[][] getRoot() {
        return root;
    }
}

class Driver {
    public static void main(String[] args) {
        double[] p = {0, 0.15, 0.1, 0.05, 0.1, 0.2};
        double[] q = {0.05, 0.1, 0.05, 0.05, 0.05, 0.1};
        int n = p.length - 1; // Number of keys (excluding the dummy key)

        OptimalBST obst = new OptimalBST(p, q, n);

        /**
         * Optimal Cost Matrix (e):
         * 0.05	0.45	0.90	1.25	1.75	2.75
         * 0.00	0.10	0.40	0.70	1.20	2.00
         * 0.00	0.00	0.05	0.25	0.60	1.30
         * 0.00	0.00	0.00	0.05	0.30	0.90
         * 0.00	0.00	0.00	0.00	0.05	0.50
         * 0.00	0.00	0.00	0.00	0.00	0.10
         *
         * Root Matrix:
         * 1	1	2	2	2
         * 0	2	2	2	4
         * 0	0	3	4	5
         * 0	0	0	4	5
         * 0	0	0	0	5
         */

        // Display the optimal cost matrix (e)
        double[][] e = obst.getE();
        System.out.println("Optimal Cost Matrix (e):");
        for (int i = 1; i <= n + 1; i++) {
            for (int j = 0; j <= n; j++) {
                System.out.printf("%.2f\t", e[i][j]);
            }
            System.out.println();
        }

        // Display the root matrix
        int[][] root = obst.getRoot();
        System.out.println("\nRoot Matrix:");
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                System.out.printf("%d\t", root[i][j]);
            }
            System.out.println();
        }
    }
}