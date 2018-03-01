package edu.mtech.stout.db;
import edu.mtech.stout.core.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class UserDAO extends AbstractDAO<User>{

  public UserDAO(SessionFactory factory) {
    super(factory);
  }

  public Optional<User> findById(Long id) {
    return Optional.ofNullable(get(id));
  }

  public User create(User user) {
    return persist(user);
  }

}