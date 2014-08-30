package response;

public class Weather {

	private String mWeather = "";
	private String mHumidity = "";
	private Temperature mTemp = null;
	

	public Weather() {
	}

	public Weather(String weather, String humidity, Temperature temp) {
		super();
		mWeather = weather;
		mHumidity = humidity;
		mTemp = temp;
		
	}

	public String getHumidity() {
		return mHumidity;
	}

	public void setHumidity(String humidity) {
		mHumidity = humidity;
	}

	public Temperature getTemp() {
		return mTemp;
	}

	public void setTemp(Temperature temp) {
		this.mTemp = temp;
	}

	public String getWeather() {
		return mWeather;
	}

	public void setWeather(String weather) {
		mWeather = weather;
	}
}
