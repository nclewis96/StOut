package edu.mtech.stout.resources;

import edu.mtech.stout.core.Assign;
import edu.mtech.stout.db.AssignDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/assigns")
@Produces(MediaType.APPLICATION_JSON)
public class AssignResourceList {

  AssignDAO dao;

  public AssignResourceList(AssignDAO dao) {
    this.dao = dao;
  }

  @POST
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public Assign createAssign(Assign assign) {
    return dao.create(assign);
  }

  @PUT
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public List<Assign> setAssigns(List<Assign> assigns) {
    ArrayList<Assign> newAssigns = new ArrayList<>();
    for(Assign assign : assigns){
      if(dao.findById(assign.getId()).isPresent()){
        newAssigns.add(dao.update(assign));
      }else {
        newAssigns.add(dao.create(assign));
      }
    }
    return newAssigns;
  }

  @GET
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public List<Assign> getAssignList() {
    return dao.findAll();
  }

}
