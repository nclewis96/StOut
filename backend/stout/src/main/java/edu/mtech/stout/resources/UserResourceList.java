package edu.mtech.stout.resources;

import edu.mtech.stout.db.UserDAO;
import edu.mtech.stout.core.User;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResourceList {

  UserDAO dao = null;

  public UserResourceList(UserDAO dao) {
    this.dao = dao;
  }

  @POST
  @UnitOfWork
  public User createUser(User user) {
    return dao.create(user);
  }

  @GET
  @UnitOfWork
  public List<User> getUserList(){
    return dao.findAll();
  }

}
