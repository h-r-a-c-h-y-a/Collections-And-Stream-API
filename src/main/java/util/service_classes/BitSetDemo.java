package util.service_classes;

import java.util.BitSet;

public class BitSetDemo {
    public static void main(String[] args) {
        BitSet bitSet1 = new BitSet(16);
        BitSet bitSet2 = new BitSet(16);
        for (int i = 0; i < 16; i++) {
            if ((i % 2) == 0) bitSet1.set(i);
            if ((i % 5) != 0) bitSet2.set(i);
        }
        System.out.println("first bit combination in bitset1: ");
        System.out.println(bitSet1);
        System.out.println("first bit combination in bitset2: ");
        System.out.println(bitSet2);

        //do logic op & to bits
        bitSet2.and(bitSet1);
        System.out.println("\nbitset2 AND bitsetl: ");
        System.out.println(bitSet2);

        //do logic op || to bits
        bitSet2.or(bitSet1);
        System.out.println("\nbitset2 OR bitsetl: ");
        System.out.println(bitSet2);

        //do logic op ^ to bits
        bitSet2.xor(bitSet1);
        System.out.println("\nbitset2 XOR bitsetl: ");
        System.out.println(bitSet2);
    }
}
