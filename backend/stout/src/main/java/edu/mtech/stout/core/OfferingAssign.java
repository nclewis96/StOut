package edu.mtech.stout.core;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

@Entity
@Table(name = "Offering_Assign")
@NamedNativeQueries(
    {
        @NamedNativeQuery(
            name = "edu.mtech.stout.core.OfferingAssign.findAll",
            query = "SELECT * FROM Offering_Assign",
            resultClass = OfferingAssign.class
        )
    })
public class OfferingAssign implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "assign_id")
  private long id;
  @Column(name = "offering_id")
  private long offeringId;
  @Column(name = "score")
  private int score;
  @Column(name = "name")
  private String name;
  @Column(name = "desc")
  private String desc;
  @Column(name = "max_score")
  private int masScore;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public long getOfferingId() {
    return offeringId;
  }

  public void setOfferingId(long offeringId) {
    this.offeringId = offeringId;
  }

  public int getScore() {
    return score;
  }

  public void setScore(int score) {
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

  public int getMasScore() {
    return masScore;
  }

  public void setMasScore(int masScore) {
    this.masScore = masScore;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof OfferingAssign)) return false;
    OfferingAssign that = (OfferingAssign) o;
    return getId() == that.getId() &&
        getOfferingId() == that.getOfferingId() &&
        getScore() == that.getScore() &&
        getMasScore() == that.getMasScore() &&
        Objects.equals(getName(), that.getName()) &&
        Objects.equals(getDesc(), that.getDesc());
  }

  @Override
  public int hashCode() {

    return Objects.hash(getId(), getOfferingId(), getScore(), getName(), getDesc(), getMasScore());
  }
}
