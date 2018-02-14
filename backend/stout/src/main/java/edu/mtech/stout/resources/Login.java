package edu.mtech.stout.resources;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;
import edu.mtech.stout.api.AuthenticationObject;
import edu.mtech.stout.client.CASValidator;
import edu.mtech.stout.api.Ticket;

@Path("/login/")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class Login{

  private CASValidator cas;

  public Login(CASValidator cas){
    this.cas = cas;
  }

  @POST
  public AuthenticationObject attemptLogin(@NotNull @Valid Ticket ticket){
    AuthenticationObject auth = new AuthenticationObject();
    // Call casURL/validate
    String username = cas.validateTicket(ticket.getTicket());

    auth.setJwt(username);
    return auth;
  }
}
