package resource;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

/**
 * Created by wanglei on 2014/10/4.
 */
@Path("hello")
public class HelloResource {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String greed(){
        return "hello world";
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{id}")
    public String sayHello(@PathParam("id")int id){
        return "hello jersey "+id;
    }

  /*  @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Path("{username}")
    public String sayHi(@PathParam("username")String username){
        return "hi "+username;
    }*/

    @GET
       @Path("/param")
       @Produces("text/plain")
       public String sayHis(@QueryParam("param")String param){
        return "nice "+param;
    }
}
