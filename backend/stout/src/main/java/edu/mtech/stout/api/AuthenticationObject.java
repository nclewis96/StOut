package edu.mtech.stout.api;
import com.fasterxml.jackson.annotation.JsonProperty;

public class AuthenticationObject{

  @JsonProperty
  private String jwt;

  @JsonProperty
  public String getJwt(){
    return jwt;
  }

  @JsonProperty
  public void setJwt(String jwt){
    this.jwt = jwt;
  }
}
