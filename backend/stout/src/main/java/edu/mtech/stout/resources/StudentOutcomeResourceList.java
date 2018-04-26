package edu.mtech.stout.resources;

import edu.mtech.stout.core.StudentOutcome;
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

  public StudentOutcomeResourceList(StudentOutcomeDAO dao){
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
