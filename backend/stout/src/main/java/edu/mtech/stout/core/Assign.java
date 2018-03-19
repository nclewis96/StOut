package edu.mtech.stout.core;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "Offering_Assign")
@NamedNativeQueries(
  {
    @NamedNativeQuery(
      name = "edu.mtech.stout.core.Assign.findAll",
      query = "SELECT * FROM Offering_Assign",
      resultClass = Assign.class
    )
  })
public class Assign {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "semester_id")
  private long id;
  @Column(name = "offering_id")
  private long offering_id;
  @Column(name = "score")
  private long score;
  @Column(name = "name")
  private String name;
  @Column(name = "desc")
  private String desc;
  @Column(name = "max_score")
  private long maxScore;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getOffering_id() {
    return offering_id;
  }

  public void setOffering_id(long offering_id) {
    this.offering_id = offering_id;
  }

  public long getScore() {
    return score;
  }

  public void setScore(long score) {
    this.score = score;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  public long getMaxScore() {
    return maxScore;
  }

  public void setMaxScore(long maxScore) {
    this.maxScore = maxScore;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Assign)) {
      return false;
    }

    final Assign that = (Assign) o;

    return Objects.equals(this.id, that.id) &&
      Objects.equals(this.offering_id, that.offering_id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, offering_id);
  }
}
