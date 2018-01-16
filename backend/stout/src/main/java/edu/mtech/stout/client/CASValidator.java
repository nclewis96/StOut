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

  public String validateTicket(String ticket){
    WebTarget webResource = client.target(config.getCasURL()).path("validate");
    webResource = webResource.queryParam("ticket", ticket);
    webResource = webResource.queryParam("service", config.getService());
    Invocation.Builder invocationBuilder = webResource.request(MediaType.APPLICATION_XML);
    Response response = invocationBuilder.get();
    response.readEntity();
    return "";
  }
}
