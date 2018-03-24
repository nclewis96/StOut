package edu.mtech.stout.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.mtech.stout.core.Offering;

import java.util.List;

public class OfferingsWrapper {
  @JsonProperty
  public List<Offering> offerings;

  public List<Offering> getOfferings() {
    return offerings;
  }

  public void setOfferings(List<Offering> offerings) {
    this.offerings = offerings;
  }
}
