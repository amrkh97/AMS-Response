package BLL;

import DAL.IncidentResponseDAL;
import Models.IncidentResponse.*;

public class ResponseManager {
	
	public static IncidentResponseMsg addIncidentResponse(IncidentResponse Incident) {
		return IncidentResponseDAL.addResponse(Incident);
	}

}
