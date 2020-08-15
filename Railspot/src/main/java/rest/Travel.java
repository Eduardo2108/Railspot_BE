package rest;

import backend.Bill;
import backend.Route;
import backend.Station;
import main.Railspot;
import main.Settings;
import util.tools.Serializer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;

@Path("/routes")
public class Travel {
    /**
     * Method for buying a ticket, and adding it to the stations
     * The route was previous calculated by server and this method is just for confirmation of the purchase.
     *
     * @param start start station
     * @param end   ending route.
     * @param cant  amount of tickets to buy
     * @param date  String format of the date of the purchase
     * @param id    id of the customer
     * @return Accepted code and the json format of the corresponding bill
     * Internal server error if problems
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/buy")
    public Response buy(@QueryParam("start") String start,
                        @QueryParam("end") String end,
                        @QueryParam("cant") int cant,
                        @QueryParam("date") String date,
                        @QueryParam("id") String id) {
        try {
            Route route = Railspot.getInstance().shortestPath(new Station(start), new Station(end));
            Bill bill = Railspot.getInstance().purchaseTicket(route, cant, id, date);
            String billJson = Serializer.bills(bill);
            Settings.Loggers.TRAVELS.log(Level.INFO, () -> cant + " tickets bought" +
                    " " + " for " + route + " by " + id);
            return Response.status(Response.Status.ACCEPTED).entity(billJson).type(MediaType.APPLICATION_JSON_TYPE).build();
        } catch (Exception e) {
            Settings.Loggers.TRAVELS.log(Level.SEVERE, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).type(MediaType.TEXT_PLAIN).build();
        }
    }

    /**
     * Method for getting all the list of stations
     *
     * @return json format of the list of stations.
     */
    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllStations() {
        try {
            String stationsJson = Serializer.stations(Railspot.getInstance().getElements());
            Settings.Loggers.TRAVELS.log(Level.INFO, "stations asked");
            return Response.status(Response.Status.ACCEPTED).
                    entity(stationsJson).
                    type(MediaType.APPLICATION_JSON_TYPE).
                    build();
        } catch (Exception e) {
            Settings.Loggers.TRAVELS.log(Level.SEVERE, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity(e.getMessage()).
                    type(MediaType.APPLICATION_JSON_TYPE).
                    build();
        }
    }

    /**
     * Method for getting the calculation of a route, from a to b.
     *
     * @param starting Name of the start station.
     * @param ending   Name of the ending station.
     * @return the route, with the price and the stations to pass.
     */
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("/calc")
    public Response calcRoute(@QueryParam("start") String starting,
                              @QueryParam("end") String ending) {
        try {
            Station startPoint = Railspot.getInstance().getElements().getElement(new Station(starting));
            Station endPoint = Railspot.getInstance().getElements().getElement(new Station(ending));
            Route route = Railspot.getInstance().shortestPath(startPoint, endPoint);
            System.out.println(route);
            String routeString = Serializer.route(route);
            Settings.Loggers.TRAVELS.log(Level.INFO, "Route calc: " + routeString);
            return Response.status(Response.Status.ACCEPTED).entity(route.getPrice()).type(MediaType.APPLICATION_JSON_TYPE).build();
        } catch (Exception e) {
            Settings.Loggers.TRAVELS.log(Level.SEVERE, e.getMessage());
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity(e.getMessage()).
                    build();
        }
    }
}
