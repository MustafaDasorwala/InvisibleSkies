package response;

public class WeatherData {

	private Location loc = null;
	private Time time = null;
	private Weather weather = null;

	public WeatherData() {
	}

	public WeatherData(Location loc, Time time, Weather weather) {
		super();
		this.loc = loc;
		this.time = time;
		this.weather = weather;
	}

	public Location getLoc() {
		return loc;
	}

	public void setLoc(Location loc) {
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
