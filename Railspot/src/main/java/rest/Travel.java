package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/routes")
public class Travel {


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/buy")
    public Response buy(@QueryParam("route") String routeJson) {
        //todo: deserialize the json file and pass to Railspot class to buy the ticket
        return Response.status(Response.Status.NOT_IMPLEMENTED).entity("Aca va el json con la ruta").type(MediaType.APPLICATION_JSON_TYPE).build();

    }

    @GET
    @Path("/all")
    public Response getAllstations() {
        //todo: return all stations in the graph
        return Response.status(Response.Status.NOT_IMPLEMENTED).entity("Aca va el json con la ruta").type(MediaType.APPLICATION_JSON_TYPE).build();

    }

    @GET
    @Consumes(MediaType.TEXT_PLAIN)
    @Path("/calc")
    public Response calcRoute() {
        //todo: add the querry params of the string name of the stations and then find and return the calculation.
        return Response.status(Response.Status.NOT_IMPLEMENTED).entity("Aca va el json con la ruta").type(MediaType.APPLICATION_JSON_TYPE).build();
    }
}
