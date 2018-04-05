package edu.mtech.stout.resources;

import edu.mtech.stout.core.Course;
import edu.mtech.stout.core.User;
import edu.mtech.stout.db.CourseDAO;
import edu.mtech.stout.db.ProgramDAO;
import edu.mtech.stout.db.UserDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashSet;
import java.util.List;

@Path("/courses")
@Produces(MediaType.APPLICATION_JSON)
<<<<<<< HEAD
public class CourseResourceList{
	CourseDAO dao = null;
	ProgramDAO programDao = null;
	UserDAO userDao = null;
	
	public CourseResourceList(CourseDAO dao, ProgramDAO progDao, UserDAO userDao){
		this.dao = dao;
		this.programDao = progDao;
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
		HashSet<Long> programAccessList = programDao.getProgramIdSetByUser(user.getUserId());

		if(programId != null){
			if(programAccessList.contains(programId.get())){
				return dao.findByProgramId(programId.get());
			}else{
				throw new ForbiddenException();
			}

		}else{
			return dao.findAll();
		}

	}
}
