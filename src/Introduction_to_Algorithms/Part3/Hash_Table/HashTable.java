package Introduction_to_Algorithms.Part3.Hash_Table;

import java.util.LinkedList;

class HashTable<K, V> {
    private final int INITIAL_SIZE = 16;
    private LinkedList<Node<K, V>>[] data;

    public HashTable() {
        data = new LinkedList[INITIAL_SIZE];
        for (int i = 0; i < INITIAL_SIZE; i++) {
            data[i] = new LinkedList<>();
        }
    }

    /* HashTable<Object, Object> hashTable = new HashTable<>(); */

    /**
     * class Node {
     *     int value;
     *     Node next;
     *
     *     Node(int value) {
     *         this.value = value;
     *         this.next = null;
     *     }
     * }
     *
     * public class Main {
     *     public static void main(String[] args) {
     *         Node node = new Node(5);
     *         System.out.println(node instanceof Object);  // This will print: true
     *     }
     * }
     */

    private static class Node<K, V> {
        K key;
        V value;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(K key, V value) {
        int index = getIndex(key);
        LinkedList<Node<K, V>> items = data[index];
        if (items == null) {
            items = new LinkedList<>();
            data[index] = items;
        }
        for (Node<K, V> node : items) {
            if (node.key.equals(key)) {
                node.value = value; // update the existing value
                return;
            }
        }
        items.add(new Node<>(key, value));
    }

    public V get(K key) {
        int index = getIndex(key);
        LinkedList<Node<K, V>> items = data[index];
        if (items == null) return null;
        for (Node<K, V> node : items) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }
        return null;
    }

    public void remove(K key) {
        int index = getIndex(key);
        LinkedList<Node<K, V>> items = data[index];
        if (items == null) return;
        for (Node<K, V> node : items) {
            if (node.key.equals(key)) {
                items.remove(node);
                return;
            }
        }
    }

    private int getIndex(K key) {
        return Math.abs(key.hashCode() % data.length);
    }
}

class Driver {
    public static void main(String[] args) {
        /**
         * One: 1
         * Two: 2
         * Three: 3
         * Two: null
         */
        HashTable<String, Integer> hashTable = new HashTable<>();

        // Put some key value pairs
        hashTable.put("One", 1);
        hashTable.put("Two", 2);
        hashTable.put("Three", 3);

        // Get values
        System.out.println("One: " + hashTable.get("One"));
        System.out.println("Two: " + hashTable.get("Two"));
        System.out.println("Three: " + hashTable.get("Three"));

        // Remove a key-value pair
        hashTable.remove("Two");

        // Try to get the removed key
        System.out.println("Two: " + hashTable.get("Two"));
    }
}