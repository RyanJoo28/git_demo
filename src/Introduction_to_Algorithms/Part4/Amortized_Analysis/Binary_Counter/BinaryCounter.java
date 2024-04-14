package Introduction_to_Algorithms.Part4.Amortized_Analysis.Binary_Counter;

class BinaryCounter {
    private boolean[] bits;
    private int size;

    public BinaryCounter(int size) {
        this.size = size;
        this.bits = new boolean[size];
    }

    public void increment() {
        int index = size - 1;
        while (index >= 0 && bits[index]) {
            bits[index] = false;
            index--;
        }
        if (index >= 0) {
            bits[index] = true;
        } else {
            // Overflow handling if all bits are 1
            System.out.println("Counter overflow!");
        }
    }

    public String getBinaryRepresentation() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(bits[i] ? 1 : 0);
        }
        return sb.toString();
    }
}

class Driver {
    public static void main(String[] args) {
        BinaryCounter counter = new BinaryCounter(4);

        System.out.println("Initial binary representation: " + counter.getBinaryRepresentation());

        counter.increment();
        System.out.println("After increment: " + counter.getBinaryRepresentation());

        counter.increment();
        System.out.println("After increment: " + counter.getBinaryRepresentation());

        counter.increment();
        System.out.println("After increment: " + counter.getBinaryRepresentation());

        counter.increment(); // Overflow
    }
}