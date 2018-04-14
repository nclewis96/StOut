package edu.mtech.stout.db;

import edu.mtech.stout.core.Course;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class CourseDAO extends StOutDAO<Course> {

  public CourseDAO(SessionFactory factory) {
    super(factory);
  }

  public List<Course> findByProgramId(long programId) {
    List<Course> courseList = list(namedQuery("edu.mtech.stout.core.Course.findByProgramId").setParameter(0,programId));
    return courseList;
  }

  public List<Course> findByAssignId(long assignId){
    return list(namedQuery("edu.mtech.stout.core.Course.findByAssignId").setParameter(0,assignId));
  }

  public List<Course> findByCourseOutcome(){
    return list(namedQuery("edu.mtech.stout.core.Course.findByCourseOutcome"));
  }

  public List<Course> findAll() {
    return list(namedQuery("edu.mtech.stout.core.Course.findAll"));
  }


}