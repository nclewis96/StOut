package edu.mtech.stout.resources;

import edu.mtech.stout.api.MatrixReportApi;
import edu.mtech.stout.core.*;
import edu.mtech.stout.db.CourseDAO;
import edu.mtech.stout.db.CourseOutcomeDAO;
import edu.mtech.stout.db.MetricDAO;
import edu.mtech.stout.db.OutcomeDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.PermitAll;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/matrix-report/{metricId}")
public class MatrixReport {

  private OutcomeDAO outcomeDAO;
  private MetricDAO metricDAO;
  private CourseOutcomeDAO courseOutcomeDAO;
  private CourseDAO courseDAO;

  public MatrixReport(OutcomeDAO outcomeDAO, MetricDAO metricDAO, CourseDAO courseDAO, CourseOutcomeDAO courseOutcomeDAO){
    this.outcomeDAO = outcomeDAO;
    this.metricDAO = metricDAO;
    this.courseOutcomeDAO = courseOutcomeDAO;
    this.courseDAO = courseDAO;
  }

  @GET
  @PermitAll
  @UnitOfWork
  public MatrixReportApi getMatrixReport(@Auth User user, @PathParam("metricId") LongParam metricId){
    Optional<Metric> optMetric = metricDAO.findById(metricId.get());
    if(optMetric.isPresent()){
      MatrixReportApi report = new MatrixReportApi();
      List<CourseOutcome> courseOutcomes = new ArrayList<CourseOutcome>();
      Metric metric  =  optMetric.get();
      List<Course> courses = courseDAO.findByProgramId(metric.getProgramId());
      for(Course course : courses){
        List<CourseOutcome> courseOutcomeList = courseOutcomeDAO.findByCourseAndMetric(course.getId(), metric.getId());
        for(CourseOutcome outcome : courseOutcomeList){
          courseOutcomes.add(outcome);
        }
      }
      report.setOutcomes(outcomeDAO.findByMetric(metric.getId()));
      report.setCourseOutcomes(courseOutcomes);
      return report;
    }
    throw new NotFoundException();
  }
}
