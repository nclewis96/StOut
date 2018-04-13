package edu.mtech.stout.resources;

import edu.mtech.stout.api.Status;
import edu.mtech.stout.core.StudentOutcome;
import edu.mtech.stout.db.StudentOutcomeDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.PATCH;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;

@Path("/student-outcomes/{studentId}/{outcomeId}")
@Produces(MediaType.APPLICATION_JSON)
public class StudentOutcomeResource {

  StudentOutcomeDAO dao;

  public StudentOutcomeResource(StudentOutcomeDAO dao){
    this.dao = dao;
  }

  @GET
  @PermitAll
  @UnitOfWork
  public StudentOutcome getStudentOutcome(@PathParam("studentId")LongParam studentId, @PathParam("outcomeId") LongParam outcomeId){
    return findSafely(studentId.get(),outcomeId.get());
  }

  private StudentOutcome findSafely(long studentId, long outcomeId){
    return dao.findById(new StudentOutcome(studentId, outcomeId)).orElseThrow(()-> new NotFoundException("No such Student Outcome"));
  }

  @PATCH
  @RolesAllowed({"Program Coordinator", "Faculty"})
  @UnitOfWork
  public StudentOutcome updateStudentOutcome(@PathParam("studentId")LongParam studentId, @PathParam("outcomeId")LongParam outcomeId, StudentOutcome outcome){
    return dao.update(outcome);
  }

  @DELETE
  @RolesAllowed({"Program Coordinator", "Faculty"})
  @UnitOfWork
  public Status deleteStudentOutcome(@PathParam("studentId")LongParam studentId, @PathParam("outcomeId")LongParam outcomeId) {
    Status status = new Status();
    status.setId(outcomeId.get().longValue());
    status.setAction("DELETE");
    status.setResource("StudentOutcome");

    boolean success = dao.delete(findSafely(studentId.get().longValue(), outcomeId.get().longValue()));

    if (success) {
      status.setMessage("Successfully deleted Student Outcome");
      status.setStatus(200);
    } else {
      status.setMessage("Error deleting Student Outcome");
      status.setStatus(500);
    }
    return status;
  }
}
