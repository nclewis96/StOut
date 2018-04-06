package edu.mtech.stout.api;

import edu.mtech.stout.core.User;
import io.dropwizard.auth.Auth;
import io.dropwizard.jersey.params.LongParam;
import edu.mtech.stout.db.ProgramDAO;

import javax.ws.rs.ForbiddenException;
import javax.ws.rs.QueryParam;
import java.util.HashSet;

public class QueryBySelector {

    ProgramDAO programDao;

    public QueryBySelector(ProgramDAO programDao){
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
}
