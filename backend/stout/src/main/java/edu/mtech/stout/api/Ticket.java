package edu.mtech.stout.api;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Ticket {
  @JsonProperty
  private String ticket;
  @JsonProperty
  public void setTicket(String ticket){
    this.ticket = ticket;
  }

  @JsonProperty
  public String getTicket(){
    return ticket;
  }
}



