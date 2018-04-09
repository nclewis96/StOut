package edu.mtech.stout.resources;

import edu.mtech.stout.core.Performance;
import edu.mtech.stout.db.PerformanceDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/performance-indicators")
@Produces(MediaType.APPLICATION_JSON)
public class PerformanceResourceList {

  PerformanceDAO dao;

  public PerformanceResourceList(PerformanceDAO dao){
    this.dao = dao;
  }

  @POST
  @RolesAllowed({"Program Coordinator"})
  @UnitOfWork
  public Performance createPerformance(Performance performance){  return dao.create(performance);}

  @GET
  @PermitAll
  @UnitOfWork
  public List<Performance> getPerformance(){
    return dao.findAll();
  }

}
