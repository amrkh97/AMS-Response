package Models.AndroidResponse;

import Models.Location.LocationModel;

public class AndroidResponseModel {
	
	private String driverFullname;

	private String paramedicFullname;

	private String carModel;
	private String carBrand;
	private String licensePlate;

	private LocationModel startLocation;

	private LocationModel destinationLocation;
	private String destLocLong;
	private String destLocLat;
	private String destLocFFA ;

	private String incidentType;
	private String incidentPriority;

	private String alarmLevel;

	//---------------------------------------------------//
	public String getDriverFullname() {
		return driverFullname;
	}
	public void setDriverFullname(String driverFullname) {
		this.driverFullname = driverFullname;
	}

	public String getParamedicFullname() {
		return paramedicFullname;
	}
	public void setParamedicFullname(String paramedicFullname) {
		this.paramedicFullname = paramedicFullname;
	}
	
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public String getCarBrand() {
		return carBrand;
	}
	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}
	public String getLicensePlate() {
		return licensePlate;
	}
	public void setLicensePlate(String license) {
		this.licensePlate = license;
	}
	public LocationModel getStartLocation() {
		return startLocation;
	}
	public void setStartLocation(LocationModel startLocation) {
		this.startLocation = startLocation;
	}
	
	public LocationModel getDestinationLocation() {
		return destinationLocation;
	}
	public void setDestinationLocation(LocationModel destinationLocation) {
		this.destinationLocation = destinationLocation;
	}
	public String getDestLocLong() {
		return destLocLong;
	}
	public void setDestLocLong(String destLocLong) {
		this.destLocLong = destLocLong;
	}
	public String getDestLocLat() {
		return destLocLat;
	}
	public void setDestLocLat(String destLocLat) {
		this.destLocLat = destLocLat;
	}
	public String getDestLocFFA() {
		return destLocFFA;
	}
	public void setDestLocFFA(String destLocFFA) {
		this.destLocFFA = destLocFFA;
	}
	public String getIncidentType() {
		return incidentType;
	}
	public void setIncidentType(String incidentType) {
		this.incidentType = incidentType;
	}
	public String getIncidentPriority() {
		return incidentPriority;
	}
	public void setIncidentPriority(String incidentPriority) {
		this.incidentPriority = incidentPriority;
	}
	public String getAlarmLevel() {
		return alarmLevel;
	}
	public void setAlarmLevel(String alarmLevelName) {
		this.alarmLevel = alarmLevelName;
	}

	
	
}