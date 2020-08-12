package backend;

import util.LinkedList;

public class Route implements Comparable<Route> {
    private LinkedList<Station> route;


    public void addStop(Station newStation) {
        this.route.add(newStation);
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
}
