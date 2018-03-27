package edu.mtech.stout.db;

import edu.mtech.stout.core.Course;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class CourseDAO extends StOutDAO<Course> {
	public CourseDAO(SessionFactory factory) {
		super(factory);
	}
	
	public Optional<Course> findByProgram(long programId) {
		List<Course> courseList = list(namedQuery("edu.mtech.stout.core.Course.findByProgram").setParameter(0,programId));
		Optional<Course> course = Optional.empty();
		if(!courseList.isEmpty()){
			course = Optional.of(courseList.get(0));
		}
		return course;
	}
	
	public List<Course> findAll() {
		return list(namedQuery("edu.mtech.stout.core.Course.findAll"));
	}
}