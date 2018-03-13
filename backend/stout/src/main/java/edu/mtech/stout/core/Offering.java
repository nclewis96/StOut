package edu.mtech.stout.core;

import javax.persistence.*;
import java.util.Objects;
import edu.mtech.stout.db.OfferingDAO;
import edu.mtech.stout.db.OutcomeDAO;

@Entity
@Table(name = "Offering")
@NamedNativeQueries(
  {
    @NamedNativeQuery(
      name = "edu.mtech.stout.core.Offering.findAll",
      query = "SELECT * FROM Offering",
      resultClass = OutcomeDAO.class
    ),
    @NamedNativeQuery(
      name = "edu.mtech.stout.core.Offering.findByCourse",
      query = "SELECT * FROM Offering WHERE course = ?",
      resultClass = OutcomeDAO.class
    )
  })

public class Offering {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "offering_id")
  private long id;
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

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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

    return Objects.equals(this.id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

}
