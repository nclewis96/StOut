package edu.mtech.stout.resources;

import edu.mtech.stout.db.JobTitleDAO;
import edu.mtech.stout.db.UserDAO;
import edu.mtech.stout.core.User;
import edu.mtech.stout.db.ProgramDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;


import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResourceList {

  UserDAO dao = null;
  JobTitleDAO jobTitleDAO;
  ProgramDAO programDao;

  public UserResourceList(UserDAO dao, JobTitleDAO jobTitleDAO, ProgramDAO programDao) {
    this.dao = dao;
    this.jobTitleDAO = jobTitleDAO;
    this.programDao = programDao;
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
  public List<User> getUserList(@Auth User user, @QueryParam("program") LongParam programId){
    HashSet<Long> programAccessList = programDao.getProgramIdSetByUser(user.getUserId());
    if(programId != null){
      if(programAccessList.contains(programId.get())){
        return dao.findByProgram(programId.get());
      }else{
        throw new ForbiddenException();
      }
    }else{
      return dao.findAll();
    }
  }
}
