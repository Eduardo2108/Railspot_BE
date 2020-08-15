package main;

import backend.Bill;
import backend.Route;
import backend.Station;
import backend.Ticket;
import util.graph.Graph;
import util.LinkedList;
import util.tools.JsonWriter;
import util.tools.jsonReader;

import java.io.IOException;
import java.util.logging.Level;

/**
 * Driver class for the server, facade for the management of the router, buys and configurations of the server
 */
public class Railspot {
    /**
     * Field for the singleton
     */
    private static Railspot instance;
    private final LinkedList<Ticket> reservations;
    /**
     * Field for the stations on a directed graph
     */
    private final Graph<Station> map;

    /**
     * Constructor
     */
    private Railspot() {
        this.reservations = jsonReader.loadReservations();
        this.map = jsonReader.loadStations();
    }

    /**
     * Method for the singleton pattern
     * @return instance of the class.
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
        JsonWriter.updateGraph();
    }

    public void deleteStation(Station station) throws IOException {
        if (this.getMap().getElements().getElement(station).getTickets().getLen() != 0) {
            throw new IllegalArgumentException("La estación tiene tiquetes activos, no se puede eliminar.");
        }
        this.map.deleteElement(station);
        JsonWriter.updateGraph();

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
            int discount = ((amount <= 46) && amount > 1) ? amount * 2 : 90;
            double price = (route.getDistance()) * Settings.Constants.PRICE_KM * ((double) discount / 100);
            Bill bill = new Bill.Builder().price(price).date(date).id(id).build();
            Ticket ticket = new Ticket(price, id, date);
            route.addTicket(ticket);
            this.reservations.add(ticket);
            Settings.Loggers.RAILSPOT.log(Level.INFO, "ticked bought");
            JsonWriter.updateReservations();
            return bill;
        } catch (Exception e) {
            Settings.Loggers.RAILSPOT.log(Level.WARNING, e.getMessage());
            return null;
        }


    }

    public void connect(Station start, Station end, int weight) {
        try {
            this.map.connect(start, end, weight);
            JsonWriter.updateGraph();
        } catch (Exception e) {
            Settings.Loggers.RAILSPOT.log(Level.WARNING, e.getMessage());

        }
    }

    public LinkedList<Station> getElements() {

        return this.map.getElements();
    }

    public void disconnect(Station station1, Station station2) {
        this.map.disconnect(station1, station2);
        JsonWriter.updateGraph();

    }

    public LinkedList<Ticket> getReservationsByID(String id) {
        LinkedList<Ticket> results = new LinkedList<>();
        for (int i = 0; i < this.reservations.getLen(); i++) {
            if (this.reservations.getElement(i).getOwnerID().equals(id))
                results.add(this.reservations.getElement(i));
        }
        return results;
    }

    public LinkedList<Ticket> getReservationsByDate(String date) {
        LinkedList<Ticket> results = new LinkedList<>();
        for (int i = 0; i < this.reservations.getLen(); i++) {
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

    public LinkedList<Ticket> getReservations() {
        return this.reservations;
    }
}

