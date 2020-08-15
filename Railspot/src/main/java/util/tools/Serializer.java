package util.tools;

import backend.Bill;
import backend.Route;
import backend.Station;
import backend.Ticket;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import main.Settings;
import util.LinkedList;

public abstract class Serializer {
    private static final Gson gson = new Gson();

    public static String station(Station station) {
        return gson.toJson(station, Settings.TypeJson.STATION_TYPE);
    }

    public static Station station(String station) {
        return gson.fromJson(station, Settings.TypeJson.STATION_TYPE);
    }

    public static String route(Route route) {
        return gson.toJson(route, Settings.TypeJson.ROUTE_TYPE);
    }

    public static Route route(String route) {
        return gson.fromJson(route, Settings.TypeJson.ROUTE_TYPE);
    }

    public static String bills(Bill bill) {
        return gson.toJson(bill, Settings.TypeJson.BILL_TYPE);
    }

    public static Bill bills(String bill) {
        return gson.fromJson(bill, Settings.TypeJson.BILL_TYPE);
    }

    public static String ticket(Ticket ticket) {
        return gson.toJson(ticket, Settings.TypeJson.TICKET_TYPE);
    }

    public static Ticket ticket(String ticket) {
        return gson.fromJson(ticket, Settings.TypeJson.TICKET_TYPE);
    }

    public static String stations(LinkedList<Station> list) {
        JsonArray array = new JsonArray();
        for (int i = 0; i < list.getLen(); i++) {
            array.add(list.getElement(i).getName());
        }
        return array.toString();
    }

    public static String tickets(LinkedList<Ticket> list) {
        JsonArray array = new JsonArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.getLen(); i++) {
            Ticket ticket = list.getElement(i);
            sb.append("Identificacion: ").append(ticket.getOwnerID()).append(" - ").
                    append("Fecha: ").append(ticket.getDate()).append(" - ").
                    append("Precio: ").append(ticket.getPrice()).append(".");

            array.add(sb.toString());
            sb = new StringBuilder();
        }
        return array.toString();
    }
}
