package edu.mtech.stout.resources;

import edu.mtech.stout.api.Status;
import edu.mtech.stout.core.Performance;
import edu.mtech.stout.db.PerformanceDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.PATCH;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;


@Path("/performance-indicators/{performanceId}")
@Produces
public class PerformanceResource {

  PerformanceDAO dao;

  public PerformanceResource(PerformanceDAO dao){
    this.dao = dao;
  }

  @GET
  @PermitAll
  @UnitOfWork
  public Performance getPerformance(@PathParam("performanceId")LongParam performanceId){
    
    return findSafely(performanceId.get());
  }

  private Performance findSafely(long performanceId){
    return  dao.findById(performanceId).orElseThrow(() -> new NotFoundException("No such Performance Indicator"));
  }

  @PATCH
  @UnitOfWork
  public Performance updatePeformance(@PathParam("performanceId") LongParam performanceId, Performance performance){
    return dao.update(performance);
  }

  @DELETE
  @RolesAllowed({"Program Coordinator"})
  @UnitOfWork
  public Status deletePerformance(@PathParam("performanceId") LongParam performanceId) {
    Status status = new Status();
    status.setId(performanceId.get().intValue());
    status.setAction("DELETE");
    status.setResource("Performance");

    boolean success = dao.delete(findSafely(performanceId.get().intValue()));

    if (success) {
      status.setMessage("Successfully deleted Performance Indicator");
      status.setStatus(200);
    } else {
      status.setMessage("Error deleting Performance Indicator");
      status.setStatus(500);
    }

    return status;

  }

}
