package Models.Location;

public class LocationModel {

	private String longitude;
	private String latitude;
	private String freeFormatAddress;
	
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getFreeFormatAddress() {
		return freeFormatAddress;
	}
	public void setFreeFormatAddress(String freeFormatAddress) {
		this.freeFormatAddress = freeFormatAddress;
	}
}
