package edu.mtech.stout.resources;

import edu.mtech.stout.db.UserDAO;
import edu.mtech.stout.core.User;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.DenyAll;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
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
  @RolesAllowed({"Admin", "Program_Coordinator"})
  @UnitOfWork
  public User createUser(User user) {
    return dao.create(user);
  }

  @GET
  @RolesAllowed({"Admin", "Program_Coordinator"})
  @UnitOfWork
  public List<User> getUserList(){
    return dao.findAll();
  }

}
