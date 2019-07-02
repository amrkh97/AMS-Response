package Models.IncidentResponse;

public class ResponseStatusMsg {
	private String responseStatus;
	private String responseMessage;
	private String returnHex;
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
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
