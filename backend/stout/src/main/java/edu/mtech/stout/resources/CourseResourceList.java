package edu.mtech.stout.resources;

import edu.mtech.stout.api.QueryBySelector;
import edu.mtech.stout.core.Course;
import edu.mtech.stout.core.User;
import edu.mtech.stout.db.CourseDAO;
import edu.mtech.stout.db.ProgramDAO;
import edu.mtech.stout.db.UserDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/courses")
@Produces(MediaType.APPLICATION_JSON)

public class CourseResourceList{
	CourseDAO dao = null;
	UserDAO userDao = null;
	QueryBySelector queryBySelector = null;

	public CourseResourceList(CourseDAO dao, ProgramDAO programDao, UserDAO userDao){
		this.dao = dao;
		queryBySelector = new QueryBySelector(programDao);
		this.userDao = userDao;

	}
	
	@POST
	@RolesAllowed({"Program Coordinator"})
	@UnitOfWork
	public Course createCourse(Course course){
		return dao.create(course);
	}

	@GET
	@RolesAllowed({"Admin", "Program Coordinator", "Faculty"})
	@UnitOfWork
	public List<Course> getCourseList(@Auth User user, @QueryParam("programId") LongParam programId){
    if (queryBySelector.queryByProgramId(user, programId)) {
      return dao.findByProgramId(programId.get());
    } else {
      return dao.findAll();
    }

	}
}
