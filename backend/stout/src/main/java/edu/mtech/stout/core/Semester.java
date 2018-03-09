package edu.mtech.stout.core;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Semester")
@NamedNativeQueries(
  {
    @NamedNativeQuery(
      name = "edu.mtech.stout.core.Outcome.findAll",
      query = "SELECT * FROM Semester",
      resultClass = Semester.class
    )
  })
public class Semester {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "semester_id")
  private long id;
  @Column(name = "semester_type_id")
  private long type_id;
  @Column(name = "year")
  private int year;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getType_id() {
    return type_id;
  }

  public void setType_id(long type_id) {
    this.type_id = type_id;
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

    return Objects.equals(this.id, that.id) &&
      Objects.equals(this.year, that.year);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, year, type_id);
  }
}
