package main;

import backend.Bill;
import backend.Route;
import backend.Station;
import backend.Ticket;
import com.google.gson.reflect.TypeToken;
import util.graph.Graph;
import util.LinkedList;

import java.lang.reflect.Type;
import java.util.logging.Logger;

public final class Settings {
    public enum TypeJson {
        ;
        public static final Type BILL_TYPE = new TypeToken<Bill>() {
        }.getType();
        public static final Type TICKET_TYPE = new TypeToken<Ticket>() {
        }.getType();
        public static final Type STATION_TYPE = new TypeToken<Station>() {
        }.getType();
        public static final Type ROUTE_TYPE = new TypeToken<Route>() {
        }.getType();
        public static final Type STATION_LIST_TYPE = new TypeToken<LinkedList<Station>>() {
        }.getType();
        public static final Type GRAPH_TYPE = new TypeToken<Graph<Station>>() {
        }.getType();
        public static final Type RESERVATIONS_TYPE = new TypeToken<LinkedList<Ticket>>() {
        }.getType();

    }

    public enum Constants {
        ;
        public static final int PRICE_KM = 25;
        private static final String DB_PATH = "C:\\Users\\eduar\\Desktop\\Railspot\\Railspot\\src\\main\\java\\backend\\DB\\";
        public static final String GRAPH_PATH = DB_PATH + "map.json";
        public static final String RESERVATIONS_PATH = DB_PATH + "reservations.json";
    }

    public enum Loggers {
        ;
        public static final Logger BILL = Logger.getLogger("Bill");
        public static final Logger STATION = Logger.getLogger("Station");
        public static final Logger EDGE = Logger.getLogger("Edge");
        public static final Logger VERTEX = Logger.getLogger("Vertex");
        public static final Logger DIJKSTRA_HELPER = Logger.getLogger("Dijkstra_Helper");
        public static final Logger GRAPH = Logger.getLogger("Graph");
        public static final Logger JSON_WRITER = Logger.getLogger("Json_writer");
        public static final Logger JSON_LOADER = Logger.getLogger("Json_Loader");
        public static final Logger SERIALIZER = Logger.getLogger("Serializer");
        public static final Logger LIST = Logger.getLogger("LinkedList");
        public static final Logger ADMINISTRATION = Logger.getLogger("Rest_admin");
        public static final Logger TRAVELS = Logger.getLogger("Rest_travel");
        public static final Logger RAILSPOT = Logger.getLogger("Main driver.") ;
    }


}
