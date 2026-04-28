package hust.soict.dsai.lab03;

public class GarbageCreator {
    public static void main(String[] args) {
        // Bad way - String immutable, many objects created
        long start = System.currentTimeMillis();
        String s = "";
        for (int i = 0; i < 100000; i++) {
            s += i;
        }
        long end = System.currentTimeMillis();
        System.out.println("String concat time: " + (end - start) + " ms");

        // Improved way - StringBuffer mutable
        start = System.currentTimeMillis();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 100000; i++) {
            buffer.append(i);
        }
        end = System.currentTimeMillis();
        System.out.println("StringBuffer append time: " + (end - start) + " ms");
    }
    //
}
