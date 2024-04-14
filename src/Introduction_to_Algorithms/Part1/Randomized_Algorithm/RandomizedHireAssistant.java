package Introduction_to_Algorithms.Part1.Randomized_Algorithm;

import java.util.ArrayList;
import java.util.Collections;

class RandomizedHireAssistant {
    public static void main(String[] args) {
        int n = 10; // Example: 10 candidates
        hireAssistant(n);
    }

    static void hireAssistant(int n) {
        ArrayList<Integer> candidates = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            candidates.add(i);
        }

        Collections.shuffle(candidates); // Randomly permute the list of candidates

        int best = 0; // Candidate 0 is a least-qualified dummy candidate

        for (int i : candidates) {
            System.out.println("Interviewing candidate " + i);

            if (i > best) {
                best = i;
                System.out.println("Hiring candidate " + i);
            }
        }
    }
}