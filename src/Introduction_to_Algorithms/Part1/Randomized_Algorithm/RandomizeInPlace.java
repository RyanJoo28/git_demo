package Introduction_to_Algorithms.Part1.Randomized_Algorithm;

import java.util.Random;

class RandomizeInPlace {
    public static void main(String[] args) {
        int[] A = {1, 2, 3, 4, 5}; // Example array
        randomizeInPlace(A);

        for (int i : A) { // Print randomized array
            System.out.print(i + " ");
        }
    }

    static void randomizeInPlace(int[] A) {
        int n = A.length;
        Random rand = new Random();

        for (int i = 0; i < n; i++) {
            int r = i + rand.nextInt(n - i); // Generate a random index between i and n-1 inclusive
            int temp = A[i]; // Swap elements at indices i and r
            A[i] = A[r];
            A[r] = temp;
        }
    }
}