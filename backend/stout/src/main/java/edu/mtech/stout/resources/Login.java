package edu.mtech.stout.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.QueryParam;
import edu.mtech.stout.StOutConfiguration;
import edu.mtech.stout.api.AuthenticationObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

@Path("/login/")
@Produces(MediaType.APPLICATION_JSON)

public class Login{
  
  private StOutConfiguration config;

  public Login(StOutConfiguration config){
   this.config = config; 
  }


  @GET
  public AuthenticationObject attemptLogin(@QueryParam("ticket") String ticket){
    // Call casURL/validate
    //  Get Params: service, ticket
    //  service: set as config
    //  ticket passed into this as part of the get param
    try{
      URL cas = new URL(config.getCasURL());
    }catch(IOException ex){
    
    }
    return null;
  }
}
