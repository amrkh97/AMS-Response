package BLL;

import java.sql.Connection;
import java.sql.SQLException;

import DAL.RequestLogDAL;
import DB.DBManager;
import Models.ActivityLog.RequestLogArray;
import Models.ActivityLog.RequestLogData;

public class RequestLogManager {
	
	public static void insertRequestData(RequestLogData data) {
		Connection intermediateConnection = DBManager.getDBConn();
		try {
			RequestLogDAL.insertRequestData(data.getIpAddress(),data.getRequestURL(),intermediateConnection);
		} finally {
			try {
				intermediateConnection.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return;	
	}

	public static RequestLogArray getAllActivityLogs() {
		Connection intermediateConnection = DBManager.getDBConn();
		RequestLogArray logArray = new RequestLogArray();
		try {
			logArray = RequestLogDAL.getAllActivityLogs(intermediateConnection);
		} finally {
			try {
				intermediateConnection.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return logArray;
	}
	
	

}
