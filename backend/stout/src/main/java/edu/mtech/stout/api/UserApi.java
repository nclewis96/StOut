package edu.mtech.stout.api;
import edu.mtech.stout.core.Role;
import edu.mtech.stout.core.User;
import edu.mtech.stout.core.JobTitle;
import com.fasterxml.jackson.annotation.JsonProperty;
import edu.mtech.stout.db.JobTitleDAO;
import edu.mtech.stout.db.RoleDAO;

import java.util.List;
import java.util.Optional;

public class UserApi {
  @JsonProperty
  private long userId;
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
    user.setUserId(userId);
    user.setName(name);
    user.setUsername(username);
    user.setJobTitle(jobTitle.getJobTitleId());
    return user;
  }

  public UserApi(User user, JobTitle jobTitle, List<Role> roleList){
    setup(user, jobTitle, roleList);
  }

  public List<Role> getRoleList() {
    return roleList;
  }

  public void setRoleList(List<Role> roleList) {
    this.roleList = roleList;
  }
  public long getUserId() {
    return userId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
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

  public UserApi(Optional<User> user, RoleDAO roleDao, JobTitleDAO jobTitleDAO){
    User currentUser = user.get();
    List<Role> roleList = roleDao.getByUserId(currentUser.getUserId());
    List<JobTitle> jobTitleList = jobTitleDAO.getByUserId(currentUser.getUserId());
    JobTitle title = null;
    if(jobTitleList.size() > 0){
      title = jobTitleList.get(0);
    }
    setup(currentUser, title, roleList);
  }

  private void setup(User user, JobTitle jobTitle, List<Role> roleList){
    userId = user.getUserId();
    name = user.getName();
    username = user.getUsername();
    this.jobTitle = jobTitle;
    this.roleList = roleList;
  }
}
