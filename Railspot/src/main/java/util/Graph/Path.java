package util.graph;

import util.LinkedList;

public class Path<T extends Comparable<T>> {
    private LinkedList<T> list = new LinkedList<>();
    ;
    private int weight;

    public void addNode(T data) {
        this.list.addFirst(data);
    }

    @Override
    public String toString() {
        return "Path{" +
                "list=" + list +
                ", weight=" + weight +
                '}';
    }

    public LinkedList<T> getRoute() {
        return this.list;
    }

    public int getWeight() {
        return this.weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
