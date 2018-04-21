package edu.mtech.stout.core;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;


@Entity
@Table(name = "Offering_Assign")
@NamedNativeQueries(
  {
    @NamedNativeQuery(
      name = "edu.mtech.stout.core.Assign.findAll",
      query = "SELECT * FROM Offering_Assign",
      resultClass = Assign.class
    ),
      @NamedNativeQuery(
          name = "edu.mtech.stout.core.Assign.findProgramId",
          query = "SELECT * FROM Offering_Assign JOIN Offering ON Offering_Assign.offering_id=Offering.offering_id" +
              " JOIN Course ON Offering.course_id = Course.course_id WHERE Course.program_id = ?",
          resultClass = Assign.class
      )
  })
public class Assign  implements Serializable {

  private long id;
  private long offeringId;
  private long score;
  private String name;
  private String desc;
  private long maxScore;
  private Set<Outcome> outcomes= new HashSet<>(0);

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "assign_id")
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Column(name = "offering_id")
  public long getOfferingId() {
    return offeringId;
  }

  public void setOfferingId(long offeringId) {
    this.offeringId = offeringId;
  }

  @Column(name = "score")
  public long getScore() {
    return score;
  }

  public void setScore(long score) {
    this.score = score;
  }

  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "desc")
  public String getDesc() {
    return desc;
  }

  public void setDesc(String desc) {
    this.desc = desc;
  }

  @Column(name = "max_score")
  public long getMaxScore() {
    return maxScore;
  }

  public void setMaxScore(long maxScore) {
    this.maxScore = maxScore;
  }

  @ManyToMany(fetch = FetchType.LAZY, mappedBy="assigns")
  @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
  public Set<Outcome> getOutcomes() {
    return outcomes;
  }

  public void setOutcomes(Set<Outcome> outcomes) {
    this.outcomes = outcomes;
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
      Objects.equals(this.offeringId, that.offeringId);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, offeringId);
  }
}
