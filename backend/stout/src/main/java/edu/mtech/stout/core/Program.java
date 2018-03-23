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
    ),
    @NamedNativeQuery(
      name = "edu.mtech.stout.core.Program.getByUserId",
      query = "SELECT * FROM Program WHERE program_id in (SELECT Program.program_id" +
        " FROM Program JOIN Program_Permissions ON Program.program_id = Program_Permissions.program_id " +
        "JOIN Users ON Users.user_id = Program_Permissions.user_id WHERE Users.user_id = ?)",
      resultClass = Program.class
    )
  })

public class Program{

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "program_id")
  private long programId;

  @Column(name = "name", nullable = false)
  private String name;

  public Program() {
  }

  public Program(String name) {
    this.name = name;
  }

  public long getProgramId() {
    return programId;
  }

  public void setProgramId(long id) {
    this.programId = id;
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

    return Objects.equals(this.programId, that.programId) &&
      Objects.equals(this.name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(programId, name);
  }
}



