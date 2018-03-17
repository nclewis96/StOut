package edu.mtech.stout.resources;

import edu.mtech.stout.api.Status;
import edu.mtech.stout.core.Scale;
import edu.mtech.stout.db.ScaleDAO;
import edu.mtech.stout.db.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

import javax.annotation.security.RolesAllowed;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/scale/{scaleId}")
@Productes(MediaType.APPLICATION_JSON)
public class ScaleResource {
	
	ScaleDAO dao = null;
	
	public ScaleResource(ScaleDAO dao) {
		this.dao = dao;
	}
	
	@GET
	@UnitOfWork
	public Scale getScale(@PathParam("scaleId") LongParam scaleId) {
		return findSafely(scaleId.get());
	}
	
	private Scale findSafely(long scaleId) {
		return dao.findById(scaleId).orElseThrow(() -> new NotFoundException("No such scale."));
	}
	
	@POST
	@UnitOfWork
	public Scale updateScale(@PathParam("scaleId") LongParam scaleId, Scale scale){
		return dao.update(scale);
	}
	
	@DELETE
	@RolesAllowed({"Admin", "Program_Coordinator"})
	@UnitOfWork
	public Status deleteScale(@PathParam("scaleId") LongParam scaleId){
		Status status = new Status();
		status.setId(scaleId.get().intValue());
		status.setAction("DELETE");
		status.setResources("Scale");
		
		boolean success = dao.delete(scaleId.get().intValue());
		
		if(success){
			status.setMessage("Successfully deleted scale");
			status.setStatus(200);
		}else{
			status.setMessage("Error deleting scale");
			status.setStatus(500);
		}
		
		return status;
	}
}