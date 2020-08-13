package main;

import backend.Bill;
import backend.Route;
import backend.Station;
import backend.Ticket;
import util.Graph.Graph;
import util.LinkedList;
/*
todo: add the json files writing and reading for the tickets
todo: manage the serialization of json files and de serialization for the http requestsl
 */

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
        //todo: convert the type to string and use gson
        this.map.addElement(newStation);
    }

    public void deleteStation(Station station) {
        //todo: convert the type to string and use gson
        if (station.getTickets().len != 0) {
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
        //todo: convert the types to string and use gson
        Route route = new Route();
        route.setPath(this.map.shortestPath(a, b));
        return route;

    }

    /**
     * Set the map of the instance to a new one .
     *
     * @param map
     */
    public void setMap(Graph<Station> map) {
        //todo: use this method for loading the graph from the json files.
        this.map = map;
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
        //amount of discount on %, máx 90% and its 2% for additional ticket
        int discount = (amount <= 46) ? amount * 2 : 90;
        double price = (route.getDistance()) * Settings.Constants.PRICE_KM * ((double) discount / 100);
        try {
            Bill bill = new Bill.Builder().price(price).date(date).id(id).build();
            Ticket ticket = new Ticket(price, id, date);
            route.addTicket(ticket);
            System.out.println("ticked bought");
            return bill;
        } catch (Exception e) {
            System.out.println(e.getCause());
            return null;
        }


    }

    public void connect(Station start, Station end, int weiight) {
        try {
            this.map.connect(start, end, weiight);
        } catch (Exception ignored) {

        }
    }

    public LinkedList<Station> getElements() {
        return this.map.getElements();
    }
}
