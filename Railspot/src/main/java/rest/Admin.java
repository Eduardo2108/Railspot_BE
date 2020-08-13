package rest;

import backend.Station;
import main.Railspot;
import util.tools.Serializer;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/admin")
public class Admin {

    @GET
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response verify(@QueryParam("user") String user,
                           @QueryParam("pass") String password) {

        //todo: return a boolean verification true if math, false if not allowed.
        return javax.ws.rs.core.Response.status(Response.Status.ACCEPTED).entity(user + "°°" + password).type(MediaType.TEXT_PLAIN_TYPE).build();
    }

    /**
     * Method for adding a new station to the server
     *
     * @param jsonStation json of the new station to be added to the server.
     * @return 201 created if success, 500 internal Server.
     */
    @PUT
    @Path("/station")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStation(@QueryParam("value") String jsonStation) {
        try {
            Station station = Serializer.station(jsonStation);
            Railspot.getInstance().createStation(station);
            return Response.status(Response.Status.CREATED).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
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
        //todo: method for changes on the stations, like name or ubication.
        try {
            Railspot.getInstance().deleteStation(new Station(name));
            return Response.status(Response.Status.ACCEPTED).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

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
    @Consumes(MediaType.APPLICATION_JSON)
    public Response connectStations(@QueryParam("start") String starting,
                                    @QueryParam("ending") String ending,
                                    @QueryParam("km") int weight) {
        try {
            Station start = Serializer.station(starting);
            Station end = Serializer.station(ending);
            Railspot.getInstance().connect(start, end, weight);
            return Response.status(Response.Status.CREATED).build();

        } catch (Exception e) {
            return Response.status(Response.Status.NOT_IMPLEMENTED).build();
        }
    }
}

