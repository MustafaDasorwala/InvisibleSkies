package response;

public class WeatherData {

	private LocationInfo loc = null;
	private Time time = null;
	private Weather weather = null;
	private WeatherIcon weatherIcon = null;

	public WeatherData() {
	}

	public WeatherData(LocationInfo loc, Time time, Weather weather,WeatherIcon weatherIcon ) {
		super();
		this.loc = loc;
		this.time = time;
		this.weather = weather;
		this.weatherIcon = weatherIcon;
	}

	public WeatherIcon getWeatherIcon() {
		return weatherIcon;
	}

	public void setWeatherIcon(WeatherIcon weatherIcon) {
		this.weatherIcon = weatherIcon;
	}

	public LocationInfo getLoc() {
		return loc;
	}

	public void setLoc(LocationInfo loc) {
		this.loc = loc;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Weather getWeather() {
		return weather;
	}

	public void setWeather(Weather weather) {
		this.weather = weather;
	}

}
