package edu.mtech.stout.resources;

import edu.mtech.stout.api.Status;
import edu.mtech.stout.core.Offering;
import edu.mtech.stout.core.OfferingStudent;
import edu.mtech.stout.db.OfferingStudentDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.PATCH;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/offering-students/{studentId}")
@Produces(MediaType.APPLICATION_JSON)
public class OfferingStudentResource {
  OfferingStudentDAO dao;

  public OfferingStudentResource(OfferingStudentDAO dao){this.dao = dao;}

  @GET
  @PermitAll
  @UnitOfWork
  public OfferingStudent getOfferingStudent(@PathParam("studentId") LongParam studentId) {return findSafely(studentId.get());}

  private OfferingStudent findSafely(long studentId){
    return dao.findById(studentId).orElseThrow(() -> new NotFoundException("No such Student."));
  }

  @PATCH
  @RolesAllowed({"Program Coordinator", "Faculty"})
  @UnitOfWork
  public OfferingStudent updateOfferingStudent(@PathParam("studentId")LongParam studentId, OfferingStudent offeringStudent){
    return  dao.update(offeringStudent);
  }

  @DELETE
  @RolesAllowed({"Program Coordinator", "Faculty"})
  @UnitOfWork
  public Status deleteOfferingStudent(@PathParam("studentId") LongParam studentId){
    Status status = new Status();
    status.setId(studentId.get().intValue());
    status.setAction("DELETE");
    status.setResource("OfferingStudent");

    boolean success = dao.delete(findSafely(studentId.get().intValue()));

    if(success){
      status.setMessage("Successfully deleted student");
      status.setStatus(200);
    }else{
      status.setMessage("Error deleting student");
      status.setStatus(500);
    }
    return status;
  }


}
