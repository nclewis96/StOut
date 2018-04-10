package edu.mtech.stout.core;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Student_Assign")
@NamedNativeQueries(
    {
        @NamedNativeQuery(
            name = "edu.mtech.stout.core.StudentAssign.findAll",
            query = "SELECT * FROM Student_Assign",
            resultClass =  StudentAssign.class
        )
    }
)
public class StudentAssign implements Serializable {
  @Id
  @Column(name = "student_id")
  private long studentId;
  @Id
  @Column(name = "assign_id")
  private long assignId;
  @Column(name = "score")
  private int score;

  public StudentAssign(){
    super();
  }

  public StudentAssign(long studentId, long assignId){
    this.studentId = studentId;
    this.assignId = assignId;
  }

  public long getStudentId() {
    return studentId;
  }

  public void setStudentId(long studentId) {
    this.studentId = studentId;
  }

  public long getAssignId() {
    return assignId;
  }

  public void setAssignId(long assignId) {
    this.assignId = assignId;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
    this.score = score;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof StudentAssign)) return false;
    StudentAssign that = (StudentAssign) o;
    return getStudentId() == that.getStudentId() &&
        getAssignId() == that.getAssignId();
  }

  @Override
  public int hashCode() {
    return Objects.hash(getStudentId(), getAssignId() );
  }
}
