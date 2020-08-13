package util.Graph;

import util.LinkedList;

class Vertex<T extends Comparable<T>> implements Comparable<Vertex<T>> {

    public Vertex(T data) {
        this.data = data;
        this.edges = new LinkedList<>();
    }

    @Override
    public String toString() {
        return this.data.toString();
    }

    private T data;

    public void connect(Vertex<T> vertex, int weight) {
        this.edges.add(new Edge<>(vertex, weight));
    }

    public void disconnect(Vertex<T> vertex) {
        this.edges.delete(new Edge<>(vertex, 1));
    }

    public T getData() {
        return this.data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public LinkedList<Edge<T>> getEdges() {
        if(this.edges == null){
            this.edges = new LinkedList<>();

        }
        return edges;
    }

    private LinkedList<Edge<T>> edges;

    @Override
    public int compareTo(Vertex<T> o) {
        return data.compareTo(o.data);
    }

}
