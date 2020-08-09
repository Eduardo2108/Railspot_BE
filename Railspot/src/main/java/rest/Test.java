package rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

@Path("/testing")
public class Test {
    @Path("hello")
    @GET
    public String sayHello(){
        return "Hello world";
    }
}
