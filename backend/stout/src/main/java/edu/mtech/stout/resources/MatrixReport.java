package edu.mtech.stout.resources;

import edu.mtech.stout.api.MatrixReportApi;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

@Path("/matrix-report/{programId}")
public class MatrixReport {
  @GET
  @PermitAll
  @UnitOfWork
  public MatrixReportApi getMatrixReport(@PathParam("programId") LongParam programId){
    return null;
  }
}
