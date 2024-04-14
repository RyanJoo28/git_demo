package Introduction_to_Algorithms.Part4.DP.Optimal_BST.Instance;

class OptimalBST {
    float optimalBST(float[] keys, float[] freq) {
        int n = keys.length;
        float[][] cost = new float[n + 1][n + 1];
        float[][] dp = new float[n + 2][n + 1];

        // Initialize cost for single keys and frequencies
        for (int i = 1; i <= n; i++) {
            cost[i][i] = freq[i - 1];
            dp[i][i] = freq[i - 1];
        }

        // Fill in the DP table
        // Compare with Matrix Chain Multiplication
        for (int len = 2; len <= n; len++) {
            for (int i = 1; i <= n - len + 1; i++) {
                int j = i + len - 1;
                dp[i][j] = Float.MAX_VALUE;
                float sum = 0;
                for (int k = i; k <= j; k++) {
                    sum += freq[k - 1];
                }
                for (int k = i; k <= j; k++) {
                    float val = dp[i][k - 1] + dp[k + 1][j] + sum;
                    if (val < dp[i][j]) {
                        dp[i][j] = val;
                        cost[i][j] = sum;
                    }
                    if (k < j) {
                        sum -= freq[k];
                    }
                }
            }
        }

        return dp[1][n];
    }
}

class Driver {
    public static void main(String[] args) {
        OptimalBST optimalBST = new OptimalBST();
        float[] keys = {10, 12, 20};
        float[] freq = {34, 8, 50};
        float minCost = optimalBST.optimalBST(keys, freq);
        System.out.println("Minimum cost of optimal BST: " + minCost); // Minimum cost of optimal BST: 84.0
    }
}