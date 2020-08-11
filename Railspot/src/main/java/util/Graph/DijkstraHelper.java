package util.Graph;

/**
 * This class can work for getting the pair of vertex - predecessor - weight for running dijkstra algorithm
 *
 * @param <T> type of the object, same as the nodes of the graph
 */
class DijkstraHelper<T extends Comparable<T>> implements Comparable<DijkstraHelper<T>> {
    /**
     * weight, sum of total weights to get here
     */
    int weight;
    /**
     * predecessor node of the node
     */
    T pre;
    /**
     * vertex fo the graph
     */
    T node;
    /**
     * true if all connections run on dijkstra too.
     */
    boolean visited;

    public DijkstraHelper(T node) {
        this.node = node;
        this.pre = null;
        this.weight = Integer.MAX_VALUE;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public T getPre() {
        return pre;
    }

    public void setPre(T pre) {
        this.pre = pre;
    }

    public T getNode() {
        return node;
    }

    public void setNode(T node) {
        this.node = node;
    }

    @Override
    public int compareTo(DijkstraHelper<T> o) {
        //will be the same if has the same node on content
        return this.node.compareTo(o.getNode());
    }


}
