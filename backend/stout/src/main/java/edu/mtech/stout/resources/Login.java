@Path("/login/")
@Produces(MediaType.APPLICATION_JSON)

public class Login{

  @GET
  public AuthenticationObject attemptLogin(@QueryParam("ticket") ticket){
  
  }
}
