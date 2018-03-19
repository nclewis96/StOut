package edu.mtech.stout.filter;

import edu.mtech.stout.api.AuthenticationObject;
import io.dropwizard.auth.AuthFilter;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.core.HttpHeaders;
import java.io.IOException;
import java.security.Principal;

public class UserAuthFilter<P extends Principal> extends AuthFilter<AuthenticationObject, P> {
  public static class Builder<P extends Principal>
    extends AuthFilterBuilder<AuthenticationObject, P, UserAuthFilter<P>> {

    @Override
    protected UserAuthFilter<P> newInstance() {
      return new UserAuthFilter<>();
    }
  }

  @Override
  public void filter(ContainerRequestContext requestContext) throws IOException {
    final AuthenticationObject credentials =
      getCredentials(requestContext.getHeaders().getFirst(HttpHeaders.AUTHORIZATION));
    if (!authenticate(requestContext, credentials, "JWT")) {
      throw new WebApplicationException(
        this.unauthorizedHandler.buildResponse(this.prefix, this.realm));
    }
  }

  private static AuthenticationObject getCredentials(String authLine) {
    if (authLine != null && authLine.startsWith("Bearer")) {
      AuthenticationObject result = new AuthenticationObject();
      result.setJwt(authLine.substring(7));
      result.setUsername(result.retrieveUsername());
      return result;
    }
    return null;
  }
}
