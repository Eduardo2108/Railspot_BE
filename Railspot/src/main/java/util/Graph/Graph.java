package util.graph;


import util.LinkedList;

import java.io.IOException;

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
     * @throws IOException if the element is not on the graph
     */
    public void deleteElement(T element) throws IOException {
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
        } catch (NullPointerException | IOException ignored) {
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
        final StringBuilder sb = new StringBuilder("Lista de adyacencia grafo: {").append("\n");
        for (int i = 0; i < this.elements.getLen(); i++) {
            sb.append("Node: ").
                    append(elements.getElement(i).getData()).
                    append("Connections: ").append(elements.getElement(i).getEdges()).append("\n");

        }
        return sb.toString();
    }

    /**
     * Method for getting the shortest path from a node to all others, using dijkstra algorithm
     *
     * @param source content of the node to calculate routes from.
     * @return list of nodes, with the corresponding predecessors and weights.
     */
    public LinkedList<VertexHelper<Vertex<T>>> dijkstraAlgorithm(T source) {
        //the source node
        Vertex<T> sourceNode = this.elements.getElement(new Vertex<>(source));
        if (sourceNode == null) return null;

        //initialize the list for the algorithm
        LinkedList<VertexHelper<Vertex<T>>> results = new LinkedList<>();
        VertexHelper<Vertex<T>> sourceHelper = new VertexHelper<>(sourceNode);
        sourceHelper.setWeight(0);
        results.addFirst(sourceHelper);
        for (int i = 0; i < this.elements.getLen(); i++) {
            if (elements.getElement(i).compareTo(sourceNode) != 0) {
                //create a new instance with the content of the node, for each node on the list of elements in the graph
                results.add(new VertexHelper<>(this.elements.getElement(i)));
            }
        }

        //do while there are nodes unvisited
        // first with the source node, because its the start point for the algorithm.
        VertexHelper<Vertex<T>> current = results.getElement(0);
        while (current != null) {
            //connections of the current node.
            LinkedList<Edge<T>> currentConnections = current.getNode().getEdges();
            for (int i = 0; i < currentConnections.getLen(); i++) {
                // find the equivalent and ask
                Edge<T> connectionsElement = currentConnections.getElement(i);
                VertexHelper<Vertex<T>> connectionComponent = results.getElement(new VertexHelper<>(connectionsElement.getConnection()));
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
        LinkedList<VertexHelper<Vertex<T>>> result = this.dijkstraAlgorithm(start);
        Path<T> path = new Path<>();

        VertexHelper<Vertex<T>> endingPoint = result.getElement(new VertexHelper<>(new Vertex<>(end)));
        path.setWeight(endingPoint.getWeight());
        while (endingPoint.getPre() != null) {
            path.addNode(endingPoint.node.getData());
            endingPoint = result.getElement(new VertexHelper<>(endingPoint.getPre()));
        }
        if (endingPoint.getNode().getData().compareTo(start) != 0) {
            System.out.println("The was trouble finding the path, maybe there's no possible path to connect the nodes.");
            return null;

        }
        path.addNode(endingPoint.node.getData());
        return path;
    }

    private VertexHelper<Vertex<T>> findNext(LinkedList<VertexHelper<Vertex<T>>> list) {
        VertexHelper<Vertex<T>> result = null;
        for (int i = 0; i < list.getLen(); i++) {
            VertexHelper<Vertex<T>> element = list.getElement(i);
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

    public LinkedList<T> getElements() {
        LinkedList<T> result = new LinkedList<>();
        for (int i = 0; i < this.elements.getLen(); i++) {
            result.add(this.elements.getElement(i).getData());
        }
        return result;
    }
}
