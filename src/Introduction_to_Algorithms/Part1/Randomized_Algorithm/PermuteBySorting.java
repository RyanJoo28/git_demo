package Introduction_to_Algorithms.Part1.Randomized_Algorithm;

import java.util.Arrays;
import java.util.Random;

class PermuteBySorting {
    public static void main(String[] args) {
        Integer[] A = {1, 2, 3, 4, 5}; // Example array
        permuteBySorting(A);
    }

    static void permuteBySorting(Integer[] A) {
        int n = A.length;
        Integer[] P = new Integer[n];
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            P[i] = rand.nextInt(n * n * n) + 1; // Random number between 1 and n^3
        }

        // Sort A using P as sort keys
        Arrays.sort(A, (i, j) -> P[i] - P[j]);

        System.out.println(Arrays.toString(A));
    }
}