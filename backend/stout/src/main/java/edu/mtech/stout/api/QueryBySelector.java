package edu.mtech.stout.api;

import edu.mtech.stout.core.Permissions;
import edu.mtech.stout.core.User;
import edu.mtech.stout.db.PermissionsDAO;
import edu.mtech.stout.db.UserDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.jersey.params.LongParam;
import edu.mtech.stout.db.ProgramDAO;

import javax.management.Query;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.QueryParam;
import java.util.HashSet;

public class QueryBySelector {

    ProgramDAO programDao;
    UserDAO userDao;
    PermissionsDAO permissionsDAO;

    public QueryBySelector(PermissionsDAO permissionsDAO){this.permissionsDAO = permissionsDAO;}
    public QueryBySelector(ProgramDAO programDao){
        this.programDao = programDao;
    }
    public QueryBySelector(UserDAO userDao){this.userDao = userDao; }
    public QueryBySelector(PermissionsDAO permissionsDAO, ProgramDAO programDao){
        this.permissionsDAO = permissionsDAO;
        this.programDao = programDao;
    }

    public boolean queryByProgramId( User user, @QueryParam("programId") LongParam programId){
    HashSet<Long> programAccessList = programDao.getProgramIdSetByUser(user.getId());

    if(programId != null) {
      if (programAccessList.contains(programId.get())) {
        return true;
      }else{
        throw new ForbiddenException();
      }
    }else {
      return false;
    }
  }

  public boolean queryByProgramId( User user, long programId){
    HashSet<Long> programAccessList = programDao.getProgramIdSetByUser(user.getId());
    if (programAccessList.contains(programId)) {
      return true;
    }else{
      throw new ForbiddenException();
    }
  }

    //Returns a users Permission level
    public long  getUserPerm( User user){
        if(user != null){
            return permissionsDAO.findByUserId(user.getId()).get(0).getPermissionId();

        }else{
            return -1;
        }
    }

    //Checks if a logged in user has the needed permissions for a program
    public boolean queryUserPermForProg(User user, long programId, long permissionId){
        if(user != null){
           Permissions userPerm = permissionsDAO.findByUserId(user.getId()).get(0);
           if(userPerm.getPermissionId() == permissionId && userPerm.getProgramId() == programId){
               return true;
           }

        }
        return false;

    }
}
