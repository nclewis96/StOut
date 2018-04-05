package edu.mtech.stout.db;

import edu.mtech.stout.core.Course;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class CourseDAO extends StOutDAO<Course> {
<<<<<<< HEAD
	public CourseDAO(SessionFactory factory) {
		super(factory);
	}
	
	public List<Course> findByProgramId(long programId) {
		List<Course> courseList = list(namedQuery("edu.mtech.stout.core.Course.findByProgramId").setParameter(0,programId));
		return courseList;
	}
	
	public List<Course> findAll() {
		return list(namedQuery("edu.mtech.stout.core.Course.findAll"));
	}
=======
  public CourseDAO(SessionFactory factory) {
    super(factory);
  }

  public Optional<Course> findByProgram(long programId) {
    List<Course> courseList = list(namedQuery("edu.mtech.stout.core.Course.findByProgram").setParameter(0, programId));
    Optional<Course> course = Optional.empty();
    if (!courseList.isEmpty()) {
      course = Optional.of(courseList.get(0));
    }
    return course;
  }

  public List<Course> findAll() {
    return list(namedQuery("edu.mtech.stout.core.Course.findAll"));
  }

>>>>>>> 61907cf4c43e9aff70c7ae043ca1bc36d8afda53
}