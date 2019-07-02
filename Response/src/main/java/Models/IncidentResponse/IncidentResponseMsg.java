package Models.IncidentResponse;

public class IncidentResponseMsg {
	private int responseID;
	private String responseMessage;
	private String returnHex;
	public int getResponseID() {
		return responseID;
	}
	public void setResponseID(int responseID) {
		this.responseID = responseID;
	}
	public String getResponseMessage() {
		return responseMessage;
	}
	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}
	public String getReturnHex() {
		return returnHex;
	}
	public void setReturnHex(String returnHex) {
		this.returnHex = returnHex;
	}

}
