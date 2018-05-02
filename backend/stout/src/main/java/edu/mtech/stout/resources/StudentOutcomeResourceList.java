package edu.mtech.stout.resources;

import edu.mtech.stout.api.QueryBySelector;
import edu.mtech.stout.core.StudentOutcome;
import edu.mtech.stout.db.ProgramDAO;
import edu.mtech.stout.db.StudentOutcomeDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/student-outcomes")
@Produces(MediaType.APPLICATION_JSON)
public class StudentOutcomeResourceList {

  private StudentOutcomeDAO dao;
  private ProgramDAO programDao;
  private QueryBySelector queryBySelector = new QueryBySelector();

  public StudentOutcomeResourceList(StudentOutcomeDAO dao, ProgramDAO programDao){
    this.programDao = programDao;
    this.dao = dao;
  }

  @POST
  @RolesAllowed({"Program Coordinator", "Faculty"})
  @UnitOfWork
  public StudentOutcome createStudentOutcome(StudentOutcome studentOutcome) {
    return dao.create(studentOutcome);
  }

  @GET
  @RolesAllowed({"Program Coordinator", "Faculty"})
  @UnitOfWork
  public List<StudentOutcome> getStudentOutcomeList(){
    return dao.findAll();
  }

}
