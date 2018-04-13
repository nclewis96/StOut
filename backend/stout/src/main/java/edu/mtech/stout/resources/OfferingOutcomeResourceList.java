package edu.mtech.stout.resources;

import edu.mtech.stout.core.OfferingOutcome;
import edu.mtech.stout.db.OfferingOutcomeDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.*;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/offering-outcomes")
@Produces(MediaType.APPLICATION_JSON)
public class OfferingOutcomeResourceList {

  OfferingOutcomeDAO dao;

  public OfferingOutcomeResourceList(OfferingOutcomeDAO dao){
    this.dao = dao;
  }

  @POST
  @RolesAllowed({"Program Coordinator"})
  @UnitOfWork
  public OfferingOutcome createOfferingOutcome(OfferingOutcome offeringOutcome) {
    return  dao.create(offeringOutcome);
  }

  @GET
  @PermitAll
  @UnitOfWork
  public List<OfferingOutcome> getOfferingOutcomeList(){
    return  dao.findAll();
  }
}
