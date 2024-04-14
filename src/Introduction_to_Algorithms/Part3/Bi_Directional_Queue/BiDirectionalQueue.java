package Introduction_to_Algorithms.Part3.Bi_Directional_Queue;

class BiDirectionalQueueArray {
    private static final int MAX_SIZE = 100;
    private int[] queue;
    private int front, rear;

    public BiDirectionalQueueArray() {
        queue = new int[MAX_SIZE];
        front = -1;
        rear = -1;
    }

    public void enqueueFront(int value) {
        if (front == -1) { // when the queue is empty
            front = 0;
            rear = 0;
        }
        /**
         * In a circular queue, if we want to add an element to the front of the queue but the front pointer is already at index 0, we should wrap around to the end of the array to maintain the circular structure
         * Setting Front to MAX_SIZE - 1 allows us to add elements at the front of the queue after wrapping around to the end of the array
         */
        else if (front == 0) {
            front = MAX_SIZE - 1;
        } else { // stack
            front--;
        }
        queue[front] = value;
    }

    public void enqueueRear(int value) {
        if (rear == -1) { // when the queue is empty
            front = 0;
            rear = 0;
        } else if (rear == MAX_SIZE - 1) {
            rear = 0;
        } else { // queue
            rear++;
        }
        queue[rear] = value;
    }

    public int dequeueFront() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }
        int value = queue[front];
        if (front == rear) {
            front = -1;
            rear = -1;
        } else if (front == MAX_SIZE - 1) {
            front = 0;
        } else {
            front++;
        }
        return value;
    }

    public int dequeueRear() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }
        int value = queue[rear];
        if (front == rear) {
            front = -1;
            rear = -1;
        } else if (rear == 0) {
            rear = MAX_SIZE - 1;
        } else {
            rear--;
        }
        return value;
    }

    public boolean isEmpty() {
        return front == -1;
    }

    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        int idx = front;
        while (idx != rear) {
            System.out.print(queue[idx] + " ");
            idx = (idx + 1) % MAX_SIZE;
        }
        System.out.println(queue[rear]);
    }
}

class BiDirectionalQueueLinkedList {
    private class Node {
        int value;
        Node next;
        Node prev;

        Node(int value) {
            this.value = value;
            this.next = null;
            this.prev = null;
        }
    }

    private Node front, rear;

    public BiDirectionalQueueLinkedList() {
        front = null;
        rear = null;
    }

    public void enqueueFront(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else { // newNode ⇔ front
            newNode.next = front;
            front.prev = newNode;
            front = newNode;
        }
    }

    public void enqueueRear(int value) {
        Node newNode = new Node(value);
        if (isEmpty()) {
            front = newNode;
            rear = newNode;
        } else { // rear ⇔ newNode
            rear.next = newNode;
            newNode.prev = rear;
            rear = newNode;
        }
    }

    public int dequeueFront() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }
        int value = front.value;
        if (front == rear) {
            front = null;
            rear = null;
        } else {
            front = front.next;
            front.prev = null;
        }
        return value;
    }

    public int dequeueRear() {
        if (isEmpty()) {
            throw new IllegalStateException("Queue is empty.");
        }
        int value = rear.value;
        if (front == rear) {
            front = null;
            rear = null;
        } else {
            rear = rear.prev;
            rear.next = null;
        }
        return value;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return;
        }
        Node current = front;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }
}

class Driver {
    public static void main(String[] args) {
        // Array-based implementation
        BiDirectionalQueueArray arrayQueue = new BiDirectionalQueueArray();
        arrayQueue.enqueueFront(1);
        arrayQueue.enqueueRear(2);
        arrayQueue.enqueueFront(0);
        arrayQueue.enqueueRear(3);
        arrayQueue.printQueue(); // 0 1 2 3
        arrayQueue.dequeueFront();
        arrayQueue.dequeueRear();
        arrayQueue.printQueue(); // 1 2

        // Linked list-based implementation
        BiDirectionalQueueLinkedList linkedListQueue = new BiDirectionalQueueLinkedList();
        linkedListQueue.enqueueFront(1);
        linkedListQueue.enqueueRear(2);
        linkedListQueue.enqueueFront(0);
        linkedListQueue.enqueueRear(3);
        linkedListQueue.printQueue(); // 0 1 2 3
        linkedListQueue.dequeueFront();
        linkedListQueue.dequeueRear();
        linkedListQueue.printQueue(); // 1 2
    }
}