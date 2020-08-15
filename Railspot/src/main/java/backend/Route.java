package backend;

import main.Railspot;
import main.Settings;
import util.graph.Path;
import util.LinkedList;

import java.util.Objects;

public class Route implements Comparable<Route> {

    private LinkedList<Station> stations;
    private int distance;

    public Route() {
        this.stations = new LinkedList<>();
    }

    public void addStop(Station newStation) {
        this.stations.add(newStation);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Route route = (Route) o;
        return distance == route.distance &&
                stations.equals(route.stations);
    }

    @Override
    public int hashCode() {
        return Objects.hash(stations, distance);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Route{");
        for (int i = 0; i < stations.getLen(); i++) {
            sb.append("-->").append(stations.getElement(i).getName()).append("-->");
        }
        sb.append(", price=").append(distance);
        sb.append('}');
        return sb.toString();
    }

    public void addTicket(Ticket ticket) {
        for (int i = 0; i < this.stations.getLen(); i++) {
            Railspot.getInstance().getMap().getElements().getElement(this.stations.getElement(i)).addTicket(ticket);
        }
    }


    @Override
    public int compareTo(Route o) {
        return 0;
    }

    public int getDistance() {
        return this.distance;
    }

    public LinkedList<Station> getPath() {
        return this.stations;
    }

    public void setPath(Path<Station> path) {
        this.stations = path.getRoute();
        this.distance = path.getWeight();
    }

    public int getPrice() {
        return this.distance * Settings.Constants.PRICE_KM;
    }
}
