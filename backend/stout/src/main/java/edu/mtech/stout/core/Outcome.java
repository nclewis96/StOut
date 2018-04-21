package edu.mtech.stout.core;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "Outcome")
@NamedNativeQueries(
  {
    @NamedNativeQuery(
      name = "edu.mtech.stout.core.Outcome.findAll",
      query = "SELECT * FROM Outcome",
      resultClass = Outcome.class
    ),
      @NamedNativeQuery(
          name = "edu.mtech.stout.core.Outcome.findByMetric",
          query = "SELECT * FROM Outcome WHERE metric_id = ?",
          resultClass = Outcome.class
      )

  })

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Outcome  implements Serializable {

  private long id;
  private long metricId;
  private String name;
  private String description;
  private Set<Assign> assigns = new HashSet<>(0);

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "outcome_id")
  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  @Column(name = "metric_id")
  public long getMetricId() {
    return metricId;
  }

  public void setMetricId(long metricId) {
    this.metricId = metricId;
  }

  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "description")
  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  @ManyToMany(fetch = FetchType.LAZY, cascade=CascadeType.ALL)
  @JoinTable(name="Outcome_Assign",joinColumns={@JoinColumn(name="outcome_id", nullable = false, updatable = false)},inverseJoinColumns={@JoinColumn(name="assign_id", nullable = false, updatable = false)})
  public Set<Assign> getAssigns() {
    return assigns;
  }

  public void setAssigns(Set<Assign> assigns) {
    this.assigns = assigns;
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

    return Objects.equals(this.id, that.id) &&
      Objects.equals(this.name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}
