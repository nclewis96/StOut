package edu.mtech.stout.core;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Offering")
@NamedNativeQueries(
  {
    @NamedNativeQuery(
      name = "edu.mtech.stout.core.Offering.findAll",
      query = "SELECT * FROM Offering",
      resultClass = Offering.class
    ),
    @NamedNativeQuery(
      name = "edu.mtech.stout.core.Offering.findByCourse",
      query = "SELECT * FROM Offering WHERE course = ?",
      resultClass = Offering.class
    )
  })

public class Offering {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "offering_id")
  private long offeringId;
  @Column(name = "course_id", nullable = false)
  private long courseId;
  @Column(name = "user_id", nullable = false)
  private long instructorId;
  @Column(name = "semester_id", nullable = false)
  private long semesterId;
  @Column(name = "section_num", nullable = false)
  private String sectionNum;
  @Column(name = "locked", nullable = false)
  private boolean locked;
  @Column(name = "num_students", nullable = false)
  private long numStudents;

  public Offering() {
  }

  public long getOfferingId() {
    return offeringId;
  }

  public void setOfferingId(long id) {
    this.offeringId = id;
  }

  public long getCourseId() {
    return courseId;
  }

  public void setCourseId(long courseId) {
    this.courseId = courseId;
  }

  public long getInstructorId() {
    return instructorId;
  }

  public void setInstructorId(long instructorId) {
    this.instructorId = instructorId;
  }

  public long getSemesterId() {
    return semesterId;
  }

  public void setSemesterId(long semesterId) {
    this.semesterId = semesterId;
  }

  public String getSectionNum() {
    return sectionNum;
  }

  public void setSectionNum(String sectionNum) {
    this.sectionNum = sectionNum;
  }

  public boolean isLocked() {
    return locked;
  }

  public void setLocked(boolean locked) {
    this.locked = locked;
  }

  public long getNumStudents() {
    return numStudents;
  }

  public void setNumStudents(long numStudents) {
    this.numStudents = numStudents;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Offering)) {
      return false;
    }

    final Offering that = (Offering) o;

    return Objects.equals(this.offeringId, that.offeringId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(offeringId);
  }

}
