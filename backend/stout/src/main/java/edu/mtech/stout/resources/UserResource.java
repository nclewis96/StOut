package edu.mtech.stout.resources;

import edu.mtech.stout.api.Status;
import edu.mtech.stout.db.JobTitleDAO;
import edu.mtech.stout.db.UserDAO;
import edu.mtech.stout.core.User;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/user/{userId}")
@Produces(MediaType.APPLICATION_JSON)
public class UserResource {

  UserDAO dao = null;
  JobTitleDAO jobTitleDAO;

  public UserResource(UserDAO dao, JobTitleDAO jobTitleDAO) {
    this.dao = dao;
    this.jobTitleDAO = jobTitleDAO;
  }

  @GET
  @PermitAll
  @UnitOfWork
  public User getUser(@PathParam("userId") LongParam userId) {
    return findSafely(userId.get());
  }

  private User findSafely(long userId) {
    return dao.findById(userId).orElseThrow(() -> new NotFoundException("No such user."));
  }

  @POST
  @UnitOfWork
  public User updateUser(@PathParam("userId") LongParam userId, User user){
    return dao.update(user);
  }

  @DELETE
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public Status deleteUser(@PathParam("userId") LongParam userId){
    Status status = new Status();
    status.setId(userId.get().intValue());
    status.setAction("DELETE");
    status.setResource("User");

    boolean success = dao.delete(userId.get().intValue());

    if(success){
      status.setMessage("Successfully deleted user");
      status.setStatus(200);
    }else{
      status.setMessage("Error deleting user");
      status.setStatus(500);
    }

    return status;
  }
}
