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

  private CASValidator cas;

  public Login(CASValidator cas){
    this.cas = cas;
  }

  @GET
  public AuthenticationObject attemptLogin(@QueryParam("ticket") String ticket){
    AuthenticationObject auth = new AuthenticationObject();
    // Call casURL/validate
    String username = cas.validateTicket(ticket);
    auth.setJwt(username);
    return auth;
  }
}
