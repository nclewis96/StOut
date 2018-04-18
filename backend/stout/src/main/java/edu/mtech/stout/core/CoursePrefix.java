package edu.mtech.stout.core;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "Course_Prefix")
@NamedNativeQueries(
  {
    @NamedNativeQuery(
      name = "edu.mtech.stout.core.CoursePrefix.findAll",
      query = "SELECT * FROM Course_Prefix",
      resultClass = CoursePrefix.class
    )
  }
)

public class CoursePrefix  implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "prefix_id")
  private long id;
  @Column(name = "prefix")
  private String prefix;

  public CoursePrefix() {
  }

  public long getId() {
    return id;
  }

  public void setId(long prefix_id) {
    this.id = prefix_id;
  }

  public String getPrefix() {
    return prefix;
  }

  public void setPrefix(String prefix) {
    this.prefix = prefix;
  }

  public boolean equals(Object object) {
    if (this == object) return true;
    if (!(object instanceof CoursePrefix)) return false;
    if (!super.equals(object)) return false;
    CoursePrefix that = (CoursePrefix) object;
    return getId() == that.getId() &&
      java.util.Objects.equals(getPrefix(), that.getPrefix());
  }

  public int hashCode() {

    return java.util.Objects.hash(super.hashCode(), getId(), getPrefix());
  }
}