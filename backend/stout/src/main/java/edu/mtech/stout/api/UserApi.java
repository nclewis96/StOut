package edu.mtech.stout.api;
import edu.mtech.stout.core.Role;
import edu.mtech.stout.core.User;
import edu.mtech.stout.core.JobTitle;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class UserApi {
  @JsonProperty
  private long user_id;
  @JsonProperty
  private String name;
  @JsonProperty
  private String username;
  @JsonProperty
  private JobTitle jobTitle;
  @JsonProperty
  private List<Role> roleList;

  public User toUser(){
    User user = new User();
    user.setUserId(user_id);
    user.setName(name);
    user.setUsername(username);
    user.setJobTitle(jobTitle.getJobTitleId());
    return user;
  }

  public UserApi(User user, JobTitle jobTitle, List<Role> roleList){
    user_id = user.getUserId();
    name = user.getName();
    username = user.getUsername();
    this.jobTitle = jobTitle;
    this.roleList = roleList;
  }

  public List<Role> getRoleList() {
    return roleList;
  }

  public void setRoleList(List<Role> roleList) {
    this.roleList = roleList;
  }
  public long getUser_id() {
    return user_id;
  }

  public void setUser_id(long user_id) {
    this.user_id = user_id;
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

  public JobTitle getJobTitle() {
    return jobTitle;
  }

  public void setJobTitle(JobTitle jobTitle) {
    this.jobTitle = jobTitle;
  }
}
