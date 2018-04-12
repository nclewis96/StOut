package edu.mtech.stout.resources;

import edu.mtech.stout.api.Status;
import edu.mtech.stout.core.CourseOutcome;
import edu.mtech.stout.db.CourseOutcomeDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.PATCH;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("course-outcomes/{courseId}/{outcomeId}")
@Produces(MediaType.APPLICATION_JSON)
public class CourseOutcomeResource {
  CourseOutcomeDAO dao;

  public CourseOutcomeResource(CourseOutcomeDAO dao){
    this.dao = dao;
  }

  @GET
  @RolesAllowed({"Program Coordinator", "Faculty"})
  @UnitOfWork
  public CourseOutcome getCourseOutcome(@PathParam("courseId")LongParam courseId, @PathParam("outcomeId") LongParam outcomeId) {
    return findSafely(courseId.get(), outcomeId.get());
  }

  private CourseOutcome findSafely(long courseId, long outcomeId){
    return dao.findById(new CourseOutcome(courseId, outcomeId)).orElseThrow(() -> new NotFoundException("No such Course Outcome"));
  }

  @PATCH
  @RolesAllowed({"Program Coordinator", "Faculty"})
  @UnitOfWork
  public CourseOutcome updateCourseOutcome(@PathParam("courseId") LongParam courseId, @PathParam("outcomeId") LongParam outcomeId, CourseOutcome outcome){
    return dao.update(outcome);
  }

  @DELETE
  @RolesAllowed({"Program Coordinator", "Faculty"})
  @UnitOfWork
  public Status deleteCourseOutcome(@PathParam("courseId")LongParam courseId, @PathParam("outcomeId")LongParam outcomeId){
    Status status = new Status();
    status.setId(courseId.get().longValue());
    status.setAction("DELETE");
    status.setResource("CourseOutcome");

    boolean success = dao.delete(findSafely(courseId.get().longValue(), outcomeId.get().longValue()));

    if(success){
      status.setMessage("Successfully deleted Course Outcome");
      status.setStatus(200);
    }else{
      status.setMessage("Error deleting Course Outcome");
      status.setStatus(500);
    }
    return status;
  }
}
