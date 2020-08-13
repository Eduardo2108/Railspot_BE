package util.tools;

import com.google.gson.Gson;
import main.Railspot;
import main.Settings;

import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;

import static main.Settings.Constants.GRAPH_PATH;
import static main.Settings.Constants.RESERVATIONS_PATH;
import static main.Settings.TypeJson.GRAPH_TYPE;
import static main.Settings.TypeJson.RESERVATIONS_TYPE;

interface JsonWriter {

    /**
     * Method for saving the information of the graph on the disk, for later use.
     */
    static void updateGraph() {
        String jsonGraph = new Gson().toJson(Railspot.getInstance().getMap(), GRAPH_TYPE);
        try (FileWriter file = new FileWriter(GRAPH_PATH)) {
            file.write(jsonGraph);
            Settings.Loggers.JSON_WRITER.log(Level.INFO, "Graph stored in memory");
        } catch (IOException e) {
            Settings.Loggers.JSON_WRITER.log(Level.SEVERE, "Error writing the graph in the disk.");
        }
    }

    /**
     * Method for saving the information of reservations for later use on the server.
     */
    static void updateReservations() {
        String jsonReservations = new Gson().toJson(Railspot.getInstance().getReservations(), RESERVATIONS_TYPE);
        try (FileWriter file = new FileWriter(RESERVATIONS_PATH)) {
            file.write(jsonReservations);
            Settings.Loggers.JSON_WRITER.log(Level.INFO, "Reservations stored in memory");
        } catch (IOException e) {
            Settings.Loggers.JSON_WRITER.log(Level.SEVERE, "Error writing the reservations in the disk.");
        }
    }
}
