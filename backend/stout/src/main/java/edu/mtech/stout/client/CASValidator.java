package edu.mtech.stout.client;

import edu.mtech.stout.StOutConfiguration;
import javax.ws.rs.client.Client;

public class CASValidator{
  private StOutConfiguration config;
  private Client client;

  public CASValidator(StOutConfiguration config, Client client){
    this.config = config; 
    this.client = client;
  }


}
