package edu.mtech.stout.resources;

import edu.mtech.stout.api.Status;
import edu.mtech.stout.core.Offering;
import edu.mtech.stout.db.OfferingDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.PATCH;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/offerings/{offeringId}")
@Produces(MediaType.APPLICATION_JSON)
public class OfferingResource {

  OfferingDAO dao;

  public OfferingResource(OfferingDAO dao) {
    this.dao = dao;
  }

  @GET
  @PermitAll
  @UnitOfWork
  public Offering getOffering(@PathParam("offeringId") LongParam offeringId) {
    return findSafely(offeringId.get());
  }

  private Offering findSafely(long offeringId) {
    return dao.findById(offeringId).orElseThrow(() -> new NotFoundException("No such offering."));
  }

  @PATCH
  @RolesAllowed({"Program Coordinator", "Faculty"})
  @UnitOfWork
  public Offering updateOffering(@PathParam("offeringId") LongParam offeringId, Offering offering) {
    return dao.update(offering);
  }

  @DELETE
  @RolesAllowed({"Program Coordinator"})
  @UnitOfWork
  public Status deleteOffering(@PathParam("offeringId") LongParam offeringId) {
    Status status = new Status();
    status.setId(offeringId.get());
    status.setAction("DELETE");
    status.setResource("Offering");

    boolean success = dao.delete(findSafely(offeringId.get()));

    if (success) {
      status.setMessage("Successfully deleted offering");
      status.setStatus(200);
    } else {
      status.setMessage("Error deleting offering");
      status.setStatus(500);
    }
    return status;
  }
}
