package Introduction_to_Algorithms.Part4.DP.Other_Usual_Issues_in_DP;

import java.util.Arrays;

class LongestCommonSubsequence {
    int longestCommonSubsequence(String text1, String text2) {
        int m = text1.length();
        int n = text2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                /**
                 * dp[i][j]: Represents the length of the longest common subsequence of substrings text1.substring(0, i) and text2.substring(0, j).
                 * dp[i - 1][j]: Represents the length of the longest common subsequence of substrings text1.substring(0, i - 1) and text2.substring(0, j).
                 * dp[i][j - 1]: Represents the length of the longest common subsequence of substrings text1.substring(0, i) and text2.substring(0, j - 1).
                 */
                if (text1.charAt(i - 1) == text2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]); // either one of them (the shorter one) could represent the longest common subsequence of the current substrings.
                }
            }
        }

        return dp[m][n];
    }
}

// you need to pack a set of items, with given values and sizes (such as weights or volumes), into a container with a maximum capacity
class KnapsackProblem {
    int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];

        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    dp[i][w] = Math.max(dp[i - 1][w], values[i - 1] + dp[i - 1][w - weights[i - 1]]);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }

        return dp[n][capacity];
    }
}

// the minimum number of coins (of certain denominations) that add up to a given amount (total value) of money
class CoinChangeProblem {
    int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1]; // to store the minimum number of coins required to make up each amount from 0 to amount
        Arrays.fill(dp, amount + 1); // an arbitrary value chosen to represent infinity or an impossible amount
        dp[0] = 0; // when it requires no coins

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                dp[i] = Math.min(dp[i], 1 + dp[i - coin]); // 1 plus the minimum number of coins required for amount i - coin (i.e., the remaining amount after using the current coin denomination)
            }
        }

        return dp[amount] > amount ? -1 : dp[amount];
    }
}

// aims to find the minimum number of operations (insertions, deletions, or substitutions) required to transform one string into another
class EditDistance {
    int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0) {
                    dp[i][j] = j; // word2.length
                } else if (j == 0) {
                    dp[i][j] = i; // word1.length
                } else if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1]; // eliminate the corresponding character
                }
                /**
                 * handles the case where the characters at positions i-1 in word1 and j-1 in word2 are not equal
                 * we need to perform an operation (either insertion, deletion, or substitution) to transform word1.substring(0, i) to word2.substring(0, j)
                 */
                else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])); // calculate the count of update
                }
            }
        }

        return dp[m][n];
    }
}

// aims to find the length of the longest subsequence of a given sequence of numbers such that all elements of the subsequence are sorted in increasing order
class LongestIncreasingSubsequence {
    int lengthOfLIS(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n];
        Arrays.fill(dp, 1); // initializes all elements of dp to 1 initially because each element by itself is a subsequence of length 1

        int maxLIS = 1;
        for (int i = 1; i < n; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    maxLIS = Math.max(maxLIS, dp[i]);
                }
            }
        }

        return maxLIS;
    }
}

class MaximumSubarraySum {
    int maxSubArray(int[] nums) {
        int maxSum = nums[0];
        int currSum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(maxSum, currSum);
        }

        return maxSum;
    }
}

class Driver {
    public static void main(String[] args) {
        LongestCommonSubsequence lcs = new LongestCommonSubsequence();
        int result1 = lcs.longestCommonSubsequence("acknowledge", "knowledge");
        System.out.println("Result: " + result1 + "\n"); // Result: 9

        KnapsackProblem knapsackProblem = new KnapsackProblem();
        int[] weights = {2, 4, 6, 3, 3, 1, 4};
        int[] values = {6, 5, 3, 7, 1, 3, 4};
        int result2 = knapsackProblem.knapsack(weights, values, 5);
        System.out.println("Result: " + result2 + "\n"); // Result: 13

        CoinChangeProblem coinChangeProblem = new CoinChangeProblem();
        int[] coins = {1, 2, 1, 2, 2, 5, 5};
        int result3 = coinChangeProblem.coinChange(coins, 3);
        int result4 = coinChangeProblem.coinChange(coins, 10);
        System.out.println("Result1: " + result3 + "; Result2: " + result4 + "\n"); // Result1: 2; Result2: 2

        EditDistance editDistance = new EditDistance();
        int result5 = editDistance.minDistance("substitution", "deletion");
        System.out.println("Result: " + result5 + "\n"); // Result: 8

        LongestIncreasingSubsequence lis = new LongestIncreasingSubsequence();
        int[] arr = {1, 3, 7, 12, 65, 32, 21, 11, 13};
        int result6 = lis.lengthOfLIS(arr);
        System.out.println("Result: " + result6 + "\n"); // Result: 5

        MaximumSubarraySum maximumSubarraySum = new MaximumSubarraySum();
        int[] nums = {12, -2, 10, 1, -13, -14, 28};
        int result7 = maximumSubarraySum.maxSubArray(nums);
        System.out.println("Result: " + result7 + "\n"); // Result: 28
    }
}