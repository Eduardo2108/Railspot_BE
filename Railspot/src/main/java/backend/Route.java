package backend;

import util.Graph.Path;
import util.LinkedList;

public class Route implements Comparable<Route> {
    private LinkedList<Station> route;
    private int price;


    public void addStop(Station newStation) {
        this.route.add(newStation);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Route{");
        for (int i = 0; i < route.len; i++) {
            sb.append("-->").append(route.getElement(i).getName()).append("-->");
        }
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }

    public void addTicket(Ticket ticket) {
        for (int i = 0; i < this.route.len; i++) {
            this.route.getElement(i).addTicket(ticket);

        }
    }


    @Override
    public int compareTo(Route o) {
        return 0;
    }

    public void setPath(Path<Station> path) {
        this.route = path.getRoute();
        this.price = path.getWeight();
    }
}
