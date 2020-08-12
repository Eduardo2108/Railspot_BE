package backend;

import util.LinkedList;

public class Station implements Comparable<Station> {
    private String name;
    private LinkedList<Ticket> tickets;

    public Station(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                ", tickets=" + tickets +
                '}';
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    @Override
    public int compareTo(Station o) {
        return this.name.compareTo(o.name);
    }

    public String getName() {
        return this.name;
    }
}
