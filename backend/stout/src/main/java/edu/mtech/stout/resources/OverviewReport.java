package edu.mtech.stout.resources;

import edu.mtech.stout.api.OverviewReportApi;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/overview-report/{programId}")
public class OverviewReport {
  @GET
  @PermitAll
  @UnitOfWork
  public OverviewReportApi getOverviewReport(@PathParam("programId") LongParam programId){
    return null;
  }

}
