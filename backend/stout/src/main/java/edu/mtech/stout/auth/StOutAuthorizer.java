package edu.mtech.stout.auth;

import edu.mtech.stout.core.User;
import io.dropwizard.auth.Authorizer;

public class StOutAuthorizer implements Authorizer<User> {

  @Override
  public boolean authorize(User user, String role) {
    return false;
    //return user.getRoles() != null && user.getRoles().contains(role);
  }
}
