package edu.mtech.stout.api;

import edu.mtech.stout.core.User;
import edu.mtech.stout.db.PermissionsDAO;
import edu.mtech.stout.db.UserDAO;
import io.dropwizard.auth.Auth;
import io.dropwizard.jersey.params.LongParam;
import edu.mtech.stout.db.ProgramDAO;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.QueryParam;
import java.util.HashSet;

public class QueryBySelector {

    ProgramDAO programDao;
    UserDAO userDao;
    PermissionsDAO permissionsDAO;

    public QueryBySelector(ProgramDAO programDao){
        this.programDao = programDao;
    }
    public QueryBySelector(UserDAO userDao){this.userDao = userDao; }
    public QueryBySelector(PermissionsDAO permissionsDAO, ProgramDAO programDao){
        this.permissionsDAO = permissionsDAO;
        this.programDao = programDao;
    }

    public boolean queryByProgramId(@Auth User user, @QueryParam("programId") LongParam programId){
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
    //Checks a users Permission level
    public int  queryByUser(@Auth User user){
        if(user != null){
            return permissionsDAO.findByUserId(user.getId()).get(0).getPermissionId();

        }else{
            return -1;
        }
    }
    public boolean queryUserPerm(@Auth User user){
        if(user)
    }
}
