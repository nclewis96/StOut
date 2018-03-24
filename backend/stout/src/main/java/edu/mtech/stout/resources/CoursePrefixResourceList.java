package edu.mtech.stout.resources;

import edu.mtech.stout.db.CoursePrefixDAO;
import edu.mtech.stout.core.CoursePrefix;
import io.dropwizard.hibernate.UnitOfWork;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/coursePrefixes")
@Produces(MediaType.APPLICATION_JSON)
public class CoursePrefixResourceList{

    CoursePrefixDAO dao;

    public CoursePrefixResourceList(CoursePrefixDAO dao) { this.dao = dao; }

    @POST
    @RolesAllowed({"Admin", "Program Coordinator"})
    @UnitOfWork
    public CoursePrefix createCoursePrefix(CoursePrefix coursePrefix)
    {
        return  dao.create(coursePrefix);
    }

    @GET
    @RolesAllowed({"Admin", "Program Coordinator"})
    @UnitOfWork
    public List<CoursePrefix>  getCoursePrefixList()
    {
        return dao.findAll();
    }

}