package edu.mtech.stout.resources;

import edu.mtech.stout.api.Status;
import edu.mtech.stout.core.StudentAssign;
import edu.mtech.stout.db.StudentAssignDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.PATCH;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/student-assigns/{studentId}/{assignId}")
@Produces(MediaType.APPLICATION_JSON)
public class StudentAssignResource {
  private StudentAssignDAO dao;

  public StudentAssignResource(StudentAssignDAO dao){this.dao = dao;}

  @GET
  @PermitAll
  @UnitOfWork
  public StudentAssign getStudentAssign(@PathParam("studentId")LongParam studentId, @PathParam("assignId")LongParam assignId){
    return findSafely(studentId.get(), assignId.get());
  }

  private StudentAssign findSafely(long studentId, long assignId){
    return  dao.findById(new StudentAssign(studentId, assignId)).orElseThrow(() -> new NotFoundException("No such Student Assign"));
  }

  @PATCH
  @RolesAllowed({"Program Coordinator", "Faculty"})
  @UnitOfWork
  public StudentAssign updateStudentAssign(@PathParam("studentId")LongParam studentId, @PathParam("assignId")LongParam assignId, StudentAssign assign){
    return dao.update(assign);
  }

  @DELETE
  @RolesAllowed({"Program Coordinator", "Faculty"})
  @UnitOfWork
  public Status deleteStudentAssign(@PathParam("studentId")LongParam studentId, @PathParam("assignId")LongParam assignId){
    Status status = new Status();
    status.setId(assignId.get().longValue());
    status.setAction("DELETE");
    status.setResource("StudentAssign");

    boolean success = dao.delete(findSafely(studentId.get().longValue(), assignId.get().longValue()));

    if(success){
      status.setMessage("Successfully deleted Student Assign");
      status.setStatus(200);
    }else{
      status.setMessage("Error deleting Student Assign");
      status.setStatus(500);
    }
    return status;
  }

}
