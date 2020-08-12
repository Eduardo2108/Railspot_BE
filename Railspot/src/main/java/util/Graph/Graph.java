package util.Graph;


import util.LinkedList;

public class Graph<T extends Comparable<T>> {
    private final LinkedList<Vertex<T>> elements;

    public Graph() {
        this.elements = new LinkedList<>();
    }

    /**
     * Method for adding a node on the graph
     *
     * @param data content of the new graph
     */
    public void addElement(T data) {
        //verificar que no este repetido
        this.elements.add(new Vertex<>(data));
    }

    /**
     * Deleting a node from the graph
     *
     * @param element element to be deleted
     */
    public void deleteElement(T element) {
        this.elements.delete(new Vertex<>(element));
    }

    /**
     * Delete the connection between two nodes
     *
     * @param node1 start of the route
     * @param node2 end of the route
     */
    public void disconnect(T node1, T node2) {
        try {
            Vertex<T> var1 = this.elements.getElement(new Vertex<>(node1));
            Vertex<T> var2 = this.elements.getElement(new Vertex<>(node2));
            //disconect a node from another
            var1.disconnect(var2);
        } catch (NullPointerException ignored) {
        }
    }

    /**
     * Add a connection between two nodes
     *
     * @param node1  start of the connection
     * @param node2  end of the connection
     * @param weight weight of the connections.
     */
    public void connect(T node1, T node2, int weight) {
        try {
            Vertex<T> var1 = this.elements.getElement(new Vertex<>(node1));
            Vertex<T> var2 = this.elements.getElement(new Vertex<>(node2));
            //verify
            if (var1 == null || var2 == null) return;
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

    /**
     * Method for getting the shortest path from a node to all others, using dijkstra algorithm
     *
     * @param source content of the node to calculate routes from.
     * @return list of nodes, with the corresponding predecessors and weights.
     */
    public LinkedList<DijkstraHelper<Vertex<T>>> dijkstraAlgorithm(T source) {
        //the source node
        Vertex<T> sourceNode = this.elements.getElement(new Vertex<>(source));
        if (sourceNode == null) return null;
        //initialize the list for the algorithm
        LinkedList<DijkstraHelper<Vertex<T>>> results = new LinkedList<>();
        DijkstraHelper<Vertex<T>> sourceHelper = new DijkstraHelper<>(sourceNode);
        sourceHelper.setWeight(0);
        results.addFirst(sourceHelper);
        for (int i = 0; i < this.elements.len; i++) {
            if (elements.getElement(i).compareTo(sourceNode) != 0) {
                //create a new instance with the content of the node, for each node on the list of elements in the graph
                results.add(new DijkstraHelper<>(this.elements.getElement(i)));
            }
        }

        //do while there are nodes unvisited
        // first with the source node, because its the start point for the algorithm.
        DijkstraHelper<Vertex<T>> current = results.getElement(0);
        while (current != null) {
            //connections of the current node.
            LinkedList<Edge<T>> currentConnections = current.getNode().getEdges();
            for (int i = 0; i < currentConnections.len; i++) {
                // find the equivalent and ask
                Edge<T> connectionsElement = currentConnections.getElement(i);
                DijkstraHelper<Vertex<T>> connectionComponent = results.getElement(new DijkstraHelper<>(connectionsElement.getConnection()));
                //current weight + current connection < dijkstra obj, weight¿?
                if ((current.weight + connectionsElement.getWeight()) < connectionComponent.weight) {
                    //yes? -> change the predecessor and weight
                    connectionComponent.setWeight(current.weight + connectionsElement.getWeight());
                    connectionComponent.setPre(current.getNode());
                }
            }
            //mark the node visited
            current.visited = true;
            current = this.findNext(results);
            //find the next not visited node and check if there are not visited nodes to finish the algorithm.

        }
        return results;
    }

    public Path<T> shortestPath(T start, T end) {
        LinkedList<DijkstraHelper<Vertex<T>>> result = this.dijkstraAlgorithm(start);
        Path<T> path = new Path<>();

        DijkstraHelper<Vertex<T>> endingPoint = result.getElement(new DijkstraHelper<>(new Vertex<>(end)));
        path.setWeight(endingPoint.getWeight());
        while (endingPoint.getPre() != null) {
            path.addNode(endingPoint.node.getData());
            endingPoint = result.getElement(new DijkstraHelper<>(endingPoint.getPre()));
        }
        path.addNode(endingPoint.node.getData());
        return path;
    }

    private DijkstraHelper<Vertex<T>> findNext(LinkedList<DijkstraHelper<Vertex<T>>> list) {
        DijkstraHelper<Vertex<T>> result = null;
        for (int i = 0; i < list.len; i++) {
            DijkstraHelper<Vertex<T>> element = list.getElement(i);
            if (!element.visited) {
                if (result == null) {
                    result = element;
                } else if (element.compareTo(result) < 0) {
                    result = element;
                }
            }
        }
        return result;
    }
}
