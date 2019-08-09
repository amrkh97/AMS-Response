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
import Models.IncidentResponse.ServerResponse;
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
		ServerResponse response = new ServerResponse();
		if (Incident.getVin() == 0) {
			response.setResponseMsg(" No Car ID Provided ");
			return Response.status(401).entity(response).build();
		} else if (Incident.getStartLocID() == 0) {
			response.setResponseMsg(" No Start Location ID Provided ");
			return Response.status(402).entity(response).build();
		} else if (Incident.getPickLocID() == 0) {
			response.setResponseMsg(" No Pick Up Locaiton ID Provided ");
			return Response.status(403).entity(response).build();
		} else if (Incident.getDropLocID() == 0) {
			response.setResponseMsg(" No Drop Locaiton ID Provided ");
			return Response.status(405).entity(response).build();
		} else if (Incident.getDestLocID() == 0) {
			response.setResponseMsg(" No Destination Locaiton ID Provided ");
			return Response.status(406).entity(response).build();
		} else if (Incident.getiSQN() == 0) {
			response.setResponseMsg(" No Incident Squance Number Provided ");
			return Response.status(407).entity(response).build();
		} else if (Incident.getAlarmLevelID() == 0) {
			response.setResponseMsg(" No Alarm Level ID Provided ");
			return Response.status(408).entity(response).build();
		} else {
			return Response.ok(ResponseManager.addIncidentResponse(Incident)).header("Access-Control-Allow-Origin", "*").build();
		}
	}

	@Path("Incident/searchResponseStatus")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response SearchResponseStatus(SearchResponseStatus Incident) {
		ServerResponse response = new ServerResponse();
		if (Incident.getSequanceNumber() == 0) {
			response.setResponseMsg(" No Response Squance Number Provided ");
			return Response.status(401).entity(response).build();
		} else {
			return Response.ok(ResponseManager.SearchResponseStatus(Incident)).header("Access-Control-Allow-Origin", "*").build();
		}
	}

	@Path("incident/updateResponseStatus")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response UpdateResponseStatus(UpdateResponseStatus Incident) {
		ServerResponse response = new ServerResponse();
		if (Incident.getSequanceNumber() == 0) {
			response.setResponseMsg(" No Response Squance Number Provided ");
			return Response.status(401).entity(response).build();
		} else if (Incident.getResponseStatus() == null || Incident.getResponseStatus().equals("")) {
			response.setResponseMsg(" No Response Status Provided ");
			return Response.status(402).entity(response).build();
		} else {
			return Response.ok(ResponseManager.UpdateResponseStatus(Incident)).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	
	@Path("incident/getResponseTable")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResponseTableData() {
		return Response.ok(ResponseManager.getResponseTableData()).header("Access-Control-Allow-Origin", "*").build();
	}

}
