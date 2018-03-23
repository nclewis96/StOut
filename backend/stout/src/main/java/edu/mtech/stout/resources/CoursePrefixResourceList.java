package edu.mtech.stout.resources;

@Path("/coursePrefix")
@Produces(MediaType.APPLICATION_JSON)
public class CoursePrefixResourceList{

    CoursePrefixDAO dao = null;

    public CoursePrefixResourceList(CoursePrefixDAO dao) { this.dao = dao; }

    @POST
    @RolesAllowed({"Admin", "Program Coordinator"})
    @UnitOfWork
    public CoursePrefix createCoursePrefix(CoursePrefix coursePrefix)
    {
        return  dao.create(coursePrefix);
    }

    @GET
    @RolesAllowed({"Admin, Program Coordinator"})
    @UnitOfWork
    public List<CoursePrefix>  getCoursePrefixList()
    {
        return dao.findAll();
    }

}