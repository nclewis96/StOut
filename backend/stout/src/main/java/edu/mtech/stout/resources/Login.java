package edu.mtech.stout.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.QueryParam;
import edu.mtech.stout.api.AuthenticationObject;
import edu.mtech.stout.client.CASValidator;

@Path("/login/")
@Produces(MediaType.APPLICATION_JSON)

public class Login{
  
  @GET
  public AuthenticationObject attemptLogin(@QueryParam("ticket") String ticket){
    // Call casURL/validate
    //  Get Params: service, ticket
    //  service: set as config
    //  ticket passed into this as part of the get param
    return null;
  }
}
