package BLL;

import java.sql.Connection;
import java.sql.SQLException;

import DAL.IncidentResponseDAL;
import DB.DBManager;
import Models.IncidentResponse.*;

public class ResponseManager {

	public static IncidentResponseMsg addIncidentResponse(IncidentResponse Incident) throws Exception {
		return IncidentResponseDAL.addResponse(Incident);
	}

	public static ResponseStatusMsg SearchResponseStatus(SearchResponseStatus Incident) {
		return IncidentResponseDAL.SearchResponseStatus(Incident);
	}

	public static UpdateResponseStatusMsg UpdateResponseStatus(UpdateResponseStatus Incident) {
		return IncidentResponseDAL.UpdateResponseStatus(Incident);
	}
	
	@SuppressWarnings("unused")
	public static ResponseTableJson getResponseTableData(ResponseTableDatePicker model) {
		ResponseTableJson responseTableJson = new ResponseTableJson();
		Connection intermediateConnection = DBManager.getDBConn();
		try {
			
			try {
				Integer check = model.getDays();
			} catch (Exception e) {
				model = new ResponseTableDatePicker();
				model.setDays(1);
			}
			
			responseTableJson = IncidentResponseDAL.getResponseTable(model.getDays(),intermediateConnection);
		} finally {
			try {
				intermediateConnection.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return responseTableJson;
	}

	public static TripHistoryArray getTripHistory(SearchResponseStatus model) {
		Connection intermediateConnection = DBManager.getDBConn();
		TripHistoryArray historyArray = new TripHistoryArray();
		
		try {
			historyArray = IncidentResponseDAL.getTripHistory(model.getSequanceNumber(),intermediateConnection);
		} finally {
			try {
				intermediateConnection.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return historyArray;
	}

	public static ServerResponse assignVehicleToResponse(IncidentResponse model) {
		Connection intermediateConnection = DBManager.getDBConn();
		ServerResponse response = new ServerResponse();
		
		try {
			response = IncidentResponseDAL.assignVehicleToResponse(model.getPrimaryResponseSQN(),model.getVin(),intermediateConnection);
		} finally {
			try {
				intermediateConnection.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return response;
	}

	public static ResponseTableJson getCarHistory(IncidentResponse model) {
		ResponseTableJson responseTableJson = new ResponseTableJson();
		Connection intermediateConnection = DBManager.getDBConn();
		try {
			responseTableJson = IncidentResponseDAL.getCarHistory(model.getVin(),intermediateConnection);
		} finally {
			try {
				intermediateConnection.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return responseTableJson;
	}

	public static ServerResponse editResponse(IncidentResponse incident) {
		Connection intermediateConnection = DBManager.getDBConn();
		ServerResponse response = new ServerResponse();
		
		try {
			response = IncidentResponseDAL.editResponse(incident,intermediateConnection);
		} finally {
			try {
				intermediateConnection.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		
		return response;
	}
	
}
