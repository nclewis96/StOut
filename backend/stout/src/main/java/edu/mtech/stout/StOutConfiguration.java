package edu.mtech.stout;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;
import javax.validation.constraints.*;

public class StOutConfiguration extends Configuration {
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
