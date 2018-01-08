package edu.mtech.stout.auth;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class StOutAuthenticator implements Authenticator<BasicCredentials, User>{

	@Override
	public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
		if (VALID_USERS.containsKey(credentials.getUsername()) && "secret".equals(credentials.getPassword())) {
			return Optional.of(new User(credentials.getUsername(), VALID_USERS.get(credentials.getUsername())));
		}
		return Optional.empty();
	}

}
