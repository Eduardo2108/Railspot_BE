package backend;

import util.LinkedList;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Station station = (Station) o;
        return Objects.equals(name, station.name) &&
                Objects.equals(tickets, station.tickets);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tickets);
    }

    public LinkedList<Ticket> getTickets() {
        return this.tickets;
    }
}
