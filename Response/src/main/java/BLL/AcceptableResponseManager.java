package BLL;

import java.sql.Connection;
import java.sql.SQLException;

import DAL.AcceptableResponseDAL;
import DB.DBManager;
import Models.AcceptableResponseStatus.ResponseStatusArray;
import Models.AcceptableResponseStatus.ResponseStatusModel;
import Models.AcceptableResponseStatus.UpdateResponseStatusModel;
import Models.IncidentResponse.ServerResponse;

public class AcceptableResponseManager {

	public static ServerResponse addAcceptableCode(ResponseStatusModel model) {
		ServerResponse serverResponse = new ServerResponse();
		Connection intermediateConnection = DBManager.getDBConn();
		try {
			serverResponse = AcceptableResponseDAL.addAcceptableCode(model,intermediateConnection);
		} finally {
			try {
				intermediateConnection.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return serverResponse;
	}
	
	public static ServerResponse deleteAcceptableCode(ResponseStatusModel model) {
		ServerResponse serverResponse = new ServerResponse();
		Connection intermediateConnection = DBManager.getDBConn();
		try {
			serverResponse = AcceptableResponseDAL.deleteAcceptableCode(model,intermediateConnection);
		} finally {
			try {
				intermediateConnection.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return serverResponse;
	}

	public static ServerResponse updateAcceptableCode(UpdateResponseStatusModel model) {
		ServerResponse serverResponse = new ServerResponse();
		Connection intermediateConnection = DBManager.getDBConn();
		try {
			serverResponse = AcceptableResponseDAL.updateAcceptableCode(model,intermediateConnection);
		} finally {
			try {
				intermediateConnection.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return serverResponse;
	}

	public static ResponseStatusArray getAllAcceptableCodes() {
		ResponseStatusArray responseStatusArray = new ResponseStatusArray();
		Connection intermediateConnection = DBManager.getDBConn();
		try {
			responseStatusArray = AcceptableResponseDAL.getAllAcceptableCodes(intermediateConnection);
		} finally {
			try {
				intermediateConnection.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return responseStatusArray;
	}

}
