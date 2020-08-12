package backend;

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
