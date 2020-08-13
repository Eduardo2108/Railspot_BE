package util.tools;

import backend.Route;
import backend.Station;
import com.google.gson.Gson;
import org.junit.jupiter.api.Test;

class SerializerTest {

    @Test
    void station() {
        Station station = new Station("Prueba");
        System.out.println(Serializer.station(station));
    }

    @Test
    void testStation() {
        Station station = new Station("Prueba");
        String stationJson = (Serializer.station(station));
        System.out.println(Serializer.station(stationJson));
    }

    @Test
    void route() {
        Route route = new Route();
        route.addStop(new Station("Stop 1" ));
        route.addStop(new Station("Stop 2" ));
        route.addStop(new Station("Stop 3" ));
        System.out.println(Serializer.route(route));
    }

    @Test
    void testRoute() {
    }

    @Test
    void bills() {
    }

    @Test
    void testBills() {
    }

    @Test
    void ticket() {
    }

    @Test
    void testTicket() {
    }
}