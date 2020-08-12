package rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/reservations")
public class Reservations {

    @Path("/byPerson")
    @GET
    public Response getByPerson() {
        //todo: return the json with the list of reservations or the number??????????
        return javax.ws.rs.core.Response.
                status(javax.ws.rs.core.Response.Status.NOT_IMPLEMENTED).
                entity("Aca va el json con la ruta").
                type(MediaType.APPLICATION_JSON_TYPE).
                build();
    }

    @Path("/byRoute")
    @GET
    @Consumes
    public Response getByRoute() {
        //todo: return the json with the list of reservations or the number??????????
        return javax.ws.rs.core.Response.
                status(javax.ws.rs.core.Response.Status.NOT_IMPLEMENTED).
                entity("Aca va el json con la ruta").
                type(MediaType.APPLICATION_JSON_TYPE).
                build();
    }
}
