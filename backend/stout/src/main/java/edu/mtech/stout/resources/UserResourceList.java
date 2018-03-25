package edu.mtech.stout.resources;

import edu.mtech.stout.db.JobTitleDAO;
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
  JobTitleDAO jobTitleDAO;

  public UserResourceList(UserDAO dao, JobTitleDAO jobTitleDAO) {
    this.dao = dao;
    this.jobTitleDAO = jobTitleDAO;
  }

  @POST
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public User createUser(User user) {
    return dao.create(user);
  }

  @GET
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public List<User> getUserList(){
    return dao.findAll();
  }

}
