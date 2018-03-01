package edu.mtech.stout.resources;

import edu.mtech.stout.db.UserDAO;
import edu.mtech.stout.core.User;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.POST;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class DaoCreateTest {

  UserDAO dao = null;

  public DaoCreateTest(UserDAO dao){
    this.dao = dao;
  }

  @POST
  @UnitOfWork
  public User createUser(User user) {
    return dao.create(user);
  }

}
