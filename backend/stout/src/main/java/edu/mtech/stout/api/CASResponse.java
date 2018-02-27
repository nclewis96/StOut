package edu.mtech.stout.api;
import javax.xml.bind.annotation.XmlElement;

public class CASResponse{

  private String username;
  @XmlElement(name="cas:user")
  public void setUsername(String user){
    this.username = username;
  }


  public String getUsername(){
    return username;
  }

}
