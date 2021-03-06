package util.graph;

import util.LinkedList;

import java.io.IOException;

class Vertex<T extends Comparable<T>> implements Comparable<Vertex<T>> {

    private LinkedList<Edge<T>> edges;
    private T data;

    public Vertex(T data) {
        this.data = data;
        this.edges = new LinkedList<>();
    }

    @Override
    public String toString() {
        return this.data.toString();
    }

    public void connect(Vertex<T> vertex, int weight) {
        this.edges.add(new Edge<>(vertex, weight));
    }

    public void disconnect(Vertex<T> vertex) throws IOException {
        this.edges.delete(new Edge<>(vertex, 1));
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LinkedList<Edge<T>> getEdges() {
        if (this.edges == null) {
            this.edges = new LinkedList<>();

        }
        return edges;
    }


    @Override
    public int compareTo(Vertex<T> o) {
        return data.compareTo(o.data);
    }

}
