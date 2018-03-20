package edu.mtech.stout.resources;

import edu.mtech.stout.api.Status;
import edu.mtech.stout.core.Outcome;
import edu.mtech.stout.db.OutcomeDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/outcomes/{outcomeId}")
@Produces(MediaType.APPLICATION_JSON)
public class OutcomeResource {

  OutcomeDAO dao = null;

  public OutcomeResource(OutcomeDAO dao) {
    this.dao = dao;
  }

  @GET
  @PermitAll
  @UnitOfWork
  public Outcome getOutcome(@PathParam("outcomeId") LongParam outcomeId) {
    return findSafely(outcomeId.get());
  }

  private Outcome findSafely(long outcomeId) {
    return dao.findById(outcomeId).orElseThrow(() -> new NotFoundException("No such outcome."));
  }

  @POST
  @RolesAllowed({"Program_Coordinator"})
  @UnitOfWork
  public Outcome updateOutcome(@PathParam("outcomeId") LongParam outcomeId, Outcome outcome){
    return dao.update(outcome);
  }

  @DELETE
  @RolesAllowed({"Program_Coordinator"})
  @UnitOfWork
  public Status deleteOutcome(@PathParam("outcomeId") LongParam outcomeId){
    Status status = new Status();
    status.setId(outcomeId.get().intValue());
    status.setAction("DELETE");
    status.setResource("Outcome");

    boolean success = dao.delete(outcomeId.get().intValue());

    if(success){
      status.setMessage("Successfully deleted outcome");
      status.setStatus(200);
    }else{
      status.setMessage("Error deleting outcome");
      status.setStatus(500);
    }

    return status;
  }
}
