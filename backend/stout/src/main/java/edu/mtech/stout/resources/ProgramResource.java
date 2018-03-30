package edu.mtech.stout.resources;

import edu.mtech.stout.api.Status;
import edu.mtech.stout.core.Program;
import edu.mtech.stout.db.ProgramDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/program/{programId}")
@Produces(MediaType.APPLICATION_JSON)
public class ProgramResource {

  ProgramDAO dao;

  public ProgramResource(ProgramDAO dao) {
    this.dao = dao;
  }

  @GET
  @PermitAll
  @UnitOfWork
  public Program getProgram(@PathParam("programId") LongParam programId) {
    return findSafely(programId.get());
  }

  private Program findSafely(long programId) {
    return dao.findById(programId).orElseThrow(() -> new NotFoundException("No such program."));
  }

  @POST
  @RolesAllowed({"Program Coordinator"})
  @UnitOfWork
  public Program updateProgram(@PathParam("programId") LongParam programId, Program program) {
    return dao.update(program);
  }

  @DELETE
  @RolesAllowed({"Admin"})
  @UnitOfWork
  public Status deleteProgram(@PathParam("programId") LongParam programId) {
    Status status = new Status();
    status.setId(programId.get().intValue());
    status.setAction("DELETE");
    status.setResource("Program");

    boolean success = dao.delete(programId.get().intValue());

    if (success) {
      status.setMessage("Successfully deleted program");
      status.setStatus(200);
    } else {
      status.setMessage("Error deleting program");
      status.setStatus(500);
    }

    return status;
  }
}
