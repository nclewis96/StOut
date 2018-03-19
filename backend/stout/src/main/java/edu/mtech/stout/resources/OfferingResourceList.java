package edu.mtech.stout.resources;

import edu.mtech.stout.core.Offering;
import edu.mtech.stout.db.OfferingDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/offerings")
@Produces(MediaType.APPLICATION_JSON)
public class OfferingResourceList {

  OfferingDAO dao = null;

  public OfferingResourceList(OfferingDAO dao) {
    this.dao = dao;
  }

  @POST
  @RolesAllowed({"Admin", "Program_Coordinator"})
  @UnitOfWork
  public Offering createOffering(Offering offering) {
    return dao.create(offering);
  }

  @GET
  @RolesAllowed({"Admin", "Program_Coordinator"})
  @UnitOfWork
  public List<Offering> getOfferingList(){
    return dao.findAll();
  }

}
