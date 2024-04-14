package Introduction_to_Algorithms.Part2.Heap_Sort.Max_and_Min_Heap;

class Driver {
    public static void main(String[] args) {
        MinHeap minHeap = new MinHeap(10);
        MaxHeap maxHeap = new MaxHeap(100);

        minHeap.insert(20);
        minHeap.insert(30);
        minHeap.insert(50);
        minHeap.insert(70);
        minHeap.insert(90);
        minHeap.insert(100);

        maxHeap.insert(90);
        maxHeap.insert(80);
        maxHeap.insert(50);
        maxHeap.insert(60);
        maxHeap.insert(30);
        maxHeap.insert(5);

        minHeap.printHeap();
        maxHeap.printHeap();
    }
}