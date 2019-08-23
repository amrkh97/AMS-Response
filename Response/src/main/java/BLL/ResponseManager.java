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
	public static ResponseTableJson getResponseTableData(ResponseTableDatePicker model) {
		ResponseTableJson responseTableJson = new ResponseTableJson();
		Connection intermediateConnection = DBManager.getDBConn();
		try {
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
	
}
