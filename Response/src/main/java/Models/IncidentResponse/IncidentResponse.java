package Models.IncidentResponse;

public class IncidentResponse {
	private int vin;
	private int startLocID;
	private int pickLocID;
	private int dropLocID;
	private int destLocID;
	private String responseStatus;
	private int iSQN;
	private String primaryResponseSQN;
	private int alarmLevelID;
	private String personsCount;
	public int getVin() {
		return vin;
	}
	public void setVin(int vin) {
		this.vin = vin;
	}
	public int getStartLocID() {
		return startLocID;
	}
	public void setStartLocID(int startLocID) {
		this.startLocID = startLocID;
	}
	public int getPickLocID() {
		return pickLocID;
	}
	public void setPickLocID(int pickLocID) {
		this.pickLocID = pickLocID;
	}
	public int getDropLocID() {
		return dropLocID;
	}
	public void setDropLocID(int dropLocID) {
		this.dropLocID = dropLocID;
	}
	public int getDestLocID() {
		return destLocID;
	}
	public void setDestLocID(int destLocID) {
		this.destLocID = destLocID;
	}
	public int getiSQN() {
		return iSQN;
	}
	public void setiSQN(int iSQN) {
		this.iSQN = iSQN;
	}
	public int getAlarmLevelID() {
		return alarmLevelID;
	}
	public void setAlarmLevelID(int alarmLevelID) {
		this.alarmLevelID = alarmLevelID;
	}
	public String getPersonsCount() {
		return personsCount;
	}
	public void setPersonsCount(String personsCount) {
		this.personsCount = personsCount;
	}
	public String getPrimaryResponseSQN() {
		return primaryResponseSQN;
	}
	public void setPrimaryResponseSQN(String primaryResponseSQN) {
		this.primaryResponseSQN = primaryResponseSQN;
	}
	public String getResponseStatus() {
		return responseStatus;
	}
	public void setResponseStatus(String responseStatus) {
		this.responseStatus = responseStatus;
	}

}
