package edu.mtech.stout.resources;

import edu.mtech.stout.api.Status;
import edu.mtech.stout.core.Assign;
import edu.mtech.stout.db.AssignDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.PATCH;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/assigns/{assignId}")
@Produces(MediaType.APPLICATION_JSON)
public class AssignResource {

  AssignDAO dao;

  public AssignResource(AssignDAO dao) {
    this.dao = dao;
  }

  @GET
  @UnitOfWork
  public Assign getAssign(@PathParam("assignId") LongParam assignId) {
    return findSafely(assignId.get());
  }

  private Assign findSafely(long assignId) {
    return dao.findById(assignId).orElseThrow(() -> new NotFoundException("No such Assign."));
  }

  @PATCH
  @UnitOfWork
  public Assign updateAssign(@PathParam("assignId") LongParam assignId, Assign assign) {
    return dao.update(assign);
  }

  @DELETE
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public Status deleteAssign(@PathParam("assignId") LongParam assignId) {
    Status status = new Status();
    status.setId(assignId.get().intValue());
    status.setAction("DELETE");
    status.setResource("Assign");

    boolean success = dao.delete(findSafely(assignId.get().intValue()));

    if (success) {
      status.setMessage("Successfully deleted assign");
      status.setStatus(200);
    } else {
      status.setMessage("Error deleting assign");
      status.setStatus(500);
    }
    return status;
  }

}