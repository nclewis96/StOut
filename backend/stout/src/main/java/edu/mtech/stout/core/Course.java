package edu.mtech.stout.core;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Course")
@NamedNativeQueries(
{
	@NamedNativeQuery(
	name = "edu.mtech.stout.core.Course.findAll",
	query = "SELECT * FROM Course",
	resultClass = Course.class
	),
	@NamedNativeQuery(
		name = "edu.mtech.stout.core.Course.findByProgram",
		query = "SELECT * FROM Course WHERE program_id = ?",
		resultClass = Course.class
	)
})

public class Course {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "course_id")
	private long course_id;
	@Column(name = "course_num", nullable = false)
	private String courseNum;
	@Column(name = "prefix_id", nullable = false)
	private long prefixId;
	@Column(name = "title", nullable = false)
	private String title;
	@Column(name = "program_id", nullable = false)
	private long programId;
	
	public Course() {
	}
	
	public long getId(){
		return course_id;
	}
	
	public void setId(long id){
		this.course_id = id;
	}
	
	public String getCourseNum(){
		return courseNum;
	}
	
	public void setCourseNum(String courseNum){
		this.courseNum = courseNum;
	}
	
	public long getPrefixId(){
		return prefixId;
	}
	
	public void setPrefixId(long prefixId){
		this.prefixId = prefixId;
	}
	
	public String getTitle(){
		return title;
	}
	
	public void setTitle(String title){
		this.title = title;
	}
	
	public long getProgramId(){
		return programId;
	}
	
	public void setProgramId(long programId){
		this.programId = programId;
	}

	public boolean equals(Object object) {
		if (this == object) return true;
		if (!(object instanceof Course)) return false;
		if (!super.equals(object)) return false;
		Course course = (Course) object;
		return course_id == course.course_id &&
				getPrefixId() == course.getPrefixId() &&
				getProgramId() == course.getProgramId() &&
				java.util.Objects.equals(getCourseNum(), course.getCourseNum()) &&
				java.util.Objects.equals(getTitle(), course.getTitle());
	}

	public int hashCode() {

		return java.util.Objects.hash(super.hashCode(), course_id, getCourseNum(), getPrefixId(), getTitle(), getProgramId());
	}
}