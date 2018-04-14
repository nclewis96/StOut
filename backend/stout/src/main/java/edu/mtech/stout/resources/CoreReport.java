package edu.mtech.stout.resources;

import edu.mtech.stout.api.CoreReportApi;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/core-report/{programId}")
public class CoreReport {
  @GET
  @PermitAll
  @UnitOfWork
  public CoreReportApi getCoreReport(@PathParam("programId") LongParam programId){
    return null;
  }
}
