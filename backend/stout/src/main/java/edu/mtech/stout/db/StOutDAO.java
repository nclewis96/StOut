package edu.mtech.stout.db;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class StOutDAO<Type> extends AbstractDAO<Type>{

  public StOutDAO(SessionFactory factory) {
    super(factory);
  }

  public Optional<Type> findById(Long id) {
    return Optional.ofNullable(get(id));
  }

  public Type create(Type type) {
    return persist(type);
  }

  public Type update(Type type) {return update(type);}

  public boolean delete(int type) {return delete(type);};
}