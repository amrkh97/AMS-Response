package DAL;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import DB.DBManager;
import Models.AndroidResponse.AndroidResponseModel;
import Models.Firebase.FBLocation.FBLocationEnum;
import Models.Firebase.FBLocation.HttpConnectionHelper;
import Models.IncidentResponse.*;
import Models.Location.LocationModel;

public class IncidentResponseDAL {
	public static IncidentResponseMsg addResponse(IncidentResponse incidentResponse) throws Exception {

		String SPsql = "EXEC usp_Response_Insert ?,?,?,?,?,?,?,?,?,?,?,?";
		IncidentResponseMsg ResponseData = new IncidentResponseMsg();
		Connection conn = DBManager.getDBConn();
		String YelloPadUniqueID = "";
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

			// -------------------------------------------------//
			if (ResponseData.getReturnHex().equals("00")) {

				AndroidResponseModel currentResponse = new AndroidResponseModel();
				LocationModel startLocation = new LocationModel();
				LocationModel destinationLocation = new LocationModel();

				SPsql = "USE KAN_AMO; EXEC [dbo].[usp_getAndroidIncident]" + "?,?,?,?,?,?,?,?,?,?,?,?,?"
						+ ",?,?,?,?,?,?,?,?,?,?,?,?" + ",?,?,?,?";

				cstmt = conn.prepareCall(SPsql);
				cstmt.setInt(1, incidentResponse.getVin());
				cstmt.setInt(2, incidentResponse.getStartLocID());
				cstmt.setInt(3, incidentResponse.getDestLocID());
				cstmt.setInt(4, incidentResponse.getAlarmLevelID());
				cstmt.setInt(5, incidentResponse.getiSQN());

				cstmt.registerOutParameter(6, Types.NVARCHAR); // Driver First Name
				cstmt.registerOutParameter(7, Types.NVARCHAR); // Driver Last Name
				cstmt.registerOutParameter(8, Types.NVARCHAR); // Paramedic First Name
				cstmt.registerOutParameter(9, Types.NVARCHAR); // Paramedic Last Name
				cstmt.registerOutParameter(10, Types.NVARCHAR);// Car Model
				cstmt.registerOutParameter(11, Types.NVARCHAR);// Car Brand
				cstmt.registerOutParameter(12, Types.NVARCHAR);// Car License
				cstmt.registerOutParameter(13, Types.NVARCHAR);// Start Longitude
				cstmt.registerOutParameter(14, Types.NVARCHAR);// Start Latitude
				cstmt.registerOutParameter(15, Types.NVARCHAR);// Start FFA
				cstmt.registerOutParameter(16, Types.NVARCHAR);// Dest Longitude
				cstmt.registerOutParameter(17, Types.NVARCHAR);// Dest Latitude
				cstmt.registerOutParameter(18, Types.NVARCHAR);// Dest FFA
				cstmt.registerOutParameter(19, Types.NVARCHAR);// Incident Name
				cstmt.registerOutParameter(20, Types.NVARCHAR);// Incident Note
				cstmt.registerOutParameter(21, Types.NVARCHAR);// Priority Name
				cstmt.registerOutParameter(22, Types.NVARCHAR);// Priority Note
				cstmt.registerOutParameter(23, Types.NVARCHAR);// Alarm Name
				cstmt.registerOutParameter(24, Types.NVARCHAR);// Alarm Note
				cstmt.registerOutParameter(25, Types.BIGINT); // BatchID
				cstmt.registerOutParameter(26, Types.INTEGER); // PatientID
				cstmt.registerOutParameter(27, Types.NVARCHAR); // CallerFName
				cstmt.registerOutParameter(28, Types.NVARCHAR); // CallerLName
				cstmt.registerOutParameter(29, Types.NVARCHAR); // CallerMobile
				cstmt.executeUpdate();

				currentResponse.setResponseSequenceNumber(ResponseData.getResponseID());
				currentResponse.setDriverFullname(cstmt.getString(6) + " " + cstmt.getString(7));
				currentResponse.setParamedicFullname(cstmt.getString(8) + " " + cstmt.getString(9));
				currentResponse.setCarModel(cstmt.getString(10));
				currentResponse.setCarBrand(cstmt.getString(11));
				currentResponse.setLicensePlate(cstmt.getString(12));
				startLocation.setLongitude(cstmt.getString(13));
				startLocation.setLatitude(cstmt.getString(14));
				startLocation.setFreeFormatAddress(cstmt.getString(15));
				currentResponse.setStartLocation(startLocation);

				destinationLocation.setLongitude(cstmt.getString(16));
				destinationLocation.setLatitude(cstmt.getString(17));
				destinationLocation.setFreeFormatAddress(cstmt.getString(18));
				currentResponse.setDestinationLocation(destinationLocation);
				
				String encodedIncidentType = cstmt.getString(19);
				encodedIncidentType = URLEncoder.encode(encodedIncidentType, "UTF-8");
				
				currentResponse.setIncidentType(encodedIncidentType);
				currentResponse.setIncidentPriority(cstmt.getString(21));
				currentResponse.setAlarmLevel(cstmt.getString(23));
				currentResponse.setBatchID(cstmt.getLong(25));
				currentResponse.setPatientID(incidentResponse.getPatientID());
				currentResponse.setCallerName(cstmt.getString(27) + " " + cstmt.getString(28));
				currentResponse.setCallerMobile(cstmt.getString(29));

				YelloPadUniqueID = getYellopadUniqueID(incidentResponse.getVin(), conn);

				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				System.out.println(gson.toJson(currentResponse));
				HttpConnectionHelper httpConnectionHelper = new HttpConnectionHelper();
				httpConnectionHelper.sendPost(FBLocationEnum.FBResponseURL.getJsonKey() + YelloPadUniqueID + ".json",
						gson.toJson(currentResponse).toString());

			} else {

				// Do Nothing

			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				System.out.println("Connention Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return ResponseData;
	}

	public static String getYellopadUniqueID(Integer id, Connection conn) {

		String SPsql = "EXEC usp_IncidentResponse_GetYelloPad ?,?";
		String ResponseData = "";

		try {

			CallableStatement cstmt = conn.prepareCall(SPsql);
			cstmt.setInt(1, id);
			cstmt.registerOutParameter(2, Types.NVARCHAR);
			cstmt.executeUpdate();
			ResponseData = cstmt.getString(2); // YelloPadUniqueID

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return ResponseData;
	}

	public static ResponseStatusMsg SearchResponseStatus(SearchResponseStatus incidentResponse) {

		String SPsql = "EXEC usp_ResponseStatus_SearchByID ?,?,?,?";
		ResponseStatusMsg ResponseData = new ResponseStatusMsg();
		Connection conn = DBManager.getDBConn();
		try {
			CallableStatement cstmt = conn.prepareCall(SPsql);
			cstmt.setInt(1, incidentResponse.getSequanceNumber());
			cstmt.registerOutParameter(2, Types.NVARCHAR);
			cstmt.registerOutParameter(3, Types.NVARCHAR);
			cstmt.registerOutParameter(4, Types.NVARCHAR);
			cstmt.execute();
			ResponseData.setResponseStatus(cstmt.getString(4));
			ResponseData.setResponseMessage(cstmt.getString(3));
			ResponseData.setReturnHex(cstmt.getString(2));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				System.out.println("Connention Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return ResponseData;
	}

	public static UpdateResponseStatusMsg UpdateResponseStatus(UpdateResponseStatus incidentResponse) {

		String SPsql = "EXEC usp_ResponseStatus_UpdateByID ?,?,?,?,?";
		UpdateResponseStatusMsg ResponseData = new UpdateResponseStatusMsg();
		Connection conn = DBManager.getDBConn();
		try {
			CallableStatement cstmt = conn.prepareCall(SPsql);
			cstmt.setInt(1, incidentResponse.getSequanceNumber());
			cstmt.setString(2, incidentResponse.getResponseStatus());
			cstmt.registerOutParameter(3, Types.NVARCHAR);
			cstmt.registerOutParameter(4, Types.NVARCHAR);
			cstmt.registerOutParameter(5, Types.NVARCHAR);
			cstmt.execute();
			ResponseData.setResponseStatus(cstmt.getString(5));
			ResponseData.setResponseMessage(cstmt.getString(4));
			ResponseData.setReturnHex(cstmt.getString(3));
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				System.out.println("Connention Closed");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return ResponseData;
	}
	public static ResponseTableJson getResponseTable() {

		String responseTableSP = "EXEC usp_Response_TableData";
		Connection conn = DBManager.getDBConn();
		ArrayList<ResponseTable> responseDataArray = new ArrayList<ResponseTable>();
		ResponseTableJson responseTableJson = new ResponseTableJson();
		ResponseTable responseData;
		try {
			CallableStatement cstmt = conn.prepareCall(responseTableSP);
			ResultSet rs = cstmt.executeQuery();

			while (rs.next()) {
				responseData = new ResponseTable();
				responseData.setiSQN(rs.getInt(1));
				responseData.setTypeName(rs.getString(2));
				responseData.setResponseID(rs.getInt(3));
				responseData.setPriorityName(rs.getString(4));
				responseData.setRespStatus(rs.getString(5));
				responseData.setVin(rs.getInt(6));
				responseData.setParamID(rs.getInt(7));
				responseData.setParamFname(rs.getString(8));
				responseData.setParamLname(rs.getString(9));
				responseData.setParamContact(rs.getString(10));
				responseData.setDriverID(rs.getInt(11));
				responseData.setDriverFname(rs.getString(12));
				responseData.setDriverLname(rs.getString(13));
				responseData.setDriverContact(rs.getString(14));
				responseData.setLicensePlate(rs.getString(15));
				responseData.setModel(rs.getString(16));
//				responseData.setFfa(URLDecoder.decode(rs.getString(17),"UTF-8"));
				responseData.setFfa(rs.getString(17));
				
				responseDataArray.add(responseData);
			}
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				conn.close();
				System.out.println("Connection Closed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block

				e.printStackTrace();
			}
		}
		responseTableJson.setResponseTableData(responseDataArray);
		return responseTableJson;
	}

}
