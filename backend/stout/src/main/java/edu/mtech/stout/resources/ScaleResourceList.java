package edu.mtech.stout.resources;

import edu.mtech.stout.core.Scale;
import edu.mtech.stout.db.ScaleDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/scales")
@Produces(MediaType.APPLICATION_JSON)
public class ScaleResourceList {

  ScaleDAO dao = null;

  public ScaleResourceList(ScaleDAO dao) {
    this.dao = dao;
  }

  @POST
  @RolesAllowed({"Admin", "Program_Coordinator"})
  @UnitOfWork
  public Scale createScale(Scale scale) {
    return dao.create(scale);
  }

  @GET
  @RolesAllowed({"Admin", "Program_Coordinator"})
  @UnitOfWork
  public List<Scale> getScaleList(){
    return dao.findAll();
  }

}