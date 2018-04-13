package edu.mtech.stout.db;

import edu.mtech.stout.core.OfferingAssign;
import org.hibernate.SessionFactory;

import java.util.List;

public class OfferingAssignDAO extends StOutDAO<OfferingAssign> {
  public OfferingAssignDAO(SessionFactory factory){
    super(factory);
  }

  public List<OfferingAssign> findAll(){
    return list(namedQuery("edu.mtech.stout.core.OfferingAssign.findAll"));
  }
}
