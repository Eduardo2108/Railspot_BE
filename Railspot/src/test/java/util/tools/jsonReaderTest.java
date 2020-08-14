package util.tools;

import backend.Station;
import main.Railspot;
import org.junit.jupiter.api.Test;
import util.Graph.Graph;

class jsonReaderTest {

    @Test
    void loadStations() {
        Railspot.getInstance().setMap(jsonReader.loadStations());
        System.out.println(Railspot.getInstance().getMap());
        Station C = new Station("Estacion C");
        Station D = new Station("Estacion D");
        Station E = new Station("Estacion E");

        Railspot.getInstance().createStation(C);
        Railspot.getInstance().createStation(D);
        Railspot.getInstance().createStation(E);
        Railspot.getInstance().connect(new Station("Estacion A"),D, 50);

        JsonWriter.updateGraph();
        System.out.println(Railspot.getInstance().getMap().shortestPath(new Station("Estacion A"), new Station("Estacion B")));

    }

    @Test
    void loadReservations() {
    }
}