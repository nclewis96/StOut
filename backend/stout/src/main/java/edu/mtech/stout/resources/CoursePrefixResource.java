package edu.mtech.stout.resources;

import edu.mtech.stout.api.Status;
import edu.mtech.stout.core.CoursePrefix;
import edu.mtech.stout.db.CoursePrefixDAO;
import io.dropwizard.hibernate.UnitOfWork;
import.io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("coursePrefix/{coursePrefix}")
@Produces(MediaType.APPLICATION_JSON)
public class CoursePrefixResource {

    CoursePrefixDAO dao = null;

    public CoursePrefix(CoursePrefixDao dao){ this.dao = dao;}


}