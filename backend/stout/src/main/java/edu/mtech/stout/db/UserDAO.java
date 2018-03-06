package edu.mtech.stout.db;

import edu.mtech.stout.core.User;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class UserDAO extends AbstractDAO<User> {

  public UserDAO(SessionFactory factory) {
    super(factory);
  }

  public Optional<User> findById(Long id) {
    return Optional.ofNullable(get(id));
  }

  public Optional<User> findByUsername(String username) {
    List<User> userList = list(namedQuery("edu.mtech.stout.core.User.findByUsername")
                        .setParameter(0, username));
    Optional<User> user = Optional.empty();
    if(!userList.isEmpty()){
      user = Optional.of(userList.get(0));
    }
    return user;
  }

  public User create(User user) {
    return persist(user);
  }

  public User update(User user) {return update(user);}

  public boolean delete(int userId) {return delete(userId);};

  public List<User> findAll() {
    return list(namedQuery("edu.mtech.stout.core.User.findAll"));
  }
}