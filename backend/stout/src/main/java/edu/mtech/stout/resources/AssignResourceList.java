package edu.mtech.stout.resources;

import edu.mtech.stout.api.QueryBySelector;
import edu.mtech.stout.core.Assign;
import edu.mtech.stout.core.Course;
import edu.mtech.stout.core.User;
import edu.mtech.stout.db.AssignDAO;
import edu.mtech.stout.db.CourseDAO;
import edu.mtech.stout.db.ProgramDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/assigns")
@Produces(MediaType.APPLICATION_JSON)
public class AssignResourceList {

  private AssignDAO dao;
  private CourseDAO courseDao;
  private QueryBySelector qbs;

  public AssignResourceList(AssignDAO dao, ProgramDAO programDao, CourseDAO courseDao) {
    this.courseDao = courseDao;
    this.dao = dao;
    qbs = new QueryBySelector(programDao);
  }

  @POST
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public Assign createAssign(@Auth User user, Assign assign) {
    //Checks to see if the User has access to the Assign's Program
    List<Course> c = courseDao.findByAssignId(assign.getId());

      if(qbs.queryByProgramId(user, c.get(0).getProgramId()) ){
        return dao.create(assign);

      }else{
        throw new NotAuthorizedException("Cannot create assign not in your program");
      }

  }

  @PUT
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public List<Assign> setAssigns(List<Assign> assigns) {
    ArrayList<Assign> newAssigns = new ArrayList<>();
    for(Assign assign : assigns){
      if(dao.findById(assign.getId()).isPresent()){
        newAssigns.add(dao.update(assign));
      }else {
        newAssigns.add(dao.create(assign));
      }
    }
    return newAssigns;
  }

  @GET
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public List<Assign> getAssignList(@Auth User user, @QueryParam("programId")LongParam programId) {
    if(qbs.queryByProgramId(user, programId)){
      return dao.findProgramId(programId.get());
    }else{
      return dao.findAll();
    }
  }

}
