package edu.mtech.stout.resources;

import edu.mtech.stout.core.Outcome;
import edu.mtech.stout.db.OutcomeDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/outcomes")
@Produces(MediaType.APPLICATION_JSON)
public class OutcomeResourceList {

  OutcomeDAO dao;

  public OutcomeResourceList(OutcomeDAO dao) {
    this.dao = dao;
  }

  @POST
  @RolesAllowed({"Program Coordinator"})
  @UnitOfWork
  public Outcome createOutcome(Outcome program) {
    return dao.create(program);
  }

  @GET
  @PermitAll
  @UnitOfWork
  public List<Outcome> getOutcomeList(@QueryParam("metricId")LongParam metricId) {
    if(metricId != null){
      return dao.findByMetric(metricId.get());
    }else{
      return dao.findAll();
    }
  }

}
