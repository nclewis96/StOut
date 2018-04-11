package edu.mtech.stout.resources;

import edu.mtech.stout.core.OfferingAssign;
import edu.mtech.stout.db.OfferingAssignDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


@Path("/offering-assigns")
@Produces(MediaType.APPLICATION_JSON)
public class OfferingAssignResourceList {
  OfferingAssignDAO dao;

  public OfferingAssignResourceList(OfferingAssignDAO dao){
    this.dao = dao;
  }

  @POST
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public OfferingAssign createOfferingAssign(OfferingAssign assign){
    return  dao.create(assign);
  }

  @GET
  @PermitAll
  @UnitOfWork
  public List<OfferingAssign> getOfferingAssignList(){
    return dao.findAll();
  }

}
