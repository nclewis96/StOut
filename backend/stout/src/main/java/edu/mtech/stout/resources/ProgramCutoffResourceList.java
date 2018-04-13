package edu.mtech.stout.resources;

import edu.mtech.stout.core.ProgramCutoff;
import edu.mtech.stout.db.ProgramCutoffDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/program-cutoffs")
@Produces(MediaType.APPLICATION_JSON)
public class ProgramCutoffResourceList {

  ProgramCutoffDAO dao;

  public ProgramCutoffResourceList(ProgramCutoffDAO dao){
    this.dao = dao;
  }

  @POST
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public ProgramCutoff createProgramCutoff(ProgramCutoff cutoff){
    return dao.create(cutoff);
  }

  @GET
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public List<ProgramCutoff> getProgramCutoffList(){
    return dao.findAll();
  }

}
