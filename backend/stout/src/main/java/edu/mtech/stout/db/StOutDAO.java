package edu.mtech.stout.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.Optional;

abstract public class StOutDAO<Type> extends AbstractDAO<Type>{

  public StOutDAO(SessionFactory factory) {
    super(factory);
  }

  public Optional<Type> findById(Long id) {
    return Optional.ofNullable(get(id));
  }

  public Type create(Type type) {
    return persist(type);
  }

  public Type update(Type type) {return persist(type);}

  abstract public boolean delete(int type);
}
