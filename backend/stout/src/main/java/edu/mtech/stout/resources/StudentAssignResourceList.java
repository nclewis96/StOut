package edu.mtech.stout.resources;

import edu.mtech.stout.core.StudentAssign;
import edu.mtech.stout.db.StudentAssignDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

@Path("/student-assigns")
@Produces(MediaType.APPLICATION_JSON)
public class StudentAssignResourceList {

  StudentAssignDAO dao;

  public StudentAssignResourceList(StudentAssignDAO dao){this.dao = dao;}

  @POST
  @RolesAllowed({"Program Coordinator", "Faculty"})
  @UnitOfWork
  public StudentAssign createStudentAssign(StudentAssign studentAssign) {
    return dao.create(studentAssign);
  }

  @GET
  @RolesAllowed({"Program Coordinator", "Faculty"})
  @UnitOfWork
  public List<StudentAssign> getStudentAssignList(){
    return dao.findAll();
  }
}
