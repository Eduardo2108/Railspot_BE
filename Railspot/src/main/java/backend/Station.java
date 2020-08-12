package backend;

import util.LinkedList;

public class Station implements Comparable<Station> {
    private LinkedList<Ticket> tickets;

    public void addTicket(Ticket ticket) {
        this.tickets.add(ticket);
    }

    @Override
    public int compareTo(Station o) {
        return 0;
    }
}
