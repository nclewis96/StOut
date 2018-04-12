package edu.mtech.stout.resources;

import edu.mtech.stout.api.QueryBySelector;
import edu.mtech.stout.core.Offering;
import edu.mtech.stout.core.User;
import edu.mtech.stout.db.OfferingDAO;
import edu.mtech.stout.db.ProgramDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/offerings")
@Produces(MediaType.APPLICATION_JSON)
public class OfferingResourceList {


  OfferingDAO dao;
  QueryBySelector queryBySelector;

  public OfferingResourceList(OfferingDAO dao, ProgramDAO programDao) {
    this.dao = dao;
    queryBySelector = new QueryBySelector(programDao);
  }

  @POST
  @RolesAllowed({"Program Coordinator"})
  @UnitOfWork
  public Offering createOffering(Offering offering) {
    return dao.create(offering);
  }

  @GET
  @RolesAllowed({"Admin", "Program Coordinator", "Faculty"})
  @UnitOfWork
  public List<Offering> getOfferingList(@Auth User user, @QueryParam("programId") LongParam programId,
                                        @QueryParam("userId") LongParam userId) {
    if (queryBySelector.queryByProgramId(user, programId)) {
      return dao.findByProgramId(programId.get());
    } else if(userId != null){
      return dao.findByUser(userId.get());
    } else{
      return dao.findAll();
    }
  }

}
