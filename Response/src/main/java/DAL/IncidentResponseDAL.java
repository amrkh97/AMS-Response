package DAL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

import DB.DBManager;
import Models.IncidentResponse.*;

public class IncidentResponseDAL {
	public static IncidentResponseMsg addResponse(IncidentResponse incidentResponse) {

		String SPsql = "EXEC usp_Response_Insert ?,?,?,?,?,?,?,?,?,?,?,?";
		IncidentResponseMsg ResponseData = new IncidentResponseMsg();
		Connection conn = DBManager.getDBConn();
		try {
			CallableStatement cstmt = conn.prepareCall(SPsql);
			cstmt.setInt(1, incidentResponse.getVin());
			cstmt.setInt(2, incidentResponse.getStartLocID());
			cstmt.setInt(3, incidentResponse.getPickLocID());
			cstmt.setInt(4, incidentResponse.getDropLocID());
			cstmt.setInt(5, incidentResponse.getDestLocID());
			cstmt.setInt(6, incidentResponse.getiSQN());
			cstmt.setInt(7, incidentResponse.getPrimaryResponseSQN());
			cstmt.setInt(8, incidentResponse.getAlarmLevelID());
			cstmt.setString(9, incidentResponse.getPersonsCount());
			cstmt.registerOutParameter(10, Types.NVARCHAR);
			cstmt.registerOutParameter(11, Types.NVARCHAR);
			cstmt.registerOutParameter(12, Types.INTEGER);
			cstmt.execute();
			ResponseData.setResponseID(cstmt.getInt(12));
			ResponseData.setReturnHex(cstmt.getString(10));
			ResponseData.setResponseMessage(cstmt.getString(11));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				System.out.println("Connention Closed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return ResponseData;
	}

}
