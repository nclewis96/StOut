package edu.mtech.stout.core;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Outcome")
@NamedNativeQueries(
  {
    @NamedNativeQuery(
      name = "edu.mtech.stout.core.Outcome.findAll",
      query = "SELECT * FROM Outcome",
      resultClass = Outcome.class
    )
  })

public class Outcome {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "outcome_id")
  private long outcome_id;
  @Column(name = "metric_id")
  private long metric_id;
  @Column(name = "name")
  private String name;
  @Column(name = "description")
  private String description;

  public long getId() {
    return outcome_id;
  }

  public void setId(long id) {
    this.outcome_id = id;
  }

  public long getMetric_id() {
    return metric_id;
  }

  public void setMetric_id(long metric_id) {
    this.metric_id = metric_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Outcome)) {
      return false;
    }

    final Outcome that = (Outcome) o;

    return Objects.equals(this.outcome_id, that.outcome_id) &&
      Objects.equals(this.name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(outcome_id, name);
  }
}
