package edu.mtech.stout;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.client.*;
import io.dropwizard.jdbi.*;
import org.skife.jdbi.v2.DBI;
import javax.ws.rs.client.Client;

import edu.mtech.stout.resources.Login;
import edu.mtech.stout.client.CASValidator;


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
    final Client client = new JerseyClientBuilder(environment).using(configuration.getJerseyClientConfiguration()).build(getName());
    environment.jersey().register(new CASValidator(configuration, client));
    //final UserDAO dao = jdbi.onDemand(UserDAO.class);
    //environment.jersey().register(new UserResource(dao));
    environment.jersey().register(new Login());
  }

}
