package edu.mtech.stout.api;

import edu.mtech.stout.core.CourseOutcome;
import edu.mtech.stout.core.Outcome;

import java.util.List;

public class MatrixReportApi {
  List<Outcome> outcomes;
  List<CourseOutcome> courseOutcomes;

  public List<Outcome> getOutcomes() {
    return outcomes;
  }

  public void setOutcomes(List<Outcome> outcomes) {
    this.outcomes = outcomes;
  }

  public List<CourseOutcome> getCourseOutcomes() {
    return courseOutcomes;
  }

  public void setCourseOutcomes(List<CourseOutcome> courseOutcomes) {
    this.courseOutcomes = courseOutcomes;
  }
}
