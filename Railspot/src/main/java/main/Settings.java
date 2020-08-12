package main;

import backend.Bill;
import backend.Route;
import backend.Station;
import backend.Ticket;
import com.google.gson.reflect.TypeToken;
import util.LinkedList;

import java.lang.reflect.Type;

public final class Settings {
    enum TypeJson{
        ;
        public static final Type BILL_TYPE = new TypeToken<Bill>() {}.getType();
        public static final Type TICKET_TYPE = new TypeToken<Ticket>() {}.getType();
        public static final Type STATION_TYPE = new TypeToken<Station>() {}.getType();
        public static final Type ROUTE_TYPE = new TypeToken<Route>() {}.getType();
        public static final Type STATION_LIST_TYPE = new TypeToken<LinkedList<Station>>() {}.getType();
    }
    enum Constants{
        ;
        public static final int PRICE_KM = 25;
    }
    enum Loggers{
        ;
    }


}
