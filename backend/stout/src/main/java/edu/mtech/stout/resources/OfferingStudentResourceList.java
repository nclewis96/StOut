package edu.mtech.stout.resources;

import edu.mtech.stout.core.OfferingStudent;
import edu.mtech.stout.db.OfferingStudentDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
 import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/offering-students")
@Produces(MediaType.APPLICATION_JSON)
public class OfferingStudentResourceList {

  OfferingStudentDAO dao;

  public OfferingStudentResourceList(OfferingStudentDAO dao){this.dao = dao;}

  @POST
  @RolesAllowed({"Program Coordinator", "Faculty"})
  @UnitOfWork
  public OfferingStudent createOfferingStudent(OfferingStudent offeringStudent){ return dao.create(offeringStudent);}

  @GET
  @PermitAll
  @UnitOfWork
  public List<OfferingStudent> getOfferingStudentList(){
    return dao.findAll();
  }

}
