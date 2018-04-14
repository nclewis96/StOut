package edu.mtech.stout.resources;

import edu.mtech.stout.api.QueryBySelector;
import edu.mtech.stout.api.Status;
import edu.mtech.stout.core.Assign;
import edu.mtech.stout.core.Permissions;
import edu.mtech.stout.core.User;
import edu.mtech.stout.db.AssignDAO;
import edu.mtech.stout.db.PermissionsDAO;
import edu.mtech.stout.db.ProgramDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.PATCH;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/assigns/{assignId}")
@Produces(MediaType.APPLICATION_JSON)
public class AssignResource {

  AssignDAO dao;
  QueryBySelector qbs;

  public AssignResource(AssignDAO dao, ProgramDAO programDAO) {
    this.dao = dao;
    qbs = new QueryBySelector(programDAO);
  }

  @GET
  @RolesAllowed({"Program Coordinator", "Faculty"})
  @UnitOfWork
  public Assign getAssign(@Auth User user, @PathParam("assignId") LongParam assignId) {
    //if(qbs.queryByProgramId(user, dao.findProgramId().get(0).ge) )
    return findSafely(assignId.get());
  }

  private Assign findSafely(long assignId) {

    return dao.findById(assignId).orElseThrow(() -> new NotFoundException("No such Assign."));
  }

  @PATCH
  @RolesAllowed({"Program Coordinator", "Faculty"})
  @UnitOfWork
  public Assign updateAssign(@PathParam("assignId") LongParam assignId, Assign assign) {
    return dao.update(assign);
  }

  @DELETE
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public Status deleteAssign(@PathParam("assignId") LongParam assignId) {
    Status status = new Status();
    status.setId(assignId.get().intValue());
    status.setAction("DELETE");
    status.setResource("Assign");

    boolean success = dao.delete(findSafely(assignId.get().intValue()));

    if (success) {
      status.setMessage("Successfully deleted assign");
      status.setStatus(200);
    } else {
      status.setMessage("Error deleting assign");
      status.setStatus(500);
    }
    return status;
  }

}