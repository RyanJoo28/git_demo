package Introduction_to_Algorithms.Part4.DP.Matrix_Chain_Multiplication;

class MatrixChainMultiplication {
    int[][] m;
    int[][] s;

    void matrixChainOrder(int p[]) {
        int n = p.length - 1; // dimensions of matrix (p[])
        m = new int[n + 1][n + 1];
        s = new int[n + 1][n + 1];

        for (int i = 0; i <= n; i++) {
            m[i][i] = 0; // there is only one matrix in the subsequence, and the cost is 0
        }

        for (int l = 2; l <= n; l++) { // l represents the length of the chain of matrices being considered. The loop starts from 2 because you need at least two matrices to multiply.
            /**
             * i and j define the start and end points of the chain being considered in the current iteration.
             * k iterates within the chain, splitting it into two sub-chains to calculate the minimum cost of multiplication.
             */
            for (int i = 1; i <= n - l + 1; i++) {
                int j = i + l - 1;
                if (j == n + 1) continue;
                m[i][j] = Integer.MAX_VALUE; // indicating that the cost is not yet known for this chain length.
                for (int k = i; k <= j - 1; k++) {
                    int q = m[i][k] + m[k + 1][j] + p[i - 1] * p[k] * p[j];
                    if (q < m[i][j]) {
                        m[i][j] = q; // m[i][j] stores the minimum cost of multiplying the chain of matrices from index i to index j.
                        s[i][j] = k; // s[i][j] stores the index k at which the chain is split to achieve the minimum cost.
                    }
                }
            }
        }
    }
}

class Driver {
    public static void main(String[] args) {
        MatrixChainMultiplication matrixChainMultiplication = new MatrixChainMultiplication();
        int[] p = {30, 35, 15, 5, 10, 20, 25};
        matrixChainMultiplication.matrixChainOrder(p);

        /**
         * Minimum number of multiplications for m[2][5] is: 7125
         */

        System.out.println("Minimum number of multiplications for m[2][5] is: " + matrixChainMultiplication.m[2][5]);
    }
}