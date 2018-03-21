package edu.mtech.stout.core;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Metric")
@NamedNativeQueries(
  {
    @NamedNativeQuery(
      name = "edu.mtech.stout.core.Metric.findAll",
      query = "SELECT * FROM Metric",
      resultClass = Metric.class
    )
  })
public class Metric {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "metric_id")
  private long metric_id;
  @Column(name = "program_id")
  private long program_id;
  @Column(name = "name")
  private String name;

  public long getId() {
    return metric_id;
  }

  public void setId(long id) {
    this.metric_id = id;
  }

  public long getProgram_id() {
    return program_id;
  }

  public void setProgram_id(long program_id) {
    this.program_id = program_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Metric)) {
      return false;
    }

    final Metric that = (Metric) o;

    return Objects.equals(this.metric_id, that.metric_id) &&
      Objects.equals(this.name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(metric_id, name, program_id);
  }
}
