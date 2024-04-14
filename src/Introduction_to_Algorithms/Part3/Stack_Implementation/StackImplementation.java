package Introduction_to_Algorithms.Part3.Stack_Implementation;

class StackImplementationByArray {
    private int maxSize;
    private int top;
    private int[] stackArray;

    public StackImplementationByArray(int size) {
        maxSize = size;
        stackArray = new int[maxSize];
        top = -1;
    }

    void push(int value) {
        if (top < maxSize - 1) {
            stackArray[++top] = value; // checking the arr size before push operation
        } else {
            System.out.println("Stack is full. Can't push " + value);
        }
    }

    int pop() {
        if (top >= 0) {
            return stackArray[top--]; // checking the existing arr elements before pop operation
        } else {
            System.out.println("Stack is empty.");
            return -1;
        }
    }

    int peek() {
        if (top >= 0) {
            return stackArray[top];
        } else {
            System.out.println("Stack is empty.");
            return -1;
        }
    }

    boolean isEmpty() {
        return (top == -1);
    }

    boolean isFull() {
        return (top == maxSize - 1);
    }

    void printStack() {
        System.out.println("Stack Elements:");
        for (int i = maxSize - 1; i >= 0; i--) {
            System.out.print(stackArray[i] + " ");
        }
        System.out.println();
    }
}

class StackImplementationByLinkedList {
    private Node top;

    private class Node {
        int value;
        Node next;
    }

    public StackImplementationByLinkedList() {
        top = null;
    }

    void push(int value) {
        Node oldTop = top;
        top = new Node();
        top.value = value;
        top.next = oldTop;
    }

    int pop() {
        if (top == null) {
            System.out.println("Stack is empty.");
            return -1;
        } else {
            int value = top.value;
            top = top.next;
            return value;
        }
    }

    int peek() {
        if (top == null) {
            System.out.println("Stack is empty.");
            return -1;
        } else {
            return top.value;
        }
    }

    boolean isEmpty() {
        return (top == null);
    }

    void printStack() {
        System.out.println("Stack Elements:");
        Node tempNode = top;
        while (tempNode != null) {
            System.out.print(tempNode.value + " ");
            tempNode = tempNode.next;
        }
        System.out.println();
    }
}

class Driver {
    public static void main(String[] args) {
        StackImplementationByArray stackImplementationByArray = new StackImplementationByArray(10);
        StackImplementationByLinkedList stackImplementationByLinkedList = new StackImplementationByLinkedList();

        stackImplementationByArray.push(10);
        stackImplementationByArray.push(8);
        stackImplementationByArray.push(0);
        stackImplementationByArray.push(1);
        stackImplementationByArray.push(6);

        stackImplementationByLinkedList.push(3);
        stackImplementationByLinkedList.push(4);
        stackImplementationByLinkedList.push(8);
        stackImplementationByLinkedList.push(7);
        stackImplementationByLinkedList.push(5);

        /**
         * Implementation by Array:
         * Stack Elements:
         * 0 0 0 0 0 6 1 0 8 10
         *
         * Implementation by Linked List:
         * Stack Elements:
         * 5 7 8 4 3
         */

        System.out.println("Implementation by Array:");
        stackImplementationByArray.printStack();
        System.out.println();
        System.out.println("Implementation by Linked List:");
        stackImplementationByLinkedList.printStack();
    }
}