package edu.mtech.stout;

import edu.mtech.stout.api.AuthenticationObject;
import edu.mtech.stout.auth.StOutAuthenticator;
import edu.mtech.stout.auth.StOutAuthorizer;
import edu.mtech.stout.client.CASValidator;
import edu.mtech.stout.core.*;
import edu.mtech.stout.db.*;
import edu.mtech.stout.filter.UserAuthFilter;
import edu.mtech.stout.resources.*;
import io.dropwizard.Application;
import io.dropwizard.assets.AssetsBundle;
import io.dropwizard.auth.AuthDynamicFeature;
import io.dropwizard.auth.AuthValueFactoryProvider;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.configuration.EnvironmentVariableSubstitutor;
import io.dropwizard.configuration.SubstitutingSourceProvider;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;
import io.dropwizard.hibernate.UnitOfWorkAwareProxyFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import io.dropwizard.sslreload.SslReloadBundle;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import org.glassfish.jersey.server.filter.RolesAllowedDynamicFeature;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.ws.rs.client.Client;
import java.util.EnumSet;

public class StOutApplication extends Application<StOutConfiguration> {

  public static void main(final String[] args) throws Exception {
    new StOutApplication().run(args);
  }

  private final HibernateBundle<StOutConfiguration> hibernateBundle =
    new HibernateBundle<StOutConfiguration>(User.class, Role.class, Assign.class,
      Metric.class, Offering.class, Outcome.class, Program.class,
      Scale.class, Semester.class) {
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

    //Set up DAO objects
    final AssignDAO assignDao = new AssignDAO(hibernateBundle.getSessionFactory());
    final UserDAO userDao = new UserDAO(hibernateBundle.getSessionFactory());
    final ProgramDAO programDao = new ProgramDAO(hibernateBundle.getSessionFactory());
    final OfferingDAO offeringDao = new OfferingDAO(hibernateBundle.getSessionFactory());
    final OutcomeDAO outcomeDao = new OutcomeDAO(hibernateBundle.getSessionFactory());
    final MetricDAO metricDao = new MetricDAO(hibernateBundle.getSessionFactory());
    final ScaleDAO scaleDao = new ScaleDAO(hibernateBundle.getSessionFactory());
    final SemesterDAO semesterDao = new SemesterDAO(hibernateBundle.getSessionFactory());
    final RoleDAO roleDao = new RoleDAO(hibernateBundle.getSessionFactory());

    //Set up auth
    UserDAO authDao = new UserDAO(hibernateBundle.getSessionFactory());
    StOutAuthenticator stOutAuthenticator = new UnitOfWorkAwareProxyFactory(hibernateBundle)
      .create(StOutAuthenticator.class, UserDAO.class, authDao);
    environment.jersey().register(new AuthDynamicFeature(
      new UserAuthFilter.Builder<User>()
        .setAuthenticator(stOutAuthenticator)
        .setAuthorizer(new StOutAuthorizer(roleDao))
        .setPrefix("Bearer")
        .buildAuthFilter()));
    environment.jersey().register(RolesAllowedDynamicFeature.class);
    environment.jersey().register(new AuthValueFactoryProvider.Binder<>(User.class));
    final Client client = new JerseyClientBuilder(environment).using(configuration.getJerseyClientConfiguration()).build(getName());
    CASValidator cas = new CASValidator(configuration, client);
    environment.jersey().register(cas);
    environment.jersey().register(new Login(cas, userDao, roleDao));
    AuthenticationObject.setSecret(configuration.getJwtSecret());
    AuthenticationObject.setService(configuration.getService());

    //Set up routes
    environment.jersey().register(new UserResource(userDao));
    environment.jersey().register(new UserResourceList(userDao));
    environment.jersey().register(new ProgramResource(programDao));
    environment.jersey().register(new ProgramResourceList(programDao));
    environment.jersey().register(new OfferingResource(offeringDao));
    environment.jersey().register(new OfferingResourceList(offeringDao));
    environment.jersey().register(new OutcomeResource(outcomeDao));
    environment.jersey().register(new OutcomeResourceList(outcomeDao));
    environment.jersey().register(new AssignResourceList(assignDao));
    environment.jersey().register(new MetricResourceList(metricDao));
    environment.jersey().register(new SemesterResourceList(semesterDao));
    environment.jersey().register(new ScaleResourceList(scaleDao));
  }
}
