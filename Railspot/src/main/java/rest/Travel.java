package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/routes")
public class Travel {

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/calc")
    public Response calcRoute() {
        return Response.status(Response.Status.NOT_IMPLEMENTED).entity("Aca va el json con la ruta").type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/buy")
    public Response buyTicket(@QueryParam("route") String jsonRoute) {
        return Response.status(Response.Status.NOT_IMPLEMENTED).entity("Aca va el json con el resultado de la wea").type(MediaType.APPLICATION_JSON_TYPE).build();

    }
    /*

     */
}
