package main;

import backend.Route;
import backend.Station;
import util.Graph.Graph;


/**
 * Driver class for the server, facade for the management of the router, buys and configurations of the server
 */
public class Railspot {
    /**
     * Field for the singleton
     */
    private static Railspot instance;
    /**
     * Field for the stations on a directed graph
     */
    private Graph<Station> map;

    /**
     * Constructor
     */
    private Railspot() {
        this.map = new Graph<>();
    }

    /**
     * singleton
     */
    public static synchronized Railspot getInstance() {
        if (instance == null) {
            instance = new Railspot();
        }
        return instance;
    }

    /**
     * Create stations
     * @param newStation new station to create
     */

    public void createStation(Station newStation) {
        //todo: convert the type to string and use gson
        this.map.addElement(newStation);
    }

    public void deleteStation(Station station) {
        //todo: convert the type to string and use gson
        //todo: verify the non existence of any tickets bought
        this.map.deleteElement(station);
    }

    /**
     * Calculate the shortest path between two stations.
     * @param a start station
     * @param b destination
     * @return route object with the station list and the price of all trip
     */
    public Route shortestPath(Station a, Station b) {
        //todo: convert the types to string and use gson

        Route route = new Route();
        route.setPath(this.map.shortestPath(a, b));
        return route;

    }

    /**
     * Set the map of the instance to a new one .
     * @param map
     */
    public void setMap(Graph<Station> map) {
        //todo: use this method for loading the graph from the json files.
        this.map = map;
    }
}
