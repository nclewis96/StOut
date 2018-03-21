package edu.mtech.stout.resources;

import edu.mtech.stout.api.Status;
import edu.mtech.stout.core.Course;
import edu.mtech.stout.db.CourseDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/course/{courseId}")
@Produces(MediaType.APPLICATION_JSON)
public class CourseResource{
	
	CourseDAO dao = null;
	
	public CourseResource(CourseDAO dao){
		this.dao = dao;
	}
	
	@GET
	@UnitOfWork
	public Course getCourse(@PathParam("courseId") LongParam courseId) {
		return findSafely(courseId.get());
	}
	
	private Course findSafely(long courseId){
		return dao.findById(courseId).orElseThrow(() -> new NotFoundException("No such course."));
	}
	
	@POST
	@UnitOfWork
	public Course updateCourse(@PathParam("courseId") LongParam courseId, Course course){
		return dao.update(course);
	}
	
	@DELETE
	@RolesAllowed({"Admin", "Program_Coordinator"})
	@UnitOfWork
	public Status deleteCourse(@PathParam("courseId") LongParam courseId){
		Status status = new Status();
		status.setId(courseId.get().intValue());
		status.setAction("DELETE");
		status.setResources("Course");
		
		boolean success = dao.delete(courseId.get().intValue());
		
		if(success){
			status.setMessage("Successfully deleted course");
			status.setStatus(200);
		}else{
			status.setMessage("Error deleting course");
			status.setStatus(500);
		}
		return status;
	}
}