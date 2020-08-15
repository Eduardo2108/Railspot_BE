package backend;

import java.util.Objects;

public class Ticket implements Comparable<Ticket> {
    private double price;
    private String date;
    private String ownerID;

    public Ticket(double price, String id, String date) {
        this.price = price;
        this.ownerID = id;
        this.date = date;
    }

    @Override
    public int compareTo(Ticket o) {
        return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return Double.compare(ticket.price, price) == 0 &&
                Objects.equals(date, ticket.date) &&
                Objects.equals(ownerID, ticket.ownerID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, date, ownerID);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOwnerID() {
        return ownerID;
    }

    public void setOwnerID(String ownerID) {
        this.ownerID = ownerID;
    }
}
