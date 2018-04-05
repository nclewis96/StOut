package edu.mtech.stout.resources;

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
import java.util.HashSet;
import java.util.List;

@Path("/metrics")
@Produces(MediaType.APPLICATION_JSON)
public class MetricResourceList {

<<<<<<< HEAD
  MetricDAO dao = null;
  ProgramDAO programDao = null;
=======
  MetricDAO dao;
>>>>>>> 61907cf4c43e9aff70c7ae043ca1bc36d8afda53

  public MetricResourceList(MetricDAO dao, ProgramDAO programDao) {
    this.dao = dao;
    this.programDao = programDao;
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
  public List<Metric> getMetricList(@Auth User user, @QueryParam("programId") LongParam programId){
    HashSet<Long> programAccessList = programDao.getProgramIdSetByUser(user.getUserId());

    if(programId != null){
      if(programAccessList.contains(programId.get())){
        return dao.findByProgramId(programId.get());
      }else{
        throw new ForbiddenException();
      }
    }else {
      return dao.findAll();
    }



}
