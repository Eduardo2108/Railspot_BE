package util.tools;

import backend.Station;
import main.Railspot;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JsonWriterTest {

    @Test
    void updateGraph() {
        Railspot railspot = Railspot.getInstance();
        Station A = new Station("Estacion A");
        Station B = new Station("Estacion B");
        railspot.createStation(A);
        railspot.createStation(B);
        railspot.connect(A,B, 30);
        JsonWriter.updateGraph();
        System.out.println(jsonReader.loadStations());
    }

    @Test
    void updateReservations() {
    }
}