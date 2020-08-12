package main ;
import backend.Route;
import backend.Station;
import util.Graph.Graph;


/**
 * Driver class for the server, facade for the management of the router, buys and configurations of the server
 */
public class Railspot {
    private static Railspot instance;
    private Graph<Station> map;

    //constructor
    private Railspot() {
        this.map = new Graph<>();
    }

    //singleton
    public static synchronized Railspot getInstance() {
        if (instance == null) {
            instance = new Railspot();
        }
        return instance;
    }

    public void createStation(Station newStation) {
        this.map.addElement(newStation);
    }

    public void deleteStation(Station station) {
        this.map.deleteElement(station);
    }

    public Route shortestPath(Station a, Station b) {
        Route route = new Route();
        route.setPath(this.map.shortestPath(a, b));
        return route;

    }

    public void setMap(Graph<Station> map) {
        this.map = map;
    }
}
