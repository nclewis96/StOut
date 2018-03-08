package edu.mtech.stout.core;

import javax.persistence.*;
import java.util.Objects;
import edu.mtech.stout.db.ProgramDAO;

@Entity
@Table(name = "Programs")
@NamedNativeQueries(
  {
    @NamedNativeQuery(
      name = "edu.mtech.stout.core.Program.findAll",
      query = "SELECT * FROM Programs",
      resultClass = ProgramDAO.class
    ),
    @NamedNativeQuery(
      name = "edu.mtech.stout.core.Program.findByName",
      query = "SELECT * FROM Programs WHERE name = ?",
      resultClass = ProgramDAO.class
    )
  })

public class Program{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private long id;

  @Column(name = "name", nullable = false)
  private String name;

  public Program() {
  }

  public Program(String name) {
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
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
    if (!(o instanceof Program)) {
      return false;
    }

    final Program that = (Program) o;

    return Objects.equals(this.id, that.id) &&
      Objects.equals(this.name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }
}



