package util.Graph;

/**
 * Class for the connections betweeen nodes
 *
 * @param <T> tyupe of dataa
 */
class Edge<T extends Comparable<T>> implements Comparable<Edge<T>> {

    private Integer weight;

    private Vertex<T> connection;

    @Override
    public String toString() {
        return "Edge{" +
                "weight=" + weight +
                ", connection=" + ((connection != null) ? connection.hashCode() : "" +
                "") +
                '}';
    }

    public Edge(Vertex<T> vertex, int weight) {
        this.weight = weight;
        this.connection = vertex;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Vertex<T> getConnection() {
        return connection;
    }

    public void setConnection(Vertex<T> connection) {
        this.connection = connection;
    }

    //used for deletions

    @Override
    public int compareTo(Edge<T> o) {
        return this.connection.compareTo(o.connection);
    }
}
