package rest;

import backend.Station;
import main.Railspot;
import main.Settings;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;

@Path("/admin")
public class Admin {

    @GET
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response verify(@QueryParam("user") String user,
                           @QueryParam("password") String password) {
        if (user.equals("admin") && password.equals("1234")) {

            return javax.ws.rs.core.Response.status(Response.Status.ACCEPTED).build();
        } else {
            return javax.ws.rs.core.Response.status(Response.Status.NOT_ACCEPTABLE).build();

        }
    }

    /**
     * Method for adding a new station to the server
     *
     * @param jsonStation name of the new station to create;
     * @return 201 created if success, 500 internal Server.
     */
    @PUT
    @Path("/station")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStation(@QueryParam("value") String jsonStation) {

        try {
            Station station = new Station(jsonStation);
            Railspot.getInstance().createStation(station);
            Settings.Loggers.ADMINISTRATION.log(Level.INFO, () -> jsonStation + " station created!"
                    + "\n" + Railspot.getInstance().getMap().toString());
            return Response.status(Response.Status.CREATED).entity(Railspot.getInstance().getMap().toString()).build();

        } catch (Exception e) {
            Settings.Loggers.ADMINISTRATION.log(Level.SEVERE, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    /**
     * Method for deleting an station.
     *
     * @param name name of the station to be deleted.
     * @return 202 accepted or 500 internal server error.
     */
    @DELETE
    @Path("/station")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteStation(@QueryParam("value") String name) {

        try {
            Railspot.getInstance().deleteStation(new Station(name));
            Settings.Loggers.ADMINISTRATION.log(Level.INFO, () -> name + " deleted!"
                    + "\n" + Railspot.getInstance().getMap().toString());

            return Response.status(Response.Status.ACCEPTED).entity(Railspot.getInstance().getMap().toString()).build();

        } catch (Exception e) {
            Settings.Loggers.ADMINISTRATION.log(Level.SEVERE, e.getMessage());
            return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
        }
    }

    /**
     * Method for connecting two stations
     *
     * @param starting name of the starting point
     * @param ending   name of the ending point
     * @param weight   distance fo the two stations
     * @return Created if success, internal server code if error.
     */
    @PUT
    @Path("/route")
    @Consumes(MediaType.TEXT_PLAIN)
    public Response connectStations(@QueryParam("start") String starting,
                                    @QueryParam("end") String ending,
                                    @QueryParam("km") int weight) {
        if (starting.equals(ending)) {
            return Response.status(Response.Status.CONFLICT).build();
        }
        try {
            Station start = Railspot.getInstance().getMap().getElements().getElement(new Station(starting));
            Station end = Railspot.getInstance().getMap().getElements().getElement(new Station(ending));
            Railspot.getInstance().connect(start, end, weight);
            Settings.Loggers.ADMINISTRATION.log(Level.INFO, () -> starting + "-->" + ending + " route created!"
                    + "\n" + Railspot.getInstance().getMap().toString());
            return Response.status(Response.Status.CREATED).entity(Railspot.getInstance().getMap().toString()).build();

        } catch (Exception e) {
            Settings.Loggers.ADMINISTRATION.log(Level.SEVERE, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    /**
     * Method for deleting the connection between two stations.
     *
     * @param st1 name of the station the connection starts from
     * @param st2 name of the station the connection ends in.
     * @return Response, code 202 if success, 500 internal server error if trouble.
     */
    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/route")
    public Response deleteConnection(@QueryParam("start") String st1, @QueryParam("end") String st2) {

        try {
            Station station = Railspot.getInstance().getMap().getElements().getElement(new Station(st1));
            Station station2 = Railspot.getInstance().getMap().getElements().getElement(new Station(st2));
            Railspot.getInstance().disconnect(station, station2);
            Settings.Loggers.ADMINISTRATION.log(Level.INFO, () -> st1 + "-->" + st2 + " route deleted."
                    + "\n" + Railspot.getInstance().getMap().toString());

            return Response.status(Response.Status.ACCEPTED).entity(Railspot.getInstance().getMap().toString()).build();

        } catch (Exception e) {
            Settings.Loggers.ADMINISTRATION.log(Level.SEVERE, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/percentage")
    public String disc(@QueryParam("num") int amount,
                       @QueryParam("price") int price) {
        double priceKm = price * (double)amount;
        double discount = ((amount <= 46) ? ((amount - 1) * 0.02) : (0.90)) * priceKm;
        return String.valueOf(discount);
    }
}

