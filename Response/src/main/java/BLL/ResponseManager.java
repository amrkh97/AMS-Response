package BLL;

import DAL.IncidentResponseDAL;
import Models.Callers.CallersArray;
import Models.Data.DataModel;
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

	public static CallersArray getCallers(DataModel incident) {
		
		return IncidentResponseDAL.getCallers(incident.getSentID());
	}
}
