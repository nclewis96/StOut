package edu.mtech.stout.core;


import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Student_Outcome")
@NamedNativeQueries(
    {
        @NamedNativeQuery(
            name = "edu.mtech.stout.core.StudentOutcome.findAll",
            query = "SELECT * FROM Student_Outcome",
            resultClass = StudentOutcome.class
        )
    }
)
public class StudentOutcome implements Serializable{

@Id
@Column(name = "outcome_id")
private long outcomeId;
@Id
@Column(name = "student_id")
private long studentId;
@Column(name = "score")
private double score;

  public StudentOutcome(){
    super();
  }

  public StudentOutcome(long studentId, long outcomeId){
    this.studentId = studentId;
    this.outcomeId = outcomeId;
  }
  public long getOutcomeId() {
    return outcomeId;
  }

  public void setOutcomeId(long outcomeId) {
    this.outcomeId = outcomeId;
  }

  public long getStudentId() {
    return studentId;
  }

  public void setStudentId(long studentId) {
    this.studentId = studentId;
  }

  public double getScore() {
    return score;
  }

  public void setScore(double score) {
    this.score = score;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof StudentOutcome)) return false;
    StudentOutcome that = (StudentOutcome) o;
    return getOutcomeId() == that.getOutcomeId() &&
        getStudentId() == that.getStudentId();
  }

  @Override
  public int hashCode() {

    return Objects.hash(getOutcomeId(), getStudentId());
  }
}
