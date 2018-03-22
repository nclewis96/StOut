package edu.mtech.stout.resources;

import edu.mtech.stout.core.Course;
import edu.mtech.stout.db.CourseDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/course")
@Produces(MediaType.APPLICATION_JSON)
public class CourseResourceList{
	CourseDAO dao = null;
	
	public CourseResourceList(CourseDAO dao){
		this.dao = dao;
	}
	
	@POST
	@RolesAllowed({"Program_Coordinator"})
	@UnitOfWork
	public Course createCourse(Course course){
		return dao.create(course);
	}
	
	@GET
	@PermitAll
	@UnitOfWork
	public List<Course> getCourseList(){
		return dao.findAll();
	}
	
	@GET
    @PermitAll
    @UnitOfWork
    public List<Course> getCourseByProgram(@QueryParam("programId") LongParam programId){
        return dao.findByProgram(programId.get());
    }
}