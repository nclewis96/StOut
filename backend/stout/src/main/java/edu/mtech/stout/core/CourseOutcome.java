package edu.mtech.stout.core;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Course_Outcome")
@NamedNativeQueries(
    {
        @NamedNativeQuery(
            name = "edu.mtech.stout.core.CourseOutcome.findAll",
            query = "SELECT * FROM Course_Outcome",
            resultClass = CourseOutcome.class
        ),
        @NamedNativeQuery(
          name = "edu.mtech.stout.core.CourseOutcome.findByCourseAndMetric",
          query = "SELECT * FROM Course_Outcome " +
            "JOIN Outcome on Course_Outcome.outcome_id = Outcome.outcome_id " +
            "JOIN Metric ON Metric.metric_id = Outcome.metric_id WHERE course_id = ? AND Metric.metric_id = ?;",
          resultClass = CourseOutcome.class
        )
    })

public class CourseOutcome implements Serializable{

  @Id
  @Column(name = "course_id")
  private long courseId;
  @Id
  @Column(name = "outcome_id")
  private long outcomeId;

  public CourseOutcome() { super(); }

  public CourseOutcome(long courseId, long outcomeId){
    this.courseId = courseId;
    this.outcomeId = outcomeId;
  }

  public long getCourseId() {
    return courseId;
  }

  public void setCourseId(long courseId) {
    this.courseId = courseId;
  }

  public long getOutcomeId() {
    return outcomeId;
  }

  public void setOutcomeId(long outcomeId) {
    this.outcomeId = outcomeId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CourseOutcome)) return false;
    CourseOutcome that = (CourseOutcome) o;
    return getCourseId() == that.getCourseId() &&
        getOutcomeId() == that.getOutcomeId();
  }

  @Override
  public int hashCode() {

    return Objects.hash(getCourseId(), getOutcomeId());
  }
}
