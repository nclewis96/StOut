package edu.mtech.stout.api;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CASResponse{

  private String username;
  @JsonProperty
  public void setUsername(String user){
    this.username = username;
  }

  @JsonProperty
  public String getUsername(){
    return username;
  }

}
