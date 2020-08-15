package util.tools;

import backend.Station;
import backend.Ticket;
import com.google.gson.Gson;
import main.Settings;
import util.graph.Graph;
import util.LinkedList;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;


public interface jsonReader {
    /**
     * Method for loading the information of the graph from the disk, to load it in memory
     *
     * @return the graph found on the disk memory
     */
    static Graph<Station> loadStations() {
        Graph<Station> stations;
        try (Scanner sc = new Scanner(new File(Settings.Constants.GRAPH_PATH))) {
            String line = sc.nextLine();
            stations = new Gson().fromJson(line, Settings.TypeJson.GRAPH_TYPE);
            return stations;
            //close the file
        } catch (IOException e) {
            Settings.Loggers.JSON_LOADER.log(Level.WARNING, "Error loading the graph.");
        }
        return null;
    }

    /**
     * Method for getting the information of the reservations list, and load it in memory
     *
     * @return loaded info from json file on disk space.
     */
    static LinkedList<Ticket> loadReservations() {
        LinkedList<Ticket> reservations;
        try (Scanner sc = new Scanner(new File(Settings.Constants.RESERVATIONS_PATH))) {
            String line = sc.nextLine();
            reservations = new Gson().fromJson(line, Settings.TypeJson.RESERVATIONS_TYPE);
            return reservations;
            //close the file
        } catch (IOException e) {
            Settings.Loggers.JSON_LOADER.log(Level.WARNING, "Error loading the reservations.");
        }
        return null;
    }
}