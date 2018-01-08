package edu.mtech.stout;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

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
        final DBI jdbi = factory.build(environment, config.getDataSourceFactory(), "mysql");
        final UserDAO dao = jdbi.onDemand(UserDAO.class);
        environment.jersey().register(new UserResource(dao));
    }

}
