package edu.mtech.stout.resources;

import edu.mtech.stout.api.Status;
import edu.mtech.stout.core.ProgramCutoff;
import edu.mtech.stout.db.ProgramCutoffDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.PATCH;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/program-cutoffs/{programId}/{semesterId}")
@Produces(MediaType.APPLICATION_JSON)
public class ProgramCutoffResource {

  ProgramCutoffDAO dao;

  public ProgramCutoffResource(ProgramCutoffDAO dao){
    this.dao = dao;
  }

  @GET
  @PermitAll
  @UnitOfWork
  public ProgramCutoff getProgramCutoff(@PathParam("programId")LongParam programId, @PathParam("semesterId") LongParam semesterId){
    return findSafely(programId.get(), semesterId.get());
  }

  private ProgramCutoff findSafely(long programId, long semesterId){
    return dao.findById(new ProgramCutoff(programId,semesterId)).orElseThrow(()->new NotFoundException("No such Program Cutoff"));
  }

  @PATCH
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public ProgramCutoff updateProgramCutoff(@PathParam("programId")LongParam programId, @PathParam("semesterId") LongParam semesterId, ProgramCutoff cutoff){
    return dao.update(cutoff);
  }

  @DELETE
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public Status deleteProgramCutoff(@PathParam("programId")LongParam programId, @PathParam("semesterId")LongParam semesterId){
    Status status = new Status();
    status.setId(programId.get().longValue());
    status.setAction("DELETE");
    status.setResource("ProgramCutoff");

    boolean success = dao.delete(findSafely(programId.get().longValue(), semesterId.get().longValue()));

    if(success){
      status.setMessage("Successfully deleted Program Cutoff");
      status.setStatus(200);
    }else{
      status.setMessage("Error deleting Program Cutoff");
      status.setStatus(500);
    }
    return status;
  }

}
