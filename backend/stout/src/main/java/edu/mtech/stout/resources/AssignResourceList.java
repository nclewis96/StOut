package edu.mtech.stout.resources;

import edu.mtech.stout.core.Assign;
import edu.mtech.stout.db.AssignDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/assigns")
@Produces(MediaType.APPLICATION_JSON)
public class AssignResourceList {

  AssignDAO dao = null;

  public AssignResourceList(AssignDAO dao) {
    this.dao = dao;
  }

  @POST
  @RolesAllowed({"Admin", "Program_Coordinator"})
  @UnitOfWork
  public Assign createAssign(Assign assign) {
    return dao.create(assign);
  }

  @GET
  @RolesAllowed({"Admin", "Program_Coordinator"})
  @UnitOfWork
  public List<Assign> getAssignList(){
    return dao.findAll();
  }

}
