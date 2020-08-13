package rest;

import backend.Ticket;
import com.google.gson.Gson;
import main.Railspot;
import util.LinkedList;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/reservations")
public class Reservations {
    /**
     * Method for getting the tickets that a person bought
     *
     * @param id of the customer
     * @return Linked list in json format with all the tickets bought on the system.
     */
    @Path("/byPerson")
    @GET
    public Response getByPerson(@QueryParam("id") String id) {
        //todo: test
        try {
            LinkedList<Ticket> reservations = Railspot.getInstance().getReservationsByID(id);
            String json = new Gson().toJson(reservations);
            return javax.ws.rs.core.Response.
                    status(Response.Status.ACCEPTED).
                    entity(json).
                    type(MediaType.APPLICATION_JSON_TYPE).
                    build();
        } catch (Exception e) {
            return javax.ws.rs.core.Response.
                    status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();

        }
    }

    /**
     * Method for getting a list of tickets that pass on a specific station
     *
     * @param name of the station to consult
     * @return the linked list with tickets, on json format.
     */
    @Path("/byStation")
    @GET
    @Consumes
    public Response getByRoute(@QueryParam("name") String name) {
        //todo: test

        try {
            LinkedList<Ticket> reservations = Railspot.getInstance().getReservationsByStation(name);
            String json = new Gson().toJson(reservations);
            return javax.ws.rs.core.Response.
                    status(Response.Status.ACCEPTED).
                    entity(json).
                    type(MediaType.APPLICATION_JSON_TYPE).
                    build();
        } catch (Exception e) {
            return javax.ws.rs.core.Response.
                    status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();

        }
    }

    /**
     * Method for getting all the reservations on a given date
     *
     * @param date of the day to consult.
     * @return json format of the list, containing all the tickets.
     */
    @Path("/byDate")
    @GET
    public Response getByDate(@QueryParam("date") String date) {
        //todo: test

        try {
            LinkedList<Ticket> reservations = Railspot.getInstance().getReservationsByDate(date);
            String json = new Gson().toJson(reservations);
            return javax.ws.rs.core.Response.
                    status(Response.Status.ACCEPTED).
                    entity(json).
                    type(MediaType.APPLICATION_JSON_TYPE).
                    build();
        } catch (Exception e) {
            return javax.ws.rs.core.Response.
                    status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();

        }
    }

}
