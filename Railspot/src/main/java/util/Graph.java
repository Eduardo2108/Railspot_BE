package util;


public class Graph<T extends Comparable<T>> {
    private int len;
    private final LinkedList<Vertex<T>> elements;

    public Graph() {
        this.elements = new LinkedList<>();
        this.len = 0;
    }

    public void addElement(T data) {
        //verificar que no este repetido
        this.len++;
        this.elements.add(new Vertex<>(data));
    }

    public void deleteElement(T element) {
        this.len--;
        this.elements.delete(new Vertex<>(element));
    }

    public void disconnec(T node1, T node2) {
        try {
            Vertex<T> var1 = this.elements.getElement(new Vertex<>(node1));
            Vertex<T> var2 = this.elements.getElement(new Vertex<>(node2));
            //disconect a node from another
            var1.disconnect(var2);
        } catch (NullPointerException ignored) {
        }
    }

    public void connect(T node1, T node2, int weight) {
        try {
            Vertex<T> var1 = this.elements.getElement(new Vertex<>(node1));
            Vertex<T> var2 = this.elements.getElement(new Vertex<>(node2));
            //verify
            if(var1 == null || var2== null) return;
            //connect on one way
            var1.connect(var2, weight);
        } catch (NullPointerException ignored) {

        }
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();
        string.append("Adjacency Lists").append("\n");
        for (int i = 0; i < this.elements.len; i++) {
            string.append(this.elements.getElement(i)).append("\n");
        }
        return string.toString();
    }

    class Vertex<T extends Comparable<T>> implements Comparable<Vertex<T>> {
        public Vertex(T data) {
            this.data = data;
            this.edges = new LinkedList<>();
        }

        @Override
        public String toString() {
            return "Vertex{" +
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

    class Edge<T extends Comparable<T>> implements Comparable<Edge<T>> {

        private Integer weight;
        private Vertex<T> connection;

        @Override
        public String toString() {
            return "Edge{" +
                    "weight=" + weight +
                    ", connection=" + ((connection != null) ? connection.getData() : "" +
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
}
