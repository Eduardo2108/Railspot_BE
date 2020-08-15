package util.graph;

import java.util.Objects;

/**
 * Class for the connections between nodes
 *
 * @param <T> type of data
 */
class Edge<T extends Comparable<T>> implements Comparable<Edge<T>> {

    private Integer weight;

    private Vertex<T> connection;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return Objects.equals(weight, edge.weight) &&
                Objects.equals(connection, edge.connection);
    }

    @Override
    public int hashCode() {
        return Objects.hash(weight, connection);
    }

    public Edge(Vertex<T> vertex, int weight) {
        this.weight = weight;
        this.connection = vertex;
    }

    @Override
    public String toString() {
        return "Edge{" +
                "weight=" + weight +
                ", connection=" + ((connection != null) ? connection.getData() : "" +
                "") +
                '}';
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
