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
  @Column(name = "outcomeId")
  private long outcomeId;
  @Column(name = "metricId")
  private long metricId;
  @Column(name = "name")
  private String name;
  @Column(name = "description")
  private String description;

  public long getOutcomeId() {
    return outcomeId;
  }

  public void setOutcomeId(long id) {
    this.outcomeId = id;
  }

  public long getMetricId() {
    return metricId;
  }

  public void setMetricId(long metricId) {
    this.metricId = metricId;
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

    return Objects.equals(this.outcomeId, that.outcomeId) &&
      Objects.equals(this.name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(outcomeId, name);
  }
}
