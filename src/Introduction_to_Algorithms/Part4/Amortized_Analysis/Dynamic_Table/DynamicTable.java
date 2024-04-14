package Introduction_to_Algorithms.Part4.Amortized_Analysis.Dynamic_Table;

import java.util.ArrayList;

class DynamicTable {
    private ArrayList<Integer> table;

    public DynamicTable() {
        table = new ArrayList<>();
    }

    public void add(int value) {
        table.add(value);
    }

    public int get(int index) {
        if (index >= 0 && index < table.size()) {
            return table.get(index);
        } else {
            throw new IndexOutOfBoundsException("Index out of range");
        }
    }

    public void remove(int index) {
        if (index >= 0 && index < table.size()) {
            table.remove(index);
        } else {
            throw new IndexOutOfBoundsException("Index out of range");
        }
    }

    public int size() {
        return table.size();
    }
}

class Driver {
    public static void main(String[] args) {
        DynamicTable dynamicTable = new DynamicTable();

        dynamicTable.add(10);
        dynamicTable.add(20);
        dynamicTable.add(30);

        System.out.println("Table size: " + dynamicTable.size());

        for (int i = 0; i < dynamicTable.size(); i++) {
            System.out.println("Value at index " + i + ": " + dynamicTable.get(i));
        }

        dynamicTable.remove(1);

        System.out.println("Table size after removal: " + dynamicTable.size());
    }
}