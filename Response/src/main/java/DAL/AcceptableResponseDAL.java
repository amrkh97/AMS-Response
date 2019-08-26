package DAL;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import Models.AcceptableResponseStatus.ResponseStatusArray;
import Models.AcceptableResponseStatus.ResponseStatusModel;
import Models.IncidentResponse.ServerResponse;
import Models.AcceptableResponseStatus.UpdateResponseStatusModel;

public class AcceptableResponseDAL {

	public static ServerResponse addAcceptableCode(ResponseStatusModel model, Connection intermediateConnection) {
		String SPsql = "EXEC [dbo].[Add_AcceptedResponseCode] ?,?,?,?";
		ServerResponse response = new ServerResponse();
		try {
			CallableStatement cstmt = intermediateConnection.prepareCall(SPsql);

			cstmt.setString(1, model.getResponseCode());
			cstmt.setString(2, model.getResponseMeaning());
			cstmt.registerOutParameter(3, Types.NVARCHAR);
			cstmt.registerOutParameter(4, Types.NVARCHAR);
			cstmt.executeUpdate();
			
			response.setResponseHexCode(cstmt.getString(3));
			response.setResponseMsg(cstmt.getString(4));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return response;
	}
	
	public static ServerResponse deleteAcceptableCode(ResponseStatusModel model, Connection intermediateConnection) {
		String SPsql = "EXEC [dbo].[Delete_AcceptedResponseCode] ?,?,?";
		ServerResponse response = new ServerResponse();
		try {
			CallableStatement cstmt = intermediateConnection.prepareCall(SPsql);

			cstmt.setString(1, model.getResponseCode());
			cstmt.registerOutParameter(2, Types.NVARCHAR);
			cstmt.registerOutParameter(3, Types.NVARCHAR);
			cstmt.executeUpdate();
			
			response.setResponseHexCode(cstmt.getString(2));
			response.setResponseMsg(cstmt.getString(3));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return response;
	}

	public static ServerResponse updateAcceptableCode(UpdateResponseStatusModel model, Connection intermediateConnection) {
		String SPsql = "EXEC [dbo].[Update_AcceptedResponseCode] ?,?,?,?,?";
		ServerResponse response = new ServerResponse();
		try {
			CallableStatement cstmt = intermediateConnection.prepareCall(SPsql);

			cstmt.setString(1, model.getOldResponseCode());
			cstmt.setString(2, model.getResponseCode());
			cstmt.setString(3, model.getResponseMeaning());
			cstmt.registerOutParameter(4, Types.NVARCHAR);
			cstmt.registerOutParameter(5, Types.NVARCHAR);
			cstmt.executeUpdate();
			
			response.setResponseHexCode(cstmt.getString(4));
			response.setResponseMsg(cstmt.getString(5));
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		return response;
	}

	public static ResponseStatusArray getAllAcceptableCodes(Connection intermediateConnection) {
		String SPsql = "EXEC [dbo].[Update_AcceptedResponseCode] ";
		ResponseStatusArray response = new ResponseStatusArray();
		ArrayList<ResponseStatusModel> arrayList = new ArrayList<ResponseStatusModel>();
		ResultSet rs;
		try {
			CallableStatement cstmt = intermediateConnection.prepareCall(SPsql);
			rs = cstmt.executeQuery();
			while (rs.next()) {
				ResponseStatusModel model = new ResponseStatusModel();
				model.setResponseCode(rs.getString("StatusCode"));
				model.setResponseMeaning(rs.getString("StatusMsg"));
				arrayList.add(model);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		response.setResponseArray(arrayList);
		return response;
	}

}
