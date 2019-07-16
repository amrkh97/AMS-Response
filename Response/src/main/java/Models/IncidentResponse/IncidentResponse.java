package Models.IncidentResponse;

public class IncidentResponse {
	private int vin;
	private int startLocID;
	private int pickLocID;
	private int dropLocID;
	private int destLocID;
	private int iSQN;
	private int primaryResponseSQN;
	private int alarmLevelID;
	private String personsCount;
	private Integer patientID;
	
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
	public int getPrimaryResponseSQN() {
		return primaryResponseSQN;
	}
	public void setPrimaryResponseSQN(int primaryResponseSQN) {
		this.primaryResponseSQN = primaryResponseSQN;
	}
	public Integer getPatientID() {
		return patientID;
	}
	public void setPatientID(Integer patientID) {
		this.patientID = patientID;
	}

}
