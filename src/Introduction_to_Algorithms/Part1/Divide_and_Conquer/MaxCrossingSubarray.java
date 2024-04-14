package Introduction_to_Algorithms.Part1.Divide_and_Conquer;

class MaxCrossingSubarray {
    int[] findMaxCrossingSubarray(int[] A, int low, int mid, int high) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeft = 0;
        for (int i = mid; i >= low; i--) {
            sum = sum + A[i];
            if (sum > leftSum) {
                leftSum = sum;
                maxLeft = i;
            }
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        int maxRight = 0;
        for (int j = mid + 1; j <= high; j++) {
            sum = sum + A[j];
            if (sum > rightSum) {
                rightSum = sum;
                maxRight = j;
            }
        }

        return new int[]{maxLeft, maxRight, leftSum + rightSum};
    }
}

class Driver {
    public static void main(String[] args) {
        MaxCrossingSubarray maxCrossingSubarray = new MaxCrossingSubarray();
        int[] arr = {12, 2, 7, 4, 10, 9, 8};
        int[] result = maxCrossingSubarray.findMaxCrossingSubarray(arr, 0, 3, 6);
        /**
         * traversal methods
         * Ⅰ.
         * for (int i = 0; i < arr.length; i++) {
         *     System.out.println(arr[i]);
         * }
         *
         * Ⅱ.
         * for (int num : arr) {
         *     System.out.println(num);
         * }
         *
         * Ⅲ.
         * Arrays.stream(arr).forEach(System.out::println);
         */
        System.out.print("Result: ");
        /* Result: 0 6 52  */
        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }
    }
}