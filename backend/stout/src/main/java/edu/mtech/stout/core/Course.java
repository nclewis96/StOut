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
	private string courseNum;
	@Column(name = "prefix_id", nullable = false)
	private long prefixId;
	@Column(name = "title", nullable = false)
	private string title;
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
	
	public string getCourseNum(){
		return courseNum;
	}
	
	public void setCourseNum(string courseNum){
		this.courseNum = courseNum;
	}
	
	public long getPrefixId(){
		return prefixId;
	}
	
	public void setPrefixId(long prefixId){
		this.prefixId = prefixId;
	}
	
	public string getTitle(){
		return title;
	}
	
	public void setTitle(string title){
		this.title = title;
	}
	
	public long getProgramId(){
		return programId;
	}
	
	public void setProgramId(long programId){
		this.programId = programId;
	}
	
	@Override
	public boolean equals(Object o){
		if(this == o){
			return true;
		}
		if(!(o instanceof Course)){
			return false;
		}
		final Course that = (offering) o;
		return Course.equals(this.course_id, that.course_id);
	}
	
	@Override
	public int hashCode(){
		return Course.hash(course_id);
	}
	
}