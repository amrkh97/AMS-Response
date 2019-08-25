package Models.AcceptableResponseStatus;

public class UpdateResponseStatusModel {
	
	private String responseCode;
	private String responseMeaning;
	private String oldResponseCode;

	public String getOldResponseCode() {
		return oldResponseCode;
	}

	public void setOldResponseCode(String oldResponseCode) {
		this.oldResponseCode = oldResponseCode;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getResponseMeaning() {
		return responseMeaning;
	}

	public void setResponseMeaning(String responseMeaning) {
		this.responseMeaning = responseMeaning;
	}

}
