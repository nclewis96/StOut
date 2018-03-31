package edu.mtech.stout.resources;

import edu.mtech.stout.api.Status;
import edu.mtech.stout.core.Course;
import edu.mtech.stout.db.CourseDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.PATCH;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/courses/{courseId}")
@Produces(MediaType.APPLICATION_JSON)
public class CourseResource {

  CourseDAO dao;

  public CourseResource(CourseDAO dao) {
    this.dao = dao;
  }

  @GET
  @RolesAllowed({"Admin", "Program Coordinator", "Faculty"})
  @UnitOfWork
  public Course getCourse(@PathParam("courseId") LongParam courseId) {
    return findSafely(courseId.get());
  }

  private Course findSafely(long courseId) {
    return dao.findById(courseId).orElseThrow(() -> new NotFoundException("No such course."));
  }

  @PATCH
  @RolesAllowed({"Admin", "Program Coordinator", "Faculty"})
  @UnitOfWork
  public Course updateCourse(@PathParam("courseId") LongParam courseId, Course course) {
    return dao.update(course);
  }

  @DELETE
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public Status deleteCourse(@PathParam("courseId") LongParam courseId) {
    Status status = new Status();
    status.setId(courseId.get().intValue());
    status.setAction("DELETE");
    status.setResource("Course");

    boolean success = dao.delete(findSafely(courseId.get().intValue()));

    if (success) {
      status.setMessage("Successfully deleted course");
      status.setStatus(200);
    } else {
      status.setMessage("Error deleting course");
      status.setStatus(500);
    }
    return status;
  }
}