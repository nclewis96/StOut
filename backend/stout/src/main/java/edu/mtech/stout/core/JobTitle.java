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
  private long jobTitleId;
  @Column(name = "title")
  private String title;

  public long getJobTitleId() {
    return jobTitleId;
  }

  public void setJobTitleId(long id) {
    this.jobTitleId = id;
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

    return Objects.equals(this.jobTitleId, that.jobTitleId) &&
      Objects.equals(this.title, that.title);
  }

  @Override
  public int hashCode() {
    return Objects.hash(jobTitleId, title);
  }
}
