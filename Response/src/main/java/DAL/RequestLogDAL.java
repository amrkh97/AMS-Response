package DAL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.ActivityLog.RequestLogArray;
import Models.ActivityLog.RequestLogData;

public class RequestLogDAL {

	public static void insertRequestData(String ipAddress, String requestURL, Connection intermediateConnection) {
		
		String SPsql = "USE KAN_AMO;  EXEC [dbo].[Insert_Into_Log] ?,?";
		try {
			CallableStatement cstmt = intermediateConnection.prepareCall(SPsql);
			cstmt.setString(1, ipAddress);
			cstmt.setString(2, requestURL);
			cstmt.executeUpdate();
			
		
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}

	public static RequestLogArray getAllActivityLogs(Connection intermediateConnection) {
		String SPsql = "USE KAN_AMO;  EXEC [dbo].[Get_All_Logs]";
		ResultSet RS;
		RequestLogArray logArray = new RequestLogArray();
		ArrayList<RequestLogData> requestLogData = new ArrayList<RequestLogData>();
		try {
			CallableStatement cstmt = intermediateConnection.prepareCall(SPsql);
			RS = cstmt.executeQuery();
			
			while (RS.next()) {
				RequestLogData data = new RequestLogData();
				data.setIpAddress(RS.getString(2));
				data.setRequestURL(RS.getString(3));
				requestLogData.add(data);
			}
		
		} catch (SQLException e) {

			e.printStackTrace();
		}
		logArray.setLogData(requestLogData);
		return logArray;
	}

}
