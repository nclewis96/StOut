package edu.mtech.stout.db;

import edu.mtech.stout.core.Program;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class ProgramDAO extends StOutDAO<Program> {

  public ProgramDAO(SessionFactory factory) {
    super(factory);
  }


  public Optional<Program> findByName(String name) {
    List<Program> programList = list(namedQuery("edu.mtech.stout.core.Program.findByName")
                        .setParameter(0, name));
    Optional<Program> user = Optional.empty();
    if(!programList.isEmpty()){
      user = Optional.of(programList.get(0));
    }
    return user;
  }

  public List<Program> findAll() {
    return list(namedQuery("edu.mtech.stout.core.Program.findAll"));
  }
}