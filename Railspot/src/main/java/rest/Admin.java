package rest;

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

    @PUT
    @Path("/station")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStation() {
        //todo: method to add a station on the server
        return Response.status(Response.Status.NOT_IMPLEMENTED).entity("Aca va el json con la ruta").type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    @POST
    @Path("/station")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifyStation() {
        //todo: method for changes on the stations, like name or ubication.
        return Response.status(Response.Status.NOT_IMPLEMENTED).entity("Aca va el json con la ruta").type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    @DELETE
    @Path("/station")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteStation() {
        //todo: method for changes on the stations, like name or ubication.
        return Response.status(Response.Status.NOT_IMPLEMENTED).entity("Aca va el json con la ruta").type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    @POST
    @Path("/route")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifyRoute() {
        /*
        todo: method to modify the connection of two stations with a route
            add the querry params with the new value of weight.
        **/
        return Response.status(Response.Status.NOT_IMPLEMENTED).entity("Aca va el json con la ruta").type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    @PUT
    @Path("/route")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response connectStations() {
        /*
        todo: method for adding a new route between two statios,
            add the querry params and the verification of tickets on stations.
         */
        return Response.status(Response.Status.NOT_IMPLEMENTED).entity("Aca va el json con la ruta").type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    @DELETE
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/route")
    public Response deleteConnection() {
        /*
        todo: method for deleting connection between stations, should verify there's no tickets bought on the stations.
         */
        return Response.status(Response.Status.NOT_IMPLEMENTED).entity("Aca va el json con la ruta").type(MediaType.APPLICATION_JSON_TYPE).build();

    }

}

