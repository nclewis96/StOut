package edu.mtech.stout.resources;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import edu.mtech.stout.api.AuthenticationObject;
import edu.mtech.stout.client.CASValidator;
import edu.mtech.stout.api.Ticket;
import edu.mtech.stout.core.User;
import edu.mtech.stout.db.UserDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.NonEmptyStringParam;

import java.util.Optional;

@Path("/login/")
public class Login {

  private CASValidator cas;
  private UserDAO userDao;

  public Login(CASValidator cas, UserDAO userDao) {
    this.cas = cas;
    this.userDao = userDao;
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @UnitOfWork
  public AuthenticationObject attemptLogin(@NotNull @Valid Ticket ticket) {
    if (ticket.getTicket() != null) {
      AuthenticationObject auth = new AuthenticationObject();
      // Call casURL/validate
      String username = cas.validateTicket(ticket.getTicket(), ticket.getService());
      Optional<User> user = userDao.findByUsername(username);
      if (username == null) {
        return null;
      }else if(!user.isPresent()){
        throw new ForbiddenException();
      }
      auth.setUsername(username);
      auth.createJwt();
      auth.setUser(user.get());
      return auth;
    } else if (ticket.getJwt() != null) {
      AuthenticationObject auth = new AuthenticationObject();
      auth.setJwt(ticket.getJwt());
      auth.setUsername(auth.retrieveUsername());
      Optional<User> user = userDao.findByUsername(auth.getUsername());
      if(!user.isPresent()){
        throw new ForbiddenException();
      }
      auth.createJwt();
      auth.setUser(user.get());
      return auth;
    } else {
      throw new ForbiddenException();
    }
  }

  @GET
  @UnitOfWork
  @Produces(MediaType.APPLICATION_JSON)
  public AuthenticationObject refreshJWT(@QueryParam("jwt") NonEmptyStringParam jwt) {
    AuthenticationObject token = new AuthenticationObject();
    token.setJwt(jwt.get().get());
    AuthenticationObject auth = new AuthenticationObject();
    String user = token.retrieveUsername();
    Optional<User> userObj = userDao.findByUsername(user);
    if(!userObj.isPresent()){
      throw new ForbiddenException();
    }
    if (user != null) {
      auth.setUsername(user);
      auth.setUser(userObj.get());
      auth.createJwt();
    }
    return auth;
  }
}
