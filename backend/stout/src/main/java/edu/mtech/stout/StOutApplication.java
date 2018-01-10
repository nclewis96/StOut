package edu.mtech.stout;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.jdbi.*;
import org.skife.jdbi.v2.DBI;

import edu.mtech.stout.resources.Login;

public class StOutApplication extends Application<StOutConfiguration> {

    public static void main(final String[] args) throws Exception {
        new StOutApplication().run(args);
    }

    @Override
    public String getName() {
        return "StOut";
    }

    @Override
    public void initialize(final Bootstrap<StOutConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final StOutConfiguration configuration,
                    final Environment environment) {
        // TODO: implement application
        final DBIFactory factory = new DBIFactory();
        final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
        //final UserDAO dao = jdbi.onDemand(UserDAO.class);
        //environment.jersey().register(new UserResource(dao));
        environment.jersey().register(new Login(configuration));
    }

}
