package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedListTest {

    @Test
    void add() {
        LinkedList<Integer> lista = new LinkedList<>();
        lista.addFirst(1);
        lista.addFirst(2);
        lista.addFirst(3);
        lista.addFirst(4);
        lista.addFirst(5);
        lista.addFirst(6);
        System.out.println(lista);
    }

    @Test
    void addFirst() {
    }
}