package edu.mtech.stout.resources;

import edu.mtech.stout.core.CourseOutcome;
import edu.mtech.stout.db.CourseOutcomeDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.awt.*;
import java.util.List;

@Path("/course-outcomes")
@Produces(MediaType.APPLICATION_JSON)
public class CourseOutcomeResourceList {
  CourseOutcomeDAO dao;

  public CourseOutcomeResourceList(CourseOutcomeDAO dao){
    this.dao = dao;
  }

  @POST
  @RolesAllowed({"Program Coordinator"})
  @UnitOfWork
  public CourseOutcome createCourseOutcome(CourseOutcome outcome){
    return  dao.create(outcome);
  }

  @GET
  @PermitAll
  @UnitOfWork
  public List<CourseOutcome> getCourseOutcome(){
    return dao.findAll();
  }
}
