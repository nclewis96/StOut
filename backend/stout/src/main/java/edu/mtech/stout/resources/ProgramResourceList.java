package edu.mtech.stout.resources;

import edu.mtech.stout.core.Program;
import edu.mtech.stout.db.ProgramDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/program")
@Produces(MediaType.APPLICATION_JSON)
public class ProgramResourceList {

  ProgramDAO dao = null;

  public ProgramResourceList(ProgramDAO dao) {
    this.dao = dao;
  }

  @POST
  @RolesAllowed({"Administrator", "Program_Coordinator"})
  @UnitOfWork
  public Program createProgram(Program program) {
    return dao.create(program);
  }

  @GET
  @RolesAllowed({"Administrator", "Program_Coordinator"})
  @UnitOfWork
  public List<Program> getProgramList(){
    return dao.findAll();
  }

}
