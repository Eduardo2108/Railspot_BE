package util.Graph;

import util.LinkedList;

class Vertex<T extends Comparable<T>> implements Comparable<Vertex<T>> {

    public Vertex(T data) {
        this.data = data;
        this.edges = new LinkedList<>();
    }

    @Override
    public String toString() {
        return "Vertex{ " + this.hashCode() + "||" +
                "data=" + data +
                ", edges=" + edges +
                '}';
    }

    private T data;

    public void connect(Vertex<T> vertex, int weight) {
        this.edges.add(new Edge<>(vertex, weight));
    }

    public void disconnect(Vertex<T> vertex) {
        this.edges.delete(new Edge<>(vertex, 1));
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LinkedList<Edge<T>> getEdges() {
        return edges;
    }

    private final LinkedList<Edge<T>> edges;

    @Override
    public int compareTo(Vertex<T> o) {
        return data.compareTo(o.data);
    }

}
