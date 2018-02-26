package edu.mtech.stout.resources;

import edu.mtech.stout.db.UserDAO;
import edu.mtech.stout.core.User;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/user/{userId}")
@Produces(MediaType.APPLICATION_JSON)
public class DaoTest {

  UserDAO dao = null;

  public DaoTest(UserDAO dao){
    this.dao = dao;
  }

  @GET
  @UnitOfWork
  public User getPerson(@PathParam("userId") LongParam userId) {
    return findSafely(userId.get());
  }

  private User findSafely(long personId) {
    return dao.findById(personId).orElseThrow(() -> new NotFoundException("No such user."));
  }
}
