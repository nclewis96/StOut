package edu.mtech.stout.db;

import edu.mtech.stout.core.CourseOutcome;
import org.hibernate.SessionFactory;
import  java.util.List;

public class CourseOutcomeDAO extends StOutDAO<CourseOutcome> {
  public CourseOutcomeDAO(SessionFactory factory){ super(factory);}

  public List<CourseOutcome> findAll(){
    return list(namedQuery("edu.mtech.stout.core.CourseOutcome.findAll"));
  }
  public List<CourseOutcome> findByCourseAndMetric(Long courseId, Long metricId){
    return list(namedQuery("edu.mtech.stout.core.CourseOutcome.findByCourseAndMetric").setParameter(0, courseId).setParameter(1, metricId));
  }
}
