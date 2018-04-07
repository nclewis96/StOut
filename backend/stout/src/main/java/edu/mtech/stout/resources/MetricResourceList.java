package edu.mtech.stout.resources;

import edu.mtech.stout.api.QueryBySelector;
import edu.mtech.stout.core.Metric;
import edu.mtech.stout.db.MetricDAO;
import io.dropwizard.hibernate.UnitOfWork;
import edu.mtech.stout.db.ProgramDAO;
import edu.mtech.stout.core.User;
import io.dropwizard.auth.Auth;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/metrics")
@Produces(MediaType.APPLICATION_JSON)
public class MetricResourceList {

  MetricDAO dao = null;
  QueryBySelector queryBySelector = null;


  public MetricResourceList(MetricDAO dao, ProgramDAO programDao) {
    this.dao = dao;
    queryBySelector = new QueryBySelector(programDao);
  }

  @POST
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public Metric createMetric(Metric metric) {
    return dao.create(metric);
  }

  @GET
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public List<Metric> getMetricList(@Auth User user, @QueryParam("programId") LongParam programId) {
    if (queryBySelector.queryByProgramId(user, programId)) {
      return dao.findByProgramId(programId.get());
    } else {
      return dao.findAll();
    }
  }

}
