package util;

import org.junit.jupiter.api.Test;
import util.Graph.Graph;

import java.io.IOException;

class GraphTest {

    @Test
    void disconnectOnly() {
        Graph<Integer> graph = new Graph<>();
        graph.addElement(3);
        graph.addElement(4);
        graph.addElement(5);
        graph.addElement(6);
        graph.addElement(7);
        graph.connect(3, 4, 80);
        System.out.println(graph);
        graph.disconnect(3, 4);
        System.out.println(graph);
    }
    @Test
    void disconnectWhenOthers() {
        Graph<Integer> graph = new Graph<>();
        graph.addElement(3);
        graph.addElement(4);
        graph.addElement(5);
        graph.addElement(6);
        graph.addElement(7);
        graph.connect(3, 4, 80);
        graph.connect(3, 5, 80);
        System.out.println(graph);
        graph.disconnect(3, 4);
        System.out.println(graph);
    }
    @Test
    void disconnectNull(){
        Graph<Integer> graph = new Graph<>();
        graph.addElement(3);
        graph.addElement(4);
        graph.addElement(5);
        graph.addElement(6);
        graph.addElement(7);
        graph.connect(3, 4, 80);
        graph.connect(3, 5, 80);
        System.out.println(graph);
        graph.disconnect(3, 6);
        System.out.println(graph);
    }
    @Test
    void addElement() {
        Graph<Integer> graph = new Graph<>();
        graph.addElement(3);
        graph.addElement(4);
        graph.addElement(5);
        graph.addElement(6);
        graph.addElement(7);
    }

    @Test
    void deleteElementExists() throws IOException {
        Graph<Integer> graph = new Graph<>();
        graph.addElement(3);
        graph.addElement(4);
        graph.addElement(5);
        graph.addElement(6);
        graph.addElement(7);
        graph.deleteElement(4);
        System.out.println(graph);
    }
    @Test
    void deleteNull() throws IOException {
        Graph<Integer> graph = new Graph<>();
        graph.addElement(3);
        graph.addElement(4);
        graph.addElement(5);
        graph.addElement(6);
        graph.addElement(7);
        graph.deleteElement(8);
        System.out.println(graph);
    }

    @Test
    void connectNull() {
        Graph<Integer> graph = new Graph<>();
        graph.addElement(3);
        graph.addElement(4);
        graph.addElement(5);
        graph.addElement(6);
        graph.addElement(7);
        graph.connect(3, 9, 80);
        graph.connect(9, 5, 80);
        System.out.println(graph);
    }

    @Test
    void connectExists() {
        Graph<Integer> graph = new Graph<>();
        graph.addElement(3);
        graph.addElement(4);
        graph.addElement(5);
        graph.addElement(6);
        graph.addElement(7);
        graph.connect(3, 4, 80);
        graph.connect(2, 5, 80);
        System.out.println(graph);
    }

    @Test
    void connectExistsCircular() {
        Graph<Integer> graph = new Graph<>();
        graph.addElement(2);
        graph.addElement(3);
        graph.addElement(4);
        graph.addElement(5);
        graph.addElement(6);
        graph.addElement(7);
        graph.connect(3, 4, 80);
        graph.connect(4, 3, 80);
        System.out.println(graph);
        graph.connect(4, 2, 80);
        System.out.println(graph);
    }
}