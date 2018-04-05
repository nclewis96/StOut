package edu.mtech.stout.resources;

import edu.mtech.stout.api.Status;
import edu.mtech.stout.core.CoursePrefix;
import edu.mtech.stout.db.CoursePrefixDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.PATCH;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("coursePrefixes/{coursePrefixId}")
@Produces(MediaType.APPLICATION_JSON)
public class CoursePrefixResource {

  CoursePrefixDAO dao;

  public CoursePrefixResource(CoursePrefixDAO dao) {
    this.dao = dao;
  }

  @GET
  @UnitOfWork
  public CoursePrefix getCoursePrefix(@PathParam("coursePrefixId") LongParam coursePrefixId) {
    return findSafely(coursePrefixId.get());
  }

  private CoursePrefix findSafely(long coursePrefixId) {
    return dao.findById(coursePrefixId).orElseThrow(() -> new NotFoundException("No such Course Prefix"));
  }

  @PATCH
  @UnitOfWork
  public CoursePrefix updateCoursePrefix(@PathParam("coursePrefixId") LongParam coursePrefixId, CoursePrefix coursePref) {
    return dao.update(coursePref);
  }

  @DELETE
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public Status deleteCoursePrefix(@PathParam("coursePrefixId") LongParam coursePrefId) {
    Status status = new Status();
    status.setId(coursePrefId.get().intValue());
    status.setAction("DELETE");
    status.setResource("CoursePrefix");

    boolean success = dao.delete(findSafely(coursePrefId.get().intValue()));

    if (success) {
      status.setMessage("Successfully deleted Course Prefix");
      status.setStatus(200);
    } else {
      status.setMessage("Error deleting course prefix");
      status.setStatus(500);
    }
    return status;
  }
}