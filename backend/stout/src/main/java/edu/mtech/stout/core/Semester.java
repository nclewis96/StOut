package edu.mtech.stout.core;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Semester")
@NamedNativeQueries(
  {
    @NamedNativeQuery(
      name = "edu.mtech.stout.core.Semester.findAll",
      query = "SELECT * FROM Semester",
      resultClass = Semester.class
    )
  })
public class Semester {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "semesterId")
  private long semesterId;
  @Column(name = "semester_type_id")
  private long typeId;
  @Column(name = "year")
  private int year;

  public long getSemesterId() {
    return semesterId;
  }

  public void setSemesterId(long id) {
    this.semesterId = id;
  }

  public long getTypeId() {
    return typeId;
  }

  public void setTypeId(long typeId) {
    this.typeId = typeId;
  }

  public int getYear() {
    return year;
  }

  public void setYear(int year) {
    this.year = year;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Semester)) {
      return false;
    }

    final Semester that = (Semester) o;

    return Objects.equals(this.semesterId, that.semesterId) &&
      Objects.equals(this.year, that.year);
  }

  @Override
  public int hashCode() {
    return Objects.hash(semesterId, year, typeId);
  }
}
