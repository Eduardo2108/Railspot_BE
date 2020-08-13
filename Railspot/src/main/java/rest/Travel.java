package rest;

import backend.Bill;
import backend.Route;
import backend.Station;
import main.Railspot;
import util.tools.Serializer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/routes")
public class Travel {
    /**
     * Method for buying a ticket, and adding it to the stations
     * The route was previous calculated by server and this method is just for confirmation of the purchase.
     *
     * @param routeJson route in json format
     * @param cant      amount of tickets to buy
     * @param date      String format of the date of the purchase
     * @param id        id of the customer
     * @return Accepted code and the json format of the corresponding bill
     * Internal server error if problems
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/buy")
    public Response buy(@QueryParam("route") String routeJson,
                        @QueryParam("cant") int cant,
                        @QueryParam("date") String date,
                        @QueryParam("id") String id) {
        try {
            Route route = Serializer.route(routeJson);
            Bill bill = Railspot.getInstance().purchaseTicket(route, cant, id, date);
            String billJson = Serializer.bills(bill);
            return Response.status(Response.Status.ACCEPTED).entity(billJson).type(MediaType.APPLICATION_JSON_TYPE).build();
        } catch (Exception e) {
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
        //todo: return all stations in the graph
        try {
            String stationsJson = Serializer.stations(Railspot.getInstance().getElements());
            return Response.status(Response.Status.ACCEPTED).
                    entity(stationsJson).
                    type(MediaType.APPLICATION_JSON_TYPE).
                    build();
        } catch (Exception e) {
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
     * @param ending Name of the ending station.
     * @return the route, with the price and the stations to pass.
     */
    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/calc")
    public Response calcRoute(@QueryParam("star") String starting,
                              @QueryParam("end") String ending) {
        try {
            Station startPoint = Railspot.getInstance().getElements().getElement(new Station(starting));
            Station endPoint = Railspot.getInstance().getElements().getElement(new Station(ending));
            Route route = Railspot.getInstance().shortestPath(startPoint, endPoint);
            String routeString = Serializer.route(route);
            return Response.status(Response.Status.ACCEPTED).entity(routeString).type(MediaType.APPLICATION_JSON_TYPE).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).
                    entity(e.getMessage()).
                    type(MediaType.APPLICATION_JSON_TYPE).
                    build();
        }
    }
}
