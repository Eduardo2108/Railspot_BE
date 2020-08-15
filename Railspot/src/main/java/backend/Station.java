package backend;

import util.LinkedList;

public class Station implements Comparable<Station> {
    private final String name;
    private final LinkedList<Ticket> tickets;

    public Station(String name) {
        this.name = name;
        this.tickets = new LinkedList<>();
    }

    public String toString() {
        return this.name;
    }

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    public int compareTo(Station o) {
        return this.name.compareTo(o.name);
    }

    public String getName() {
        return this.name;
    }

    public LinkedList<Ticket> getTickets() {
        return this.tickets;
    }
}
