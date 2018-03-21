package edu.mtech.stout.core;

import javax.persistence.*;
import java.util.Objects;


@Entity
@Table(name = "Job_Title")
@NamedNativeQueries(
  {
    @NamedNativeQuery(
      name = "edu.mtech.stout.core.JobTitle.findAll",
      query = "SELECT * FROM Job_Title",
      resultClass = JobTitle.class
    ),
    @NamedNativeQuery(
      name = "edu.mtech.stout.core.JobTitle.getByUserId",
      query = "SELECT * FROM Job_Title WHERE job_title_id in (SELECT job_title_id" +
        " FROM Users WHERE user_id = ?)",
      resultClass = JobTitle.class
    )
  })
public class JobTitle {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "job_title_id")
  private long job_title_id;
  @Column(name = "title")
  private String title;

  public long getId() {
    return job_title_id;
  }

  public void setId(long id) {
    this.job_title_id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof JobTitle)) {
      return false;
    }

    final JobTitle that = (JobTitle) o;

    return Objects.equals(this.job_title_id, that.job_title_id) &&
      Objects.equals(this.title, that.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(job_title_id, title);
  }
}
