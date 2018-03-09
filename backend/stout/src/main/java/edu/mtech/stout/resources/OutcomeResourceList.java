package edu.mtech.stout.resources;

import edu.mtech.stout.core.Outcome;
import edu.mtech.stout.db.OutcomeDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/outcomes")
@Produces(MediaType.APPLICATION_JSON)
public class OutcomeResourceList {

  OutcomeDAO dao = null;

  public OutcomeResourceList(OutcomeDAO dao) {
    this.dao = dao;
  }

  @POST
  @RolesAllowed({"Admin", "Program_Coordinator"})
  @UnitOfWork
  public Outcome createOutcome(Outcome program) {
    return dao.create(program);
  }

  @GET
  @RolesAllowed({"Admin", "Program_Coordinator"})
  @UnitOfWork
  public List<Outcome> getOutcomeList(){
    return dao.findAll();
  }

}
