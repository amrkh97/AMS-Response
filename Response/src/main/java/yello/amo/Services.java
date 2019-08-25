package yello.amo;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import Models.AcceptableResponseStatus.ResponseStatusModel;
import Models.AcceptableResponseStatus.UpdateResponseStatusModel;
import Models.IncidentResponse.IncidentResponse;
import Models.IncidentResponse.IncidentResponseMsg;
import Models.IncidentResponse.ResponseTableDatePicker;
import Models.IncidentResponse.SearchResponseStatus;
import Models.IncidentResponse.ServerResponse;
import Models.IncidentResponse.UpdateResponseStatus;
import BLL.AcceptableResponseManager;
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
			response.setResponseMsg("A01004007001");
			return Response.status(401).entity(response).build();
		} else if (Incident.getStartLocID() == 0) {
			response.setResponseMsg("A01004007002");
			return Response.status(402).entity(response).build();
		} else if (Incident.getPickLocID() == 0) {
			response.setResponseMsg("A01004007003");
			return Response.status(403).entity(response).build();
		} else if (Incident.getDropLocID() == 0) {
			response.setResponseMsg("A01004007004");
			return Response.status(405).entity(response).build();
		} else if (Incident.getDestLocID() == 0) {
			response.setResponseMsg("A01004007005");
			return Response.status(406).entity(response).build();
		} else if (Incident.getiSQN() == 0) {
			response.setResponseMsg("A01004007006");
			return Response.status(407).entity(response).build();
		} else if (Incident.getAlarmLevelID() == 0) {
			response.setResponseMsg("A01004007007");
			return Response.status(408).entity(response).build();
		} else {
			IncidentResponseMsg incidentResponseMsg = new IncidentResponseMsg();
			incidentResponseMsg = ResponseManager.addIncidentResponse(Incident); 
			switch (incidentResponseMsg.getReturnHex()) {
			case "FE":
				incidentResponseMsg.setResponseMessage("A01004007008");
				return Response.status(410).entity(incidentResponseMsg).build();

			default:
				return Response.ok(incidentResponseMsg).header("Access-Control-Allow-Origin", "*").build();
			}
			
		}
	}

	@Path("Incident/searchResponseStatus")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response SearchResponseStatus(SearchResponseStatus Incident) {
		ServerResponse response = new ServerResponse();
		if (Incident.getSequanceNumber() == 0) {
			response.setResponseMsg("A01004008001");
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
			response.setResponseMsg("A01004009001");
			return Response.status(401).entity(response).build();
		} else if (Incident.getResponseStatus() == null || Incident.getResponseStatus().equals("")) {
			response.setResponseMsg("A01004009002");
			return Response.status(402).entity(response).build();
		} else {
			return Response.ok(ResponseManager.UpdateResponseStatus(Incident)).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	
	@Path("incident/getResponseTable")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getResponseTableData(ResponseTableDatePicker model) {
		return Response.ok(ResponseManager.getResponseTableData(model)).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@Path("response/getAllAcceptableCodes")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response getAllAcceptableCodes() {
		
		return Response.ok(AcceptableResponseManager.getAllAcceptableCodes()).header("Access-Control-Allow-Origin", "*").build();
	}
	
	@Path("response/addAcceptableCode")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAcceptableCode(ResponseStatusModel model) {
		ServerResponse response = new ServerResponse();
		response = AcceptableResponseManager.addAcceptableCode(model);
		
		switch (response.getResponseHexCode()) {
		case "01":
			
			return Response.status(401).entity(response).header("Access-Control-Allow-Origin", "*").build();

		default:
			return Response.ok(response).header("Access-Control-Allow-Origin", "*").build();
		}
		
	}
	
	@Path("response/updateAcceptableCode")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAcceptableCode(UpdateResponseStatusModel model) {
		
		ServerResponse response = new ServerResponse();
		response = AcceptableResponseManager.updateAcceptableCode(model);
		
		switch (response.getResponseHexCode()) {
		case "01":
			
			return Response.status(401).entity(response).header("Access-Control-Allow-Origin", "*").build();
			
		case "02":
			
			return Response.status(402).entity(response).header("Access-Control-Allow-Origin", "*").build();
		case "03":
			
			return Response.status(403).entity(response).header("Access-Control-Allow-Origin", "*").build();
		default:
			return Response.ok(response).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	
	@Path("response/deleteAcceptableCode")
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAcceptableCode(ResponseStatusModel model) {
		
		ServerResponse response = new ServerResponse();
		response = AcceptableResponseManager.deleteAcceptableCode(model);
		
		switch (response.getResponseHexCode()) {
		case "01":
			
			return Response.status(401).entity(response).header("Access-Control-Allow-Origin", "*").build();

		default:
			return Response.ok(response).header("Access-Control-Allow-Origin", "*").build();
		}
	}
	
	@Path("incident/getTripHistory")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTripHistory(SearchResponseStatus model) {
		return Response.ok(ResponseManager.getTripHistory(model)).header("Access-Control-Allow-Origin", "*").build();
	}

	
	
	

}
