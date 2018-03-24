package edu.mtech.stout.resources;

import edu.mtech.stout.core.Metric;
import edu.mtech.stout.db.MetricDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/metrics")
@Produces(MediaType.APPLICATION_JSON)
public class MetricResourceList {

  MetricDAO dao;

  public MetricResourceList(MetricDAO dao) {
    this.dao = dao;
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
  public List<Metric> getMetricList(){
    return dao.findAll();
  }

}
