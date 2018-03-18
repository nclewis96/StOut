package edu.mtech.stout.core;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;
import java.security.Principal;
import java.util.Set;

@Entity
@Table(name = "Users")
@NamedNativeQueries(
  {
    @NamedNativeQuery(
      name = "edu.mtech.stout.core.User.findAll",
      query = "SELECT * FROM Users",
      resultClass = User.class
    ),
    @NamedNativeQuery(
      name = "edu.mtech.stout.core.User.findByUsername",
      query = "SELECT * FROM Users WHERE username = ?",
      resultClass = User.class
    )
  })

public class User implements Principal {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "user_id")
  private long id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "username", nullable = false)
  private String username;

  @Column(name = "job_title_id", nullable = false)
  private long jobTitle;

  //private final Set<String> roles;

  public User() {
    //this.roles = null;
  }

  public User(String name) {
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public User(String name, Set<String> roles) {
    this.name = name;
    //this.roles = roles;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }


  public long getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(long jobTitle) {
    this.jobTitle = jobTitle;
  }



  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof User)) {
      return false;
    }

    final User that = (User) o;

    return Objects.equals(this.id, that.id) &&
      Objects.equals(this.username, that.username);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, username, jobTitle);
  }


}



