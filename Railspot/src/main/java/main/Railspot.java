package main;

import backend.Bill;
import backend.Route;
import backend.Station;
import backend.Ticket;
import util.Graph.Graph;
import util.LinkedList;
//todo: login on files reading

/**
 * Driver class for the server, facade for the management of the router, buys and configurations of the server
 */
public class Railspot {
    /**
     * Field for the singleton
     */
    private static Railspot instance;
    private LinkedList<Ticket> reservations;
    /**
     * Field for the stations on a directed graph
     */
    private Graph<Station> map;

    /**
     * Constructor
     */
    private Railspot() {
        this.reservations = new LinkedList<>();
        this.map = new Graph<>();
    }

    /**
     * singleton
     */
    public static synchronized Railspot getInstance() {
        if (instance == null) {

            instance = new Railspot();
        }
        return instance;
    }

    /**
     * Create stations
     *
     * @param newStation new station to create
     */

    public void createStation(Station newStation) {
        this.map.addElement(newStation);
    }

    public void deleteStation(Station station) {
        if (this.getMap().getElements().getElement(station).getTickets().len != 0) {
            return;
        }
        this.map.deleteElement(station);
    }

    /**
     * Calculate the shortest path between two stations.
     *
     * @param a start station
     * @param b destination
     * @return route object with the station list and the price of all trip
     */
    public Route shortestPath(Station a, Station b) {
        Route route = new Route();
        route.setPath(this.map.shortestPath(a, b));
        return route;

    }

    /**
     * Method for buying a ticket to a given route, should generate the price, based on the amount of tickets
     * and add a ticket to the stations on the route
     *
     * @param route  route object
     * @param amount amount of tickets to buy
     * @param id     id of the client, for verification on trains
     * @param date   actual day of the buy
     * @return Bill generated with the info of the purchase.
     */
    public Bill purchaseTicket(Route route, int amount, String id, String date) {
        try {
            //int discount = ((amount <= 46)&&amount>1) ? amount * 2 : 90;
            int discount = 100;
            double price = (route.getDistance()) * Settings.Constants.PRICE_KM * ((double) discount / 100);
            Bill bill = new Bill.Builder().price(price).date(date).id(id).build();
            Ticket ticket = new Ticket(price, id, date);
            route.addTicket(ticket);
            this.reservations.add(ticket);
            System.out.println("ticked bought");
            return bill;
        } catch (Exception e) {
            System.out.println(e.getCause());
            return null;
        }


    }

    public void connect(Station start, Station end, int weight) {
        try {
            this.map.connect(start, end, weight);
        } catch (Exception ignored) {

        }
    }

    public LinkedList<Station> getElements() {

        return this.map.getElements();
    }

    public void disconnect(Station station1, Station station2) {
        this.map.disconnect(station1, station2);
    }

    public LinkedList<Ticket> getReservationsByID(String id) {
        LinkedList<Ticket> results = new LinkedList<>();
        for (int i = 0; i < this.reservations.len; i++) {
            if (this.reservations.getElement(i).getOwnerID().equals(id))
                results.add(this.reservations.getElement(i));
        }
        return results;
    }

    public LinkedList<Ticket> getReservationsByDate(String date) {
        LinkedList<Ticket> results = new LinkedList<>();
        for (int i = 0; i < this.reservations.len; i++) {
            if (this.reservations.getElement(i).getDate().equals(date))
                results.add(this.reservations.getElement(i));
        }
        return results;
    }

    public LinkedList<Ticket> getReservationsByStation(String name) {
        return this.map.getElements().getElement(new Station(name)).getTickets();
    }

    public Graph<Station> getMap() {
        return this.map;
    }

    /**
     * Set the map of the instance to a new one .
     *
     * @param map
     */
    public void setMap(Graph<Station> map) {
        this.map = map;
    }

    public LinkedList<Ticket> getReservations() {
        return this.reservations;
    }
}

