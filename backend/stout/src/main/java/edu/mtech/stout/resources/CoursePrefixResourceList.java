package edu.mtech.stout.resources;

import edu.mtech.stout.api.QueryBySelector;
import edu.mtech.stout.core.Course;
import edu.mtech.stout.core.CoursePrefix;
import edu.mtech.stout.core.User;
import edu.mtech.stout.db.CourseDAO;
import edu.mtech.stout.db.CoursePrefixDAO;
import edu.mtech.stout.db.ProgramDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/course-prefixes")
@Produces(MediaType.APPLICATION_JSON)
public class CoursePrefixResourceList {

  CoursePrefixDAO dao;
  QueryBySelector qbs;
  CourseDAO courseDao;

  public CoursePrefixResourceList(CoursePrefixDAO dao, ProgramDAO pDao, CourseDAO courseDao ) {
    this.courseDao = courseDao;
    this.dao = dao;
    qbs = new QueryBySelector(pDao);
  }

  @POST
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public CoursePrefix createCoursePrefix(@Auth User user, CoursePrefix coursePrefix) {
    //Checks to see if the User has access to the Course Prefix's Program
    List<Course> c = courseDao.findByCoursePrefixId(coursePrefix.getPrefix_id());
    if(c.size() > 0){
      if(qbs.queryByProgramId(user, c.get(0).getProgramId()) ){
        return dao.create(coursePrefix);
      }else{
        throw new NotAuthorizedException("Cannot create Course Prefix not in your program");
      }
    }else{
      throw new NotFoundException("No Course Prefixes are available in your program.");
    }
  }

  @GET
  @RolesAllowed({"Admin", "Program Coordinator"})
  @UnitOfWork
  public List<CoursePrefix> getCoursePrefixList(@Auth User user, @QueryParam("coursePrefixId") LongParam coursePrefixId) {
    //Checks to see if the User has access to the Course Prefix's Program
    List<Course> c = courseDao.findByCoursePrefixId(coursePrefixId.get());
    if(c.size() > 0){
      if(qbs.queryByProgramId(user, c.get(0).getProgramId()) ){
        return dao.findAll();
      }else{
        throw new NotAuthorizedException("Cannot get Course Prefixes not in your program");
      }
    }else{
      throw new NotFoundException("No Course Prefixes are available in your program.");
    }
  }

}