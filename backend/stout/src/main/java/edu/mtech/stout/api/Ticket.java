package edu.mtech.stout.api;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket {

  @JsonProperty
  private String ticket;

  @JsonProperty
  private String jwt;

  @JsonProperty
  public void setTicket(String ticket) {
    this.ticket = ticket;
  }

  @JsonProperty
  public String getTicket() {
    return ticket;
  }

  @JsonProperty
  public void setJwt(String jwt) {
    this.jwt = jwt;
  }

  @JsonProperty
  public String getJwt() {
    return jwt;
  }
}



