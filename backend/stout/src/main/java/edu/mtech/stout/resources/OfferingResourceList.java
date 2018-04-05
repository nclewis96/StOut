package edu.mtech.stout.resources;

import edu.mtech.stout.core.Offering;
import edu.mtech.stout.core.User;
import edu.mtech.stout.db.OfferingDAO;
import edu.mtech.stout.db.ProgramDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashSet;
import java.util.List;

@Path("/offerings")
@Produces(MediaType.APPLICATION_JSON)
public class OfferingResourceList {


  OfferingDAO dao = null;
  ProgramDAO programDao = null;

  public OfferingResourceList(OfferingDAO dao, ProgramDAO programDao) {
    this.dao = dao;
    this.programDao = programDao;
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
  public List<Offering> getOfferingList(@Auth User user, @QueryParam("programId") LongParam programId){
    HashSet<Long> programAccessList = programDao.getProgramIdSetByUser(user.getUserId());

    if(programId != null){
      if(programAccessList.contains(programId.get())){
        return  dao.findByProgramId(programId.get());
      }else {
        throw new ForbiddenException();
      }
    }else{
      return dao.findAll();
    }


}
