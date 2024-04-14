package Introduction_to_Algorithms.Part4.DP.Rod_Cutting;

class RodCutting {
    int cutRod(int price[], int n) {
        int val[] = new int[n + 1];
        val[0] = 0;

        // Build the table val[] in bottom up manner and return the last entry from the table
        for (int i = 1; i <= n; i++) {
            int max_val = Integer.MIN_VALUE;
            for (int j = 0; j < i; j++)
                max_val = Math.max(max_val, price[j] + val[i - j - 1]);
            val[i] = max_val;
        }

        return val[n];
    }
}

class Driver {
    public static void main(String args[]) {
        RodCutting rodCutting = new RodCutting();
        int[] price = new int[]{1, 5, 8, 9, 10, 17, 17, 20};
        int n = price.length;

        /**
         * Maximum Obtainable Value is 22
         */

        System.out.println("Maximum Obtainable Value is " + rodCutting.cutRod(price, n));
    }
}