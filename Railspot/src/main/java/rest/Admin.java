package rest;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/admin")
public class Admin {
    @GET
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response verify() {
        return javax.ws.rs.core.Response.status(javax.ws.rs.core.Response.Status.NOT_IMPLEMENTED).entity("Aca va el json con la ruta").type(MediaType.APPLICATION_JSON_TYPE).build();
    }
    @PUT
    @Path("/station")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addStation() {
        return Response.status(Response.Status.NOT_IMPLEMENTED).entity("Aca va el json con la ruta").type(MediaType.APPLICATION_JSON_TYPE).build();
    }
    @POST
    @Path("/station")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifyStation() {
        return Response.status(Response.Status.NOT_IMPLEMENTED).entity("Aca va el json con la ruta").type(MediaType.APPLICATION_JSON_TYPE).build();
    }
    @POST
    @Path("/route")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modifyRoute() {
        return Response.status(Response.Status.NOT_IMPLEMENTED).entity("Aca va el json con la ruta").type(MediaType.APPLICATION_JSON_TYPE).build();
    }

    @PUT
    @Path("/route")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response connectStations(){
        return Response.status(Response.Status.NOT_IMPLEMENTED).entity("Aca va el json con la ruta").type(MediaType.APPLICATION_JSON_TYPE).build();
    }

}

