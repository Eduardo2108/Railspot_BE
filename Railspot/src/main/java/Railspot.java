import backend.Route;
import backend.Station;
import util.Graph.Graph;
import util.LinkedList;

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

    public LinkedList<Route> shortestPath(Station a, Station b) {
        return null;

    }
}
