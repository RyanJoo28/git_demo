package Introduction_to_Algorithms.Part3.Queue_Implementation;

class QueueImplementationByArray {
    private int maxSize;
    private int front;
    private int rear;
    private int[] queueArray;

    public QueueImplementationByArray(int size) {
        maxSize = size;
        queueArray = new int[maxSize];
        front = 0; // the leftmost index
        rear = -1;
    }

    void enqueue(int value) {
        if (rear < maxSize - 1) {
            queueArray[++rear] = value;
        } else {
            System.out.println("Queue is full. Can't enqueue " + value);
        }
    }

    int dequeue() {
        if (front <= rear) {
            return queueArray[front++];
        } else {
            System.out.println("Queue is empty.");
            return -1;
        }
    }

    void printQueue() {
        for (int i = front; i <= rear; i++) {
            System.out.print(queueArray[i] + " ");
        }
    }
}

class QueueImplementationByLinkedList {
    private Node front, rear;

    private class Node {
        int value;
        Node next;
    }

    public QueueImplementationByLinkedList() {
        front = null;
        rear = null;
    }

    void enqueue(int value) {
        /* oldRear -> rear -> NULL */
        Node oldRear = rear;
        rear = new Node();
        rear.value = value;
        rear.next = null;
        if (isEmpty()) { // no oldRear before
            front = rear;
        } else {
            oldRear.next = rear;
        }
    }

    int dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty.");
            return -1;
        }
        int value = front.value;
        front = front.next;
        if (isEmpty()) {
            rear = null;
        }
        return value;
    }

    boolean isEmpty() {
        return (front == null);
    }

    void printQueue() {
        Node tempNode = front;
        while (tempNode != null) {
            System.out.print(tempNode.value + " ");
            tempNode = tempNode.next;
        }
    }
}

class Driver {
    public static void main(String[] args) {
        QueueImplementationByArray queueImplementationByArray = new QueueImplementationByArray(10);
        QueueImplementationByLinkedList queueImplementationByLinkedList = new QueueImplementationByLinkedList();

        queueImplementationByArray.enqueue(2);
        queueImplementationByArray.enqueue(7);
        queueImplementationByArray.enqueue(8);
        queueImplementationByArray.enqueue(9);
        queueImplementationByArray.enqueue(11);

        queueImplementationByLinkedList.enqueue(3);
        queueImplementationByLinkedList.enqueue(23);
        queueImplementationByLinkedList.enqueue(13);
        queueImplementationByLinkedList.enqueue(9);
        queueImplementationByLinkedList.enqueue(6);

        /**
         * Implementation by Array:
         * 2 7 8 9 11
         *
         * Implementation by Linked List:
         * 3 23 13 9 6
         */

        System.out.println("Implementation by Array:");
        queueImplementationByArray.printQueue();
        System.out.println("\n");
        System.out.println("Implementation by Linked List:");
        queueImplementationByLinkedList.printQueue();
        System.out.println();
    }
}