package Introduction_to_Algorithms.Part2.Heap_Sort.Priority_Queue;

import java.util.*;

class MyPriorityQueue {
    private PriorityQueue<Integer> maxHeap;

    // Introduction_to_Algorithms.Part4.DP.Optimal_BST.Constructor
    public MyPriorityQueue() {
        this.maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    }

    // Insert into Priority Queue
    public void insert(int x) {
        maxHeap.add(x);
    }

    // Extract maximum (highest priority element)
    public int extractMax() {
        return maxHeap.poll();
    }

    // Get maximum (highest priority element)
    public int getMax() {
        return maxHeap.peek();
    }

    // Check if the Priority Queue is empty
    public boolean isEmpty() {
        return maxHeap.isEmpty();
    }
}

class Driver {
    public static void main(String[] args) {
        MyPriorityQueue pq = new MyPriorityQueue();
        pq.insert(3);
        pq.insert(2);
        pq.insert(15);
        System.out.println(pq.extractMax()); // prints 15
        System.out.println(pq.getMax()); // prints 3
        System.out.println(pq.isEmpty()); // prints false
    }
}