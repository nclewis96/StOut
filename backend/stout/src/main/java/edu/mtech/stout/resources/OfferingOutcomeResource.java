package edu.mtech.stout.resources;

import edu.mtech.stout.api.Status;
import edu.mtech.stout.core.OfferingOutcome;
import edu.mtech.stout.db.OfferingOutcomeDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.PATCH;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("offering-outcomes/{offeringId}/{outcomeId}")
@Produces(MediaType.APPLICATION_JSON)
public class OfferingOutcomeResource {

  OfferingOutcomeDAO dao;

  public OfferingOutcomeResource(OfferingOutcomeDAO dao){
    this.dao = dao;
  }

  @GET
  @PermitAll
  @UnitOfWork
  public OfferingOutcome getOfferingOutcome(@PathParam("offeringId")LongParam offeringId, @PathParam("outcomeId") LongParam outcomeId){
    return findSafely(offeringId.get(), outcomeId.get());
  }

  private OfferingOutcome findSafely(long offeringId, long outcomeId){
    return dao.findById(new OfferingOutcome(offeringId, outcomeId)).orElseThrow(() -> new NotFoundException("No such Offering Outcome"));
  }

  @PATCH
  @RolesAllowed({"Program Coordinator", "Faculty"})
  @UnitOfWork
  public OfferingOutcome updateOfferingOutcome(@PathParam("offeringId") LongParam offeringId, @PathParam("outcomeId") LongParam outcomeId, OfferingOutcome offeringOutcome){
    return dao.update(offeringOutcome);
  }

  @DELETE
  @RolesAllowed({"Program Coordinator", "Faculty"})
  @UnitOfWork
  public Status deleteStudentAssign(@PathParam("offeringId")LongParam offeringId, @PathParam("outcomeId")LongParam outcomeId){
    Status status = new Status();
    status.setId(offeringId.get().longValue());
    status.setAction("DELETE");
    status.setResource("StudentAssign");

    boolean success = dao.delete(findSafely(offeringId.get().longValue(), outcomeId.get().longValue()));

    if(success){
      status.setMessage("Successfully deleted Offering Outcome");
      status.setStatus(200);
    }else{
      status.setMessage("Error deleting Student Offering Outcome");
      status.setStatus(500);
    }
    return status;
  }
}
