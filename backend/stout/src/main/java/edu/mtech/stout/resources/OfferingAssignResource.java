package edu.mtech.stout.resources;

import edu.mtech.stout.api.Status;
import edu.mtech.stout.core.Offering;
import edu.mtech.stout.core.OfferingAssign;
import edu.mtech.stout.db.OfferingAssignDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.PATCH;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/offering-assigns/{assignId}")
@Produces(MediaType.APPLICATION_JSON)
public class OfferingAssignResource {

  OfferingAssignDAO dao;

  public OfferingAssignResource(OfferingAssignDAO dao){
    this.dao = dao;
  }

  @GET
  @PermitAll
  @UnitOfWork
  public OfferingAssign getOfferingAssign(@PathParam("assignId")LongParam assignId){
    return findSafely(assignId.get());
  }

  private OfferingAssign findSafely(long assignId){
    return dao.findById(assignId).orElseThrow(() -> new NotFoundException("No such Offering Assign."));
  }

  @PATCH
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public OfferingAssign updateOfferingAssign(@PathParam("assignId") LongParam assignId, OfferingAssign assign){
    return  dao.update(assign);
  }

  @DELETE
  @RolesAllowed({"Admin", "Program Coordinator", ""})
  @UnitOfWork
  public Status deleteOfferingAssign(@PathParam("assignId") LongParam assignId) {
    Status status = new Status();
    status.setId(assignId.get());
    status.setAction("DELETE");
    status.setResource("OfferingAssign");

    boolean success = dao.delete(findSafely(assignId.get()));

    if (success) {
      status.setMessage("Successfully deleted Offering Assign");
      status.setStatus(200);
    } else {
      status.setMessage("Error deleting Offering Assign");
      status.setStatus(500);
    }
    return status;
  }

}
