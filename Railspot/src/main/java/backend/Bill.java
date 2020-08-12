package backend;

public class Bill {
    private double price;
    private String id;
    private String date;

    private Bill() {}

    public double getPrice() {

        return price;
    }

    public String getId() {
        return id;
    }

    public String getDate() {
        return date;
    }
    
    public static class Builder {
        private double price;
        private String id;
        private String date;

        public Builder price(double price) {
            this.price = price;
            return this;
        }

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder date(String date) {
            this.date = date;
            return this;
        }

        public Bill build() {
            Bill newBill = new Bill();
            newBill.date = this.date;
            newBill.id = this.id;
            newBill.price = this.price;
            return newBill;
        }
    }
}
