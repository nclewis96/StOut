package edu.mtech.stout.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;
import edu.mtech.stout.core.User;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class StOutAuthenticator implements Authenticator<BasicCredentials, User> {

  @Override
  public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
    if (credentials.getUsername() != null && credentials.getPassword() != null) {
      return Optional.of(new User(credentials.getUsername()));
    }
    return Optional.empty();
  }

}
