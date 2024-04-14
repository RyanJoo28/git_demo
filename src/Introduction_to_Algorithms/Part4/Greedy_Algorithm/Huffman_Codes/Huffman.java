package Introduction_to_Algorithms.Part4.Greedy_Algorithm.Huffman_Codes;

import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Comparator;

class HuffmanNode implements Comparable<HuffmanNode> {
    int data;
    char c;
    HuffmanNode left;
    HuffmanNode right;

    public HuffmanNode(int data, char c, HuffmanNode left, HuffmanNode right) {
        this.data = data;
        this.c = c;
        this.left = left;
        this.right = right;
    }

    @Override
    public int compareTo(HuffmanNode other) {
        return this.data - other.data;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        HuffmanNode node = (HuffmanNode) obj;
        return data == node.data && c == node.c;
    }

    @Override
    public int hashCode() {
        return Objects.hash(data, c);
    }
}

class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.data - y.data;
    }
}

class Huffman {
    public static void printCode(HuffmanNode root, String s) {
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
            System.out.println(root.c + ":" + s);
            return;
        }
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }
}

class Driver {
    public static void main(String[] args) {
        int n = 6;
        char[] charArray = {'a', 'b', 'c', 'd', 'e', 'f'};
        int[] charFreq = {5, 9, 12, 13, 16, 45};
        PriorityQueue<HuffmanNode> q = new PriorityQueue<>(n);
        for (int i = 0; i < n; i++) {
            q.add(new HuffmanNode(charFreq[i], charArray[i], null, null));
        }
        HuffmanNode root = null;
        while (q.size() > 1) {
            HuffmanNode x = q.poll();
            HuffmanNode y = q.poll();
            HuffmanNode f = new HuffmanNode(x.data + y.data, '-', x, y);
            root = f;
            q.add(f);
        }

        /**
         * f:0
         * c:100
         * d:101
         * a:1100
         * b:1101
         * e:111
         */

        Huffman.printCode(root, "");
    }
}