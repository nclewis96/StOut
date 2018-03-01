package edu.mtech.stout.resources;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import edu.mtech.stout.api.AuthenticationObject;
import edu.mtech.stout.client.CASValidator;
import edu.mtech.stout.api.Ticket;
import io.dropwizard.jersey.params.NonEmptyStringParam;

@Path("/login/")
public class Login{

  private CASValidator cas;

  public Login(CASValidator cas){
    this.cas = cas;
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public AuthenticationObject attemptLogin(@NotNull @Valid Ticket ticket){
    if(ticket.getTicket() != null) {
      AuthenticationObject auth = new AuthenticationObject();
      // Call casURL/validate
      String username = cas.validateTicket(ticket.getTicket());
      if (username == null) {
        return null;
      }
      auth.setUsername(username);
      auth.createJwt();
      return auth;
    } else if (ticket.getJwt() != null){
      AuthenticationObject auth = new AuthenticationObject();
      auth.setJwt(ticket.getJwt());
      auth.setUsername(auth.retrieveUsername());
      auth.createJwt();
      return auth;
    }
    else{
      throw new ForbiddenException();
    }
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public AuthenticationObject refreshJWT(@QueryParam("jwt") NonEmptyStringParam jwt){
    AuthenticationObject token = new AuthenticationObject();
    token.setJwt(jwt.get().get());
    AuthenticationObject auth = new AuthenticationObject();
    String user = token.getUsername();
    if(user != null) {
      auth.setUsername(user);
      auth.createJwt();
    }
    return auth;
  }
}
