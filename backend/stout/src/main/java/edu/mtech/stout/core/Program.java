package edu.mtech.stout.core;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "Program")
@NamedNativeQueries(
  {
    @NamedNativeQuery(
      name = "edu.mtech.stout.core.Program.findAll",
      query = "SELECT * FROM Program",
      resultClass = Program.class
    ),
    @NamedNativeQuery(
      name = "edu.mtech.stout.core.Program.findByName",
      query = "SELECT * FROM Program WHERE name = ?",
      resultClass = Program.class
    )
  })

public class Program{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "program_id")
  private long program_id;

  @Column(name = "name", nullable = false)
  private String name;

  public Program() {
  }

  public Program(String name) {
    this.name = name;
  }

  public long getId() {
    return program_id;
  }

  public void setId(long id) {
    this.program_id = id;
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

    return Objects.equals(this.program_id, that.program_id) &&
      Objects.equals(this.name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(program_id, name);
  }
}



