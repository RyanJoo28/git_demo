package Introduction_to_Algorithms.Part2.Counting_Sort;

import java.util.Arrays;

class CountingSort {
    void sort(int[] arr) {
        int n = arr.length;
        int max = Arrays.stream(arr).max().getAsInt();
        int min = Arrays.stream(arr).min().getAsInt();
        int range = max - min + 1;

        int[] count = new int[range];
        int[] output = new int[n];

        for (int i = 0; i < n; i++) {
            count[arr[i] - min]++;
        }

        for (int i = 1; i < range; i++) {
            count[i] += count[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i] - min] - 1] = arr[i];
            count[arr[i] - min]--;
        }

        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }
}

class Driver {
    public static void main(String[] args) {
        int[] arr = {10, 7, 8, 9, 1, 5};
        CountingSort countingSort = new CountingSort();
        countingSort.sort(arr);
        /* Sorted array: [1, 5, 7, 8, 9, 10] */
        System.out.println("Sorted array: " + Arrays.toString(arr));
    }
}