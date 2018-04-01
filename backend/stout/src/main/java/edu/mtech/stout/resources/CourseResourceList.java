package edu.mtech.stout.resources;

import edu.mtech.stout.core.Course;
import edu.mtech.stout.db.CourseDAO;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/courses")
@Produces(MediaType.APPLICATION_JSON)
public class CourseResourceList {
  CourseDAO dao;

  public CourseResourceList(CourseDAO dao) {
    this.dao = dao;
  }

  @POST
  @RolesAllowed({"Program Coordinator"})
  @UnitOfWork
  public Course createCourse(Course course) {
    return dao.create(course);
  }

  @GET
  @RolesAllowed({"Admin", "Program Coordinator", "Faculty"})
  @UnitOfWork
  public List<Course> getCourseList() {
    return dao.findAll();
  }
	/*
	@GET
	@PermitAll
	@UnitOfWork
	public List<Course> getCourseByProgram(@QueryParam("programId") LongParam programId){
		return dao.findByProgram(programId.get());
	}

	@GET
	@RolesAllowed({"Admin", "Program Coordinator"})
	@UnitOfWork
	public List<Course> getCourseList(@Auth Course course, @QueryParam("programId") LongParam programId){
		HashSet<Long> programCourseList = dao.findByProgram(programId.get());
	}*/
}
	/*
	@GET
	@RolesAllowed({"Admin", "Program Coordinator"})
	@UnitOfWork
	public List<User> getUserList(@Auth User user, @QueryParam("program") LongParam programId){
		HashSet<Long> programAccessList = programDao.getProgramIdSetByUser(user.getUserId());
		if(programId != null){
			if(programAccessList.contains(programId.get())){
				return dao.findbyProgram(programId.get());
			}else{
				throw new ForbiddenException();
			}
		}else{
			return dao.findAll();
		}
	}
	*/