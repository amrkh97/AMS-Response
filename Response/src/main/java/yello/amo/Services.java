package yello.amo;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import Models.IncidentResponse.IncidentResponse;
import Models.IncidentResponse.SearchResponseStatus;
import Models.IncidentResponse.UpdateResponseStatus;
import BLL.ResponseManager;

@Path("api")
public class Services {

	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getIt() {
		return "Server is Running ..!";
	}

	@Path("incident/addIncidentResponse")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response InsertResponse(IncidentResponse Incident) throws Exception {
		if (Incident.getVin() == 0) {
			return Response.ok(" No Car ID Provided ").build();
		} else if (Incident.getStartLocID() == 0) {
			return Response.ok(" No Start Location ID Provided ").build();
		} else if (Incident.getPickLocID() == 0) {
			return Response.ok(" No Pick Up Locaiton ID Provided ").build();
		} else if (Incident.getDropLocID() == 0) {
			return Response.ok(" No Drop Locaiton ID Provided ").build();
		} else if (Incident.getDestLocID() == 0) {
			return Response.ok(" No Destination Locaiton ID Provided ").build();
		} else if (Incident.getiSQN() == 0) {
			return Response.ok(" No Incident Squance Number Provided ").build();
		} else if (Incident.getAlarmLevelID() == 0) {
			return Response.ok(" No Alarm Level ID Provided ").build();
		} else {
			return Response.ok(ResponseManager.addIncidentResponse(Incident)).build();
		}
	}

	@Path("Incident/searchResponseStatus")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response SearchResponseStatus(SearchResponseStatus Incident) {
		if (Incident.getSequanceNumber() == 0) {
			return Response.ok(" No Response Squance Number Provided ").build();
		} else {
			return Response.ok(ResponseManager.SearchResponseStatus(Incident)).build();
		}
	}

	@Path("incident/updateResponseStatus")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response UpdateResponseStatus(UpdateResponseStatus Incident) {
		if (Incident.getSequanceNumber() == 0) {
			return Response.ok(" No Response Squance Number Provided ").build();
		} else if (Incident.getResponseStatus() == null || Incident.getResponseStatus().equals("")) {
			return Response.ok(" No Response Status Provided ").build();
		} else {
			return Response.ok(ResponseManager.UpdateResponseStatus(Incident)).build();
		}
	}
	
	@Path("incident/getResponseTable")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResponseTableData() {
		return Response.ok(ResponseManager.getResponseTableData()).build();
	}

}
