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
	public static ResponseTableJson getResponseTableData() {
		return IncidentResponseDAL.getResponseTable();
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
	
}
