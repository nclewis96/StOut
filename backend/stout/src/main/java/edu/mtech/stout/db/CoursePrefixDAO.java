package  edu.mtech.stout.db;

import edu.mtech.stout.core.CoursePrefix;
import org.hibernate.SessionFactory;

import java.util.List;
import java.util.Optional;

public class CoursePrefixDAO extends StOutDAO<CoursePrefix>{
    public CoursePrefixDAO(SessionFactory factory)
    {
        super(factory);
    }

    public List<CoursePrefix> findAll()
    {
        return list(namedQuery("edu.mtech.stout.core.CoursePrefix.findAll"));
    }
}