package response;

public class LocationInfo {

	private String mFull = "";
	private String mCity = "";
	private String mState = "";
	private String mState_name = "";
	private String mCountry = "";
	private String mCountry_iso3166 = "";
	private String mZip = "";
	private String mLatitude = "";
	private String mLongitude = "";
	private String mElevation = "";

	public LocationInfo() {
	}

	public LocationInfo(String full, String city, String state, String state_name,
			String country, String country_iso3166, String zip,
			String latitude, String longitude, String elevation) {
		super();
		mFull = full;
		mCity = city;
		mState = state;
		mState_name = state_name;
		mCountry = country;
		mCountry_iso3166 = country_iso3166;
		mZip = zip;
		mLatitude = latitude;
		mLongitude = longitude;
		mElevation = elevation;
	}

	public String getFull() {
		return mFull;
	}

	public void setFull(String full) {
		mFull = full;
	}

	public String getCity() {
		return mCity;
	}

	public void setCity(String city) {
		mCity = city;
	}

	public String getState() {
		return mState;
	}

	public void setState(String state) {
		mState = state;
	}

	public String getState_name() {
		return mState_name;
	}

	public void setState_name(String state_name) {
		mState_name = state_name;
	}

	public String getCountry() {
		return mCountry;
	}

	public void setCountry(String country) {
		mCountry = country;
	}

	public String getCountry_iso3166() {
		return mCountry_iso3166;
	}

	public void setCountry_iso3166(String country_iso3166) {
		mCountry_iso3166 = country_iso3166;
	}

	public String getZip() {
		return mZip;
	}

	public void setZip(String zip) {
		mZip = zip;
	}

	public String getLatitude() {
		return mLatitude;
	}

	public void setLatitude(String latitude) {
		mLatitude = latitude;
	}

	public String getLongitude() {
		return mLongitude;
	}

	public void setLongitude(String longitude) {
		mLongitude = longitude;
	}

	public String getElevation() {
		return mElevation;
	}

	public void setElevation(String elevation) {
		mElevation = elevation;
	}

}
