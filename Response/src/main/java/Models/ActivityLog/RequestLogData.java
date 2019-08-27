package Models.ActivityLog;

//TODO: Add Extra Parameters after adding token.
public class RequestLogData {

	private String ipAddress;
	private String requestURL;
	
	public String getIpAddress() {
		return ipAddress;
	}
	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}
	public String getRequestURL() {
		return requestURL;
	}
	public void setRequestURL(String requestURL) {
		this.requestURL = requestURL;
	}
	
}
