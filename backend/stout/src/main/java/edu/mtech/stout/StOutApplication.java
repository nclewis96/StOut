package edu.mtech.stout;

import edu.mtech.stout.core.User;
import edu.mtech.stout.db.UserDAO;
import edu.mtech.stout.resources.DaoCreateTest;
import edu.mtech.stout.resources.DaoTest;
import io.dropwizard.Application;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.client.*;
import io.dropwizard.sslreload.SslReloadBundle;


import io.dropwizard.assets.AssetsBundle;

import javax.servlet.FilterRegistration;
import javax.ws.rs.client.Client;

import edu.mtech.stout.resources.Login;
import edu.mtech.stout.client.CASValidator;
import edu.mtech.stout.api.AuthenticationObject;

import org.eclipse.jetty.servlets.CrossOriginFilter;
import javax.servlet.DispatcherType;
import java.util.EnumSet;
import java.util.Map;


public class StOutApplication extends Application<StOutConfiguration> {

  public static void main(final String[] args) throws Exception {
    new StOutApplication().run(args);
  }

  private final HibernateBundle<StOutConfiguration> hibernateBundle =
    new HibernateBundle<StOutConfiguration>(User.class) {
      @Override
      public DataSourceFactory getDataSourceFactory(StOutConfiguration configuration) {
        return configuration.getDataSourceFactory();
      }
    };

  @Override
  public String getName() {
    return "StOut";
  }

  @Override
  public void initialize(final Bootstrap<StOutConfiguration> bootstrap) {
    // Enable variable substitution with environment variables
    bootstrap.setConfigurationSourceProvider(
      new SubstitutingSourceProvider(
        bootstrap.getConfigurationSourceProvider(),
        new EnvironmentVariableSubstitutor(false)
      )
    );
    bootstrap.addBundle(new AssetsBundle());
    bootstrap.addBundle(hibernateBundle);
    bootstrap.addBundle(new SslReloadBundle());
  }

  @Override
  public void run(final StOutConfiguration configuration,
      final Environment environment) {

    // Enable CORS headers
    final FilterRegistration.Dynamic cors =
      environment.servlets().addFilter("CORS", CrossOriginFilter.class);

    // Configure CORS parameters
    cors.setInitParameter("allowedOrigins", "*");
    cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
    cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

    // Add URL mapping
    cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");

    // DO NOT pass a preflight request to down-stream auth filters
    // unauthenticated preflight requests should be permitted by spec
    cors.setInitParameter(CrossOriginFilter.CHAIN_PREFLIGHT_PARAM, Boolean.FALSE.toString());

    // TODO: implement application
    //final DBIFactory factory = new DBIFactory();
    //final DBI jdbi = factory.build(environment, configuration.getDataSourceFactory(), "mysql");
    final UserDAO userDao = new UserDAO(hibernateBundle.getSessionFactory());
    final Client client = new JerseyClientBuilder(environment).using(configuration.getJerseyClientConfiguration()).build(getName());
    CASValidator cas = new CASValidator(configuration, client);
    environment.jersey().register(cas);
    environment.jersey().register(new Login(cas));
    environment.jersey().register(new DaoTest(userDao));
    environment.jersey().register(new DaoCreateTest(userDao));
    AuthenticationObject.setSecret(configuration.getJwtSecret());
    AuthenticationObject.setService(configuration.getService());
  }
}
