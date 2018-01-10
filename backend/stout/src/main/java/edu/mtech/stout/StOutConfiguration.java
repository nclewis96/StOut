package edu.mtech.stout;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.jdbi.DBIFactory;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import io.dropwizard.client.JerseyClientConfiguration;

public class StOutConfiguration extends Configuration {
  /********************************************************************
   * CLIENT
   ********************************************************************/
  @Valid
  @NotNull
  private JerseyClientConfiguration jerseyClient = new JerseyClientConfiguration();

  @JsonProperty("jerseyClient")
  public JerseyClientConfiguration getJerseyClientConfiguration() {
    return jerseyClient;
  }

  @JsonProperty("jerseyClient")
  public void setJerseyClientConfiguration(JerseyClientConfiguration jerseyClient) {
    this.jerseyClient = jerseyClient;
  }
  /********************************************************************
   * DATABASE
   ********************************************************************/
  @Valid
  @NotNull
  private DataSourceFactory database = new DataSourceFactory();

  @JsonProperty("database")
  public void setDataSourceFactory(DataSourceFactory factory){
    this.database = factory; 
  }

  @JsonProperty("database")
  public DataSourceFactory getDataSourceFactory(){
    return database;
  }
  /*******************************************************************
   * CAS Auth and Service Config
   ********************************************************************/
  // TODO: implement service configuration
  @NotEmpty
  private String service;

  @NotEmpty
  private String casURL;

  @JsonProperty
  public String getService() {
    return service;
  }

  @JsonProperty
  public void setService(String service) {
    this.service = service;
  }

  @JsonProperty
  public String getCasURL() {
    return casURL;
  }

  @JsonProperty
  public void setCasURL(String casURL) {
    this.casURL = casURL;
  }
}
