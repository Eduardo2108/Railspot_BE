package util.Graph;

import util.LinkedList;

public class Path<T extends Comparable> {
    private LinkedList list = new LinkedList();
            ;
    private int weight;

    public void addNode(T data) {
        this.list.add(data);
    }

    @Override
    public String toString() {
        return "Path{" +
                "list=" + list +
                ", weight=" + weight +
                '}';
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }
}
