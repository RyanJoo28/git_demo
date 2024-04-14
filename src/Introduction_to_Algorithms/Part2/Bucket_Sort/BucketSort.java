package Introduction_to_Algorithms.Part2.Bucket_Sort;

import java.util.*;

class BucketSort {
    void sort(float[] arr, int n) {
        if (n <= 0)
            return;

        @SuppressWarnings("unchecked")
        ArrayList<Float>[] bucket = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            bucket[i] = new ArrayList<Float>();
        }

        for (int i = 0; i < n; i++) {
            int bucketIndex = (int) arr[i] * n;
            bucket[bucketIndex].add(arr[i]);
        }

        for (int i = 0; i < n; i++) {
            Collections.sort((bucket[i]));
        }

        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0, size = bucket[i].size(); j < size; j++) {
                arr[index++] = bucket[i].get(j);
            }
        }
    }
}

class Driver {
    public static void main(String[] args) {
        float[] arr = { (float)0.897, (float)0.565,
                (float)0.656, (float)0.1234,
                (float)0.665, (float)0.3434 };
        BucketSort bucketSort = new BucketSort();
        int n = arr.length;
        bucketSort.sort(arr, n);

        /**
         * Sorted array is:
         * 0.1234 0.3434 0.565 0.656 0.665 0.897
         */
        System.out.println("Sorted array is: ");
        for (float el : arr) {
            System.out.print(el + " ");
        }
    }
}