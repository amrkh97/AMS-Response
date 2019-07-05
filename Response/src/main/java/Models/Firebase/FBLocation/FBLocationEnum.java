package Models.Firebase.FBLocation;

public enum FBLocationEnum 
{
	FBLocationsURL("https://ambulance-73353.firebaseio.com/Location.json"),
	FBResponseURL("https://ambulance-73353.firebaseio.com/Response/1d11c1d4ea9b8e5b.json"),
	Address("address"),
	Longitude("longitude"),
	Latitude("latitude");
	
	private String jsonKey;
	
	FBLocationEnum(String jsonKey)
	{
		this.jsonKey = jsonKey;
	}
	
	public String getJsonKey()
	{
		return this.jsonKey;
	}
}
