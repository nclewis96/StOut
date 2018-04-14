package edu.mtech.stout.resources;

import edu.mtech.stout.api.OutcomeReportApi;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/outcome-report/{programId}")
public class OutcomeReport {
  @GET
  @PermitAll
  @UnitOfWork
  public OutcomeReportApi getOutcomeReport(@PathParam("programId") LongParam programId){
    return null;
  }
}
