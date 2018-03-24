package edu.mtech.stout.db;

import edu.mtech.stout.core.Scale;
import edu.mtech.stout.db.StOutDAO;
import org.hibernate.SessionFactory;

import java.util.List;

public class ScaleDAO extends StOutDAO<Scale> {
  public ScaleDAO(SessionFactory factory) {
    super(factory);
  }

  public List<Scale> findAll() {
    return list(namedQuery("edu.mtech.stout.core.Scale.findAll"));
  }

  @Override
  public boolean delete(int type) {
    return false;
  }
}
