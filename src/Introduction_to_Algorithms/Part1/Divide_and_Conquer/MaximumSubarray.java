package Introduction_to_Algorithms.Part1.Divide_and_Conquer;

class MaximumSubarray {
    int[] findMaximumSubarray(int[] A, int low, int high) {
        if (high == low) {
            return new int[]{low, high, A[low]};  // base case: only one element
        } else {
            int mid = (low + high) / 2;

            // Find maximum subarray in the left half
            int[] left = findMaximumSubarray(A, low, mid);

            // Find maximum subarray in the right half
            int[] right = findMaximumSubarray(A, mid + 1, high);

            // Find maximum crossing subarray
            int[] cross = findMaxCrossingSubarray(A, low, mid, high);

            if (left[2] >= right[2] && left[2] >= cross[2]) {
                return left;
            } else if (right[2] >= left[2] && right[2] >= cross[2]) {
                return right;
            } else {
                return cross;
            }
        }
    }

    int[] findMaxCrossingSubarray(int[] A, int low, int mid, int high) {
        // Left side of crossing subarray
        int maxLeftSum = Integer.MIN_VALUE;
        int sum = 0;
        int maxLeftIndex = mid;

        for (int i = mid; i >= low; i--) {
            sum += A[i];

            if (sum > maxLeftSum) {
                maxLeftSum = sum;
                maxLeftIndex = i;
            }
        }

        // Right side of crossing subarray
        sum = 0;
        int maxRightSum = Integer.MIN_VALUE;
        int maxRightIndex = mid + 1;
        for (int j = mid + 1; j <= high; j++) {
            sum += A[j];

            if (sum > maxRightSum) {
                maxRightSum = sum;
                maxRightIndex = j;
            }
        }

        return new int[]{maxLeftIndex, maxRightIndex, maxLeftSum + maxRightSum};
    }
}

class DriverMethod {
    public static void main(String[] args) {
        MaximumSubarray maximumSubarray = new MaximumSubarray();
        int[] arr = {-1, -7, 10, 13, 0, 9, -10};
        int[] result = maximumSubarray.findMaximumSubarray(arr, 0, 6);
        /* Result: 2 5 32  */
        System.out.print("Result: ");
        for (int num : result) {
            System.out.print(num + " ");
        }
        System.out.println();
    }
}