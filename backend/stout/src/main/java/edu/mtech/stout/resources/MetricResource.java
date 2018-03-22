package edu.mtech.stout.resources;

import edu.mtech.stout.api.Status;
import edu.mtech.stout.core.Metric;
import edu.mtech.stout.db.MetricDAO;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/metric/{metricId}")
@Produces(MediaType.APPLICATION_JSON)
public class MetricResource {
	
	MetricDAO dao = null;
	
	public MetricResource(MetricDAO dao){
		this.dao = dao;
	}
	
	@GET
	@UnitOfWork
	public Metric getMetric(@PathParam("metricId") LongParam metricId) {
		return findSafely(metricId.get());
	}
	
	private Metric findSafely(long metricId) {
		return dao.findById(metricId).orElseThrow(() -> new NotFoundException("No such metric."));
	}
	
	@POST
	@UnitOfWork
	public Metric updateMetric(@PathParam("metricId") LongParam metricId, Metric metric){
		return dao.update(metric);
	}
	
	@DELETE
	@RolesAllowed({"Admin", "Program Coordinator"})
	@UnitOfWork
	public Status deleteMetric(@PathParam("metricId") LongParam metricId){
		Status status = new Status();
		status.setId(metricId.get().intValue());
		status.setAction("DELETE");
		status.setResource("Metric");
		
		boolean success = dao.delete(metricId.get().intValue());
		
		if(success) {
			status.setMessage("Successfully deleted metric");
			status.setStatus(200);
		}else{
			status.setMessage("Error deleting metric");
			status.setStatus(500);
		}
		return status;
	}
	
	
	
}