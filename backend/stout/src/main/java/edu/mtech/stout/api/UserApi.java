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
  private long id;
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
    user.setId(id);
    user.setName(name);
    user.setUsername(username);
    user.setJobTitle(jobTitle.getId());
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
    List<Role> roleList = roleDao.getByUserId(currentUser.getId());
    List<JobTitle> jobTitleList = jobTitleDAO.getByUserId(currentUser.getId());
    JobTitle title = null;
    if(jobTitleList.size() > 0){
      title = jobTitleList.get(0);
    }
    setup(currentUser, title, roleList);
  }

  private void setup(User user, JobTitle jobTitle, List<Role> roleList){
    id = user.getId();
    name = user.getName();
    username = user.getUsername();
    this.jobTitle = jobTitle;
    this.roleList = roleList;
  }
}
